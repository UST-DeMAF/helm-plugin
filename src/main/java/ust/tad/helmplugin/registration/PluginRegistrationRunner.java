package ust.tad.helmplugin.registration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.listener.AbstractMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import ust.tad.helmplugin.analysistask.AnalysisTaskReceiver;

/**
 * Runner that is executed at application startup to register this plugin at the Analysis Manager.
 */
@Component
public class PluginRegistrationRunner implements ApplicationRunner {

  private static final Logger LOG = LoggerFactory.getLogger(PluginRegistrationRunner.class);

  @Autowired private GenericApplicationContext context;

  @Autowired private WebClient pluginRegistrationApiClient;

  @Autowired private RabbitAdmin rabbitAdmin;

  @Autowired private AnalysisTaskReceiver analysisTaskReceiver;

  @Value("${analysis-manager.plugin-registration.url}")
  private String pluginRegistrationURI;

  @Value("${plugin.technology}")
  private String pluginTechnology;

  @Value("${plugin.analysis-type}")
  private String pluginAnalysisType;

  @Value("${messaging.analysistask.response.exchange.name}")
  private String responseExchangeName;

  @Override
  public void run(ApplicationArguments args) throws JsonProcessingException, InterruptedException {

    LOG.info("Registering Plugin");

    connectionAttempt();

    String body = createPluginRegistrationBody();

    PluginRegistrationResponse response =
        pluginRegistrationApiClient
            .post()
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(body))
            .retrieve()
            .bodyToMono(PluginRegistrationResponse.class)
            .block();

    LOG.info("Received response: " + response.toString());

    AbstractMessageListenerContainer requestQueueListener =
        createListenerForRequestQueue(
            response.getRequestQueueName(), message -> analysisTaskReceiver.receive(message));

    context.registerBean(
        "requestQueueListener", requestQueueListener.getClass(), requestQueueListener);

    context.registerBean(
        responseExchangeName,
        FanoutExchange.class,
        () -> new FanoutExchange(response.getResponseExchangeName(), true, false));
  }

  /**
   * Try to reach the service 20 times (maxAttempts).
   *
   * @throws InterruptedException
   */
  private void connectionAttempt() throws InterruptedException {
    String host = pluginRegistrationURI.split(":")[1].replace("/", "");
    int port = Integer.parseInt(pluginRegistrationURI.split(":")[2].split("/")[0]);
    int maxAttempts = 20;

    for (int attempt = 0; attempt < maxAttempts; attempt++) {
      if (isServiceReachable(host, port, 5000, attempt + 1, maxAttempts)) {
        break;
      }
      Thread.sleep(2000);
    }
  }

  private String createPluginRegistrationBody() throws JsonProcessingException {
    ObjectMapper mapper = new ObjectMapper();
    ObjectNode plugin = mapper.createObjectNode();
    plugin.put("technology", pluginTechnology);
    plugin.put("analysisType", pluginAnalysisType);
    return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(plugin);
  }

  private AbstractMessageListenerContainer createListenerForRequestQueue(
      String requestQueueName, MessageListener messageListener) {
    SimpleMessageListenerContainer listener =
        new SimpleMessageListenerContainer(rabbitAdmin.getRabbitTemplate().getConnectionFactory());
    listener.addQueueNames(requestQueueName);
    listener.setMessageListener(messageListener);
    listener.start();

    return listener;
  }

  /**
   * Checks if Service is reachable. If so, it returns true so the plugin can start connecting
   * otherwise it returns false.
   *
   * @param hostNameOrIP
   * @param port
   * @param timeout
   * @param attempt
   * @param maxAttempts
   * @return boolean
   */
  public static boolean isServiceReachable(
      String hostNameOrIP, int port, int timeout, int attempt, int maxAttempts) {
    try (Socket socket = new Socket()) {
      // Attempt to connect to the host and port within the given timeout
      socket.connect(new InetSocketAddress(hostNameOrIP, port), timeout);
      LOG.info("Service " + hostNameOrIP + " is reachable. Start registration.");
      return true;
    } catch (IOException e) {
      // Connection failed or timed out
      LOG.info(
          "Service "
              + hostNameOrIP
              + " isn't reachable. Attempt ("
              + attempt
              + "/"
              + maxAttempts
              + ")");
      return false;
    }
  }
}
