package ust.tad.helmplugin.models;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import ust.tad.helmplugin.models.tadm.TechnologyAgnosticDeploymentModel;
import ust.tad.helmplugin.models.tsdm.TechnologySpecificDeploymentModel;

@Service
public class ModelsService {
    
    private static final Logger LOG =
      LoggerFactory.getLogger(ModelsService.class);
    
    @Autowired
    private WebClient modelsServiceApiClient;
    
    @Value("${models-service.url}")
    private String modelsServiceURL;

    /**
     * Retrieve a technology-specific deployment model from the model service.
     * 
     * @param transformationProcessId
     * @return
     */
    public TechnologySpecificDeploymentModel getTechnologySpecificDeploymentModel(UUID transformationProcessId) {
        LOG.info("Requesting technology-specific deployment model");
        return modelsServiceApiClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/technology-specific/"+transformationProcessId)
                .build())
           .accept(MediaType.APPLICATION_JSON)
           .retrieve()
           .bodyToMono(TechnologySpecificDeploymentModel.class)
           .block();
    }

    /**
     * Update a technology-specific deployment model by sending it to the update endpoint of the models service.
     * 
     * @param annotatedDeploymentModel
     */
    public void updateTechnologySpecificDeploymentModel(TechnologySpecificDeploymentModel technologySpecificDeploymentModel) {
        LOG.info("Updating technology-specific deployment model");
        modelsServiceApiClient.post()
            .uri("/technology-specific")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(technologySpecificDeploymentModel))
            .retrieve()
            .bodyToMono(TechnologySpecificDeploymentModel.class)
            .block();
    }

    /**
     * Retrieve a technology-agnostic deployment model from the model service.
     * 
     * @param transformationProcessId
     * @return
     */
    public TechnologyAgnosticDeploymentModel getTechnologyAgnosticDeploymentModel(UUID transformationProcessId) {
        LOG.info("Requesting technology-agnostic deployment model");
         return modelsServiceApiClient.get()
            .uri(uriBuilder -> uriBuilder
                .path("/technology-agnostic/"+transformationProcessId)
                .build())
            .accept(MediaType.APPLICATION_JSON)
            .retrieve()
            .bodyToMono(TechnologyAgnosticDeploymentModel.class)
            .block();
    }

    /**
     * Update a technology-agnostic deployment model by sending it to the update endpoint of the models service.
     * 
     * @param technologyAgnosticDeploymentModel
     */
    public void updateTechnologyAgnosticDeploymentModel(TechnologyAgnosticDeploymentModel technologyAgnosticDeploymentModel) {
        LOG.info("Updating technology-agnostic deployment model");
        modelsServiceApiClient.post()
            .uri("/technology-agnostic")
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON)
            .body(BodyInserters.fromValue(technologyAgnosticDeploymentModel))
            .retrieve()
            .bodyToMono(TechnologyAgnosticDeploymentModel.class)
            .block();
    }
}
