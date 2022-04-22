package ust.tad.helmplugin.analysistask;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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

    @Autowired
    private RabbitTemplate template;

    @Value("${messaging.analysistask.response.exchange.name}")
    private String responseExchangeName;

    
    public void sendSuccessResponse(UUID taskId)  {
        ObjectMapper objectMapper = new ObjectMapper();

        AnalysisTaskResponse analysisTaskResponse = new AnalysisTaskResponse();
        analysisTaskResponse.setTaskId(taskId);
        analysisTaskResponse.setSuccess(true);        

        Message message;
        try {
            message = MessageBuilder
                .withBody(objectMapper.writeValueAsString(analysisTaskResponse).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("formatIndicator", "AnalysisTaskResponse")
                .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }            
        template.convertAndSend(responseExchangeName, "", message);  
    }

    public void sendFailureResponse(UUID taskId, String errorMessage)  {
        ObjectMapper objectMapper = new ObjectMapper();

        AnalysisTaskResponse analysisTaskResponse = new AnalysisTaskResponse();
        if(taskId != null) {
            analysisTaskResponse.setTaskId(taskId);
        }
        analysisTaskResponse.setSuccess(false);      
        analysisTaskResponse.setErrorMessage(errorMessage);  

        Message message;
        try {
            message = MessageBuilder
                .withBody(objectMapper.writeValueAsString(analysisTaskResponse).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("formatIndicator", "AnalysisTaskResponse")
                .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }            
        template.convertAndSend(responseExchangeName, "", message);  
    }

    public void sendEmbeddedDeploymentModelAnalysisRequest(EmbeddedDeploymentModelAnalysisRequest request)  {
        ObjectMapper objectMapper = new ObjectMapper();

        Message message;
        try {
            message = MessageBuilder
                .withBody(objectMapper.writeValueAsString(request).getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setHeader("formatIndicator", "EmbeddedDeploymentModelAnalysisRequest")
                .build();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return;
        }            
        template.convertAndSend(responseExchangeName, "", message);  
    }

    public void sendEmbeddedDeploymentModelAnalysisRequestFromModel(TechnologySpecificDeploymentModel embeddedDeploymentModel, UUID parentTaskId)  {
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


}
