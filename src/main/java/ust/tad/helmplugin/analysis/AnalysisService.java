package ust.tad.helmplugin.analysis;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ust.tad.helmplugin.analysistask.AnalysisTaskResponseSender;
import ust.tad.helmplugin.analysistask.Location;
import ust.tad.helmplugin.models.ModelsService;
import ust.tad.helmplugin.models.tadm.TechnologyAgnosticDeploymentModel;
import ust.tad.helmplugin.models.tsdm.DeploymentModelContent;
import ust.tad.helmplugin.models.tsdm.InvalidAnnotationException;
import ust.tad.helmplugin.models.tsdm.InvalidNumberOfContentException;
import ust.tad.helmplugin.models.tsdm.InvalidNumberOfLinesException;
import ust.tad.helmplugin.models.tsdm.Line;
import ust.tad.helmplugin.models.tsdm.TechnologySpecificDeploymentModel;

@Service
public class AnalysisService {

  @Autowired private ModelsService modelsService;

  @Autowired private AnalysisTaskResponseSender analysisTaskResponseSender;

  @Autowired private HelmCommandExecuter helmCommandExecuter;

  private TechnologySpecificDeploymentModel tsdm;

  private TechnologyAgnosticDeploymentModel tadm;

  private Set<Integer> newEmbeddedDeploymentModelIndexes = new HashSet<>();

  /**
   * Start the analysis of the deployment model. 1. Retrieve internal deployment models from models
   * service 2. Parse in technology-specific deployment model from locations 3. Update tsdm with new
   * information 4. Transform to EDMM entities and update tadm 5. Send updated models to models
   * service 6. Send AnalysisTaskResponse or EmbeddedDeploymentModelAnalysisRequests if present
   *
   * @param taskId
   * @param transformationProcessId
   * @param commands
   * @param locations
   * @throws InterruptedException
   */
  public void startAnalysis(
      UUID taskId,
      UUID transformationProcessId,
      List<String> commands,
      List<String> options,
      List<Location> locations) {
    this.newEmbeddedDeploymentModelIndexes.clear();
    TechnologySpecificDeploymentModel completeTsdm =
        modelsService.getTechnologySpecificDeploymentModel(transformationProcessId);
    this.tsdm = getExistingTsdm(completeTsdm, commands);
    if (tsdm == null) {
      analysisTaskResponseSender.sendFailureResponse(
          taskId, "No technology-specific deployment model found!");
      return;
    }
    this.tadm = modelsService.getTechnologyAgnosticDeploymentModel(transformationProcessId);

    try {
      runAnalysis(commands);
    } catch (IOException
        | InterruptedException
        | InvalidAnnotationException
        | InvalidNumberOfLinesException
        | InvalidNumberOfContentException e) {
      e.printStackTrace();
      analysisTaskResponseSender.sendFailureResponse(taskId, e.getClass() + ": " + e.getMessage());
      return;
    }

    updateDeploymentModels(this.tsdm, this.tadm);

    if (newEmbeddedDeploymentModelIndexes.isEmpty()) {
      analysisTaskResponseSender.sendSuccessResponse(taskId);
    } else {
      for (int index : newEmbeddedDeploymentModelIndexes) {
        analysisTaskResponseSender.sendEmbeddedDeploymentModelAnalysisRequestFromModel(
            this.tsdm.getEmbeddedDeploymentModels().get(index), taskId);
      }
      analysisTaskResponseSender.sendSuccessResponse(taskId);
    }
  }

  private void runAnalysis(List<String> commands)
      throws IOException,
          InterruptedException,
          InvalidAnnotationException,
          InvalidNumberOfLinesException,
          InvalidNumberOfContentException {
    for (String command : commands) {
      if (command.startsWith("helm repo add")) {
        helmCommandExecuter.executeHelmCommand(command);
      } else if (command.startsWith("helm install")) {
        String templateCommand = command.replaceFirst("install", "template");
        String path = helmCommandExecuter.renderTemplate(templateCommand);
        BufferedReader br = new BufferedReader(new FileReader(path));
        if (br.readLine() == null) { //if the file is empty try rendering it again
          System.out.println("Trying to render helm template again!");
          path = helmCommandExecuter.renderTemplate(templateCommand);
        }
        TechnologySpecificDeploymentModel embeddedDeploymentModel =
            addNewEmbeddedDeploymentModel(path);
        int index = this.tsdm.addOrUpdateEmbeddedDeploymentModel(embeddedDeploymentModel);
        this.newEmbeddedDeploymentModelIndexes.add(index);
      }
    }
  }

  private TechnologySpecificDeploymentModel addNewEmbeddedDeploymentModel(String path)
      throws MalformedURLException,
          InvalidAnnotationException,
          InvalidNumberOfLinesException,
          InvalidNumberOfContentException {
    List<Line> lines = new ArrayList<>();
    Line line = new Line();
    line.setNumber(0);
    line.setAnalyzed(false);
    line.setComprehensibility(0D);
    lines.add(line);

    List<DeploymentModelContent> content = new ArrayList<>();
    DeploymentModelContent deploymentModelContent = new DeploymentModelContent();
    deploymentModelContent.setLocation(new File(path).toURI().toURL());
    deploymentModelContent.setLines(lines);
    content.add(deploymentModelContent);

    TechnologySpecificDeploymentModel embeddedDeploymentModel =
        new TechnologySpecificDeploymentModel();
    embeddedDeploymentModel.setTransformationProcessId(this.tsdm.getTransformationProcessId());
    embeddedDeploymentModel.setTechnology("kubernetes");
    embeddedDeploymentModel.setContent(content);
    return embeddedDeploymentModel;
  }

  private TechnologySpecificDeploymentModel getExistingTsdm(
      TechnologySpecificDeploymentModel tsdm, List<String> commands) {
    if (tsdm.getCommands().equals(commands)) {
      return tsdm;
    }
    for (TechnologySpecificDeploymentModel embeddedDeploymentModel :
        tsdm.getEmbeddedDeploymentModels()) {
      TechnologySpecificDeploymentModel foundModel =
          getExistingTsdm(embeddedDeploymentModel, commands);
      if (foundModel != null) {
        return foundModel;
      }
    }
    return null;
  }

  private void updateDeploymentModels(
      TechnologySpecificDeploymentModel tsdm, TechnologyAgnosticDeploymentModel tadm) {
    modelsService.updateTechnologySpecificDeploymentModel(tsdm);
    modelsService.updateTechnologyAgnosticDeploymentModel(tadm);
  }
}
