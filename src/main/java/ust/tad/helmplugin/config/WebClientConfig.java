package ust.tad.helmplugin.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

	@Value("${analysis-manager.plugin-registration.url}")
    private String pluginRegistrationURI;

	@Bean
	public WebClient pluginRegistrationApiClient() {
		return WebClient.create(pluginRegistrationURI);
	}

	@Value("${models-service.url}")
    private String modelsServiceURL;

	@Bean
	public WebClient modelsServiceApiClient() {
		return WebClient.create(modelsServiceURL);
	}
    
}
