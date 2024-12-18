package ust.tad.helmplugin.analysistask;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ust.tad.helmplugin.models.tsdm.DeploymentModelContent;
import ust.tad.helmplugin.models.tsdm.Line;
import ust.tad.helmplugin.models.tsdm.TechnologySpecificDeploymentModel;

@Service
public class AnalysisTaskResponseSender {

  private static final Logger LOG = LoggerFactory.getLogger(AnalysisTaskResponseSender.class);

  @Autowired private RabbitTemplate template;

  @Value("${messaging.analysistask.response.exchange.name}")
  private String responseExchangeName;

  /**
   * Sends a success response to the response exchange.
   *
   * @param taskId The unique identifier of the task.
   */
  public void sendSuccessResponse(UUID taskId) {
    LOG.info("Transformation completed successfully, sending success response");
    AnalysisTaskResponse analysisTaskResponse = new AnalysisTaskResponse();
    analysisTaskResponse.setTaskId(taskId);
    analysisTaskResponse.setSuccess(true);
    sendAnalysisTaskResponse(analysisTaskResponse);
  }

  /**
   * Send a failure response as an Analysis Task Response containing the error message.
   *
   * @param taskId the ID of the current analysis task.
   * @param errorMessage the reason for the failed task.
   */
  public void sendFailureResponse(UUID taskId, String errorMessage) {
    LOG.info("Sending failure response: " + errorMessage);
    AnalysisTaskResponse analysisTaskResponse = new AnalysisTaskResponse();
    if (taskId != null) {
      analysisTaskResponse.setTaskId(taskId);
    }
    analysisTaskResponse.setSuccess(false);
    analysisTaskResponse.setErrorMessage(errorMessage);
    sendAnalysisTaskResponse(analysisTaskResponse);
  }

  /**
   * Send a message containing an Analysis Task Response.
   * The response is added as a JSON in the message body.
   * The format indicator field in the header is used to identify the type of response.
   *
   * @param analysisTaskResponse the Analysis Task Response to send.
   */
  private void sendAnalysisTaskResponse(AnalysisTaskResponse analysisTaskResponse) {
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Message message =
              MessageBuilder.withBody(objectMapper.writeValueAsString(analysisTaskResponse).getBytes())
                      .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                      .setHeader("formatIndicator", "AnalysisTaskResponse")
                      .build();
      template.convertAndSend(responseExchangeName, "", message);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Send the Embedded Deployment Model Analysis Task Request.
   * The request is added as a JSON in the message body.
   * The format indicator field in the header is used to identify the type of response.
   *
   * @param request the Embedded Deployment Model Analysis Task Request.
   */
  public void sendEmbeddedDeploymentModelAnalysisRequest(
          EmbeddedDeploymentModelAnalysisRequest request) {
    LOG.info("Sending EmbeddedDeploymentModelAnalysisRequest: " + request.toString());
    ObjectMapper objectMapper = new ObjectMapper();
    try {
      Message message =
              MessageBuilder.withBody(objectMapper.writeValueAsString(request).getBytes())
                      .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                      .setHeader("formatIndicator", "EmbeddedDeploymentModelAnalysisRequest")
                      .build();
      template.convertAndSend(responseExchangeName, "", message);
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Sends an Embedded Deployment Model Analysis Task Request created from the information of an
   * embedded deployment model to the response exchange.
   *
   * @param embeddedDeploymentModel The embedded deployment model to be sent.
   * @param parentTaskId The unique identifier of the parent task.
   */
  public void sendEmbeddedDeploymentModelAnalysisRequestFromModel(
          TechnologySpecificDeploymentModel embeddedDeploymentModel, UUID parentTaskId) {
    EmbeddedDeploymentModelAnalysisRequest request = new EmbeddedDeploymentModelAnalysisRequest();
    request.setParentTaskId(parentTaskId);
    request.setTransformationProcessId(embeddedDeploymentModel.getTransformationProcessId());
    request.setTechnology(embeddedDeploymentModel.getTechnology());
    request.setCommands(embeddedDeploymentModel.getCommands());
    List<Location> locations = new ArrayList<>();
    for (DeploymentModelContent deploymentModelContent : embeddedDeploymentModel.getContent()) {
      Location location = new Location();
      location.setUrl(deploymentModelContent.getLocation());
      int startLineNumber = 0;
      int endLineNumber = 0;
      for (Line line : deploymentModelContent.getLines()) {
        if (line.getNumber() < startLineNumber) {
          startLineNumber = line.getNumber();
        } else if (line.getNumber() > endLineNumber) {
          endLineNumber = line.getNumber();
        }
      }
      location.setStartLineNumber(startLineNumber);
      location.setEndLineNumber(endLineNumber);
      locations.add(location);
    }
    request.setLocations(locations);

    sendEmbeddedDeploymentModelAnalysisRequest(request);
  }

  /**
   * Send an Embedded Deployment Model Analysis Task Request containing TADM entities to further
   * analyze.
   *
   * @param tadmEntities the TADM entities to analyze.
   * @param parentTaskId the ID of the current analysis task.
   * @param transformationProcessId the ID of the current transformation process.
   * @param technology the deployment technology of the embedded deployment model.
   */
  public void sendEmbeddedDeploymentModelAnalysisRequestFromTADMEntities(
          Map<String, List<String>> tadmEntities, UUID parentTaskId, UUID transformationProcessId
          , String technology) {
    EmbeddedDeploymentModelAnalysisRequest request = new EmbeddedDeploymentModelAnalysisRequest();
    request.setParentTaskId(parentTaskId);
    request.setTransformationProcessId(transformationProcessId);
    request.setTechnology(technology);
    request.setTadmEntities(tadmEntities);
    sendEmbeddedDeploymentModelAnalysisRequest(request);
  }
}
