package ust.tad.helmplugin.models.tsdm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TechnologySpecificDeploymentModel {

  private UUID id = UUID.randomUUID();

  private UUID transformationProcessId;

  private String technology;

  private List<String> commands = new ArrayList<>();

  private List<DeploymentModelContent> content = new ArrayList<>();

  private List<String> options = new ArrayList<>();

  private List<TechnologySpecificDeploymentModel> embeddedDeploymentModels = new ArrayList<>();

  private static final String INVALIDNUMBEROFCONTENTEXCEPTIONMESSAGE =
      "A TechnologySpecificDeploymentModel must have at least one content";

  public TechnologySpecificDeploymentModel() {}

  public TechnologySpecificDeploymentModel(
      UUID transformationProcessId,
      String technology,
      List<String> commands,
      List<DeploymentModelContent> content,
      List<String> options)
      throws InvalidNumberOfContentException {
    if (content.isEmpty()) {
      throw new InvalidNumberOfContentException(INVALIDNUMBEROFCONTENTEXCEPTIONMESSAGE);
    } else {
      this.transformationProcessId = transformationProcessId;
      this.technology = technology;
      this.commands = commands;
      this.content = content;
      this.options = options;
    }
  }

  public TechnologySpecificDeploymentModel(
      UUID transformationProcessId,
      String technology,
      List<String> commands,
      List<DeploymentModelContent> content,
      List<String> options,
      List<TechnologySpecificDeploymentModel> embeddedDeploymentModels)
      throws InvalidNumberOfContentException {
    if (content.isEmpty()) {
      throw new InvalidNumberOfContentException(INVALIDNUMBEROFCONTENTEXCEPTIONMESSAGE);
    } else {
      this.transformationProcessId = transformationProcessId;
      this.technology = technology;
      this.commands = commands;
      this.content = content;
      this.options = options;
      this.embeddedDeploymentModels = embeddedDeploymentModels;
    }
  }

  public UUID getId() {
    return this.id;
  }

  public void setId(UUID id) {
    this.id = id;
  }

  public UUID getTransformationProcessId() {
    return this.transformationProcessId;
  }

  public void setTransformationProcessId(UUID transformationProcessId) {
    this.transformationProcessId = transformationProcessId;
  }

  public String getTechnology() {
    return this.technology;
  }

  public void setTechnology(String technology) {
    this.technology = technology;
  }

  public List<String> getCommands() {
    return this.commands;
  }

  public void setCommands(List<String> commands) {
    this.commands = commands;
  }

  public List<DeploymentModelContent> getContent() {
    return this.content;
  }

  public void setContent(List<DeploymentModelContent> content)
      throws InvalidNumberOfContentException {
    if (content.isEmpty()) {
      throw new InvalidNumberOfContentException(INVALIDNUMBEROFCONTENTEXCEPTIONMESSAGE);
    } else {
      this.content = content;
    }
  }

  public List<String> getOptions() {
    return this.options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public List<TechnologySpecificDeploymentModel> getEmbeddedDeploymentModels() {
    return this.embeddedDeploymentModels;
  }

  public void setEmbeddedDeploymentModels(
      List<TechnologySpecificDeploymentModel> embeddedDeploymentModels) {
    this.embeddedDeploymentModels = embeddedDeploymentModels;
  }

  public TechnologySpecificDeploymentModel id(UUID id) {
    setId(id);
    return this;
  }

  public TechnologySpecificDeploymentModel transformationProcessId(UUID transformationProcessId) {
    setTransformationProcessId(transformationProcessId);
    return this;
  }

  public TechnologySpecificDeploymentModel technology(String technology) {
    setTechnology(technology);
    return this;
  }

  public TechnologySpecificDeploymentModel commands(List<String> commands) {
    setCommands(commands);
    return this;
  }

  public TechnologySpecificDeploymentModel content(List<DeploymentModelContent> content)
      throws InvalidNumberOfContentException {
    setContent(content);
    return this;
  }

  public TechnologySpecificDeploymentModel options(List<String> options) {
    setOptions(options);
    return this;
  }

  public TechnologySpecificDeploymentModel embeddedDeploymentModels(
      List<TechnologySpecificDeploymentModel> embeddedDeploymentModels) {
    setEmbeddedDeploymentModels(embeddedDeploymentModels);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof TechnologySpecificDeploymentModel)) {
      return false;
    }
    TechnologySpecificDeploymentModel technologySpecificDeploymentModel =
        (TechnologySpecificDeploymentModel) o;
    return Objects.equals(id, technologySpecificDeploymentModel.id)
        && Objects.equals(
            transformationProcessId, technologySpecificDeploymentModel.transformationProcessId)
        && Objects.equals(technology, technologySpecificDeploymentModel.technology)
        && Objects.equals(commands, technologySpecificDeploymentModel.commands)
        && Objects.equals(content, technologySpecificDeploymentModel.content)
        && Objects.equals(
            embeddedDeploymentModels, technologySpecificDeploymentModel.embeddedDeploymentModels);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        id,
        transformationProcessId,
        technology,
        commands,
        content,
        options,
        embeddedDeploymentModels);
  }

  @Override
  public String toString() {
    return "{"
        + " id='"
        + getId()
        + "'"
        + ", transformationProcessId='"
        + getTransformationProcessId()
        + "'"
        + ", technology='"
        + getTechnology()
        + "'"
        + ", commands='"
        + getCommands()
        + "'"
        + ", content='"
        + getContent()
        + "'"
        + ", options='"
        + getOptions()
        + "'"
        + ", embeddedDeploymentModels='"
        + getEmbeddedDeploymentModels()
        + "'"
        + "}";
  }

  public void addDeploymentModelContent(DeploymentModelContent deploymentModelContent) {
    this.content.add(deploymentModelContent);
  }

  public void removeDeploymentModelContent(DeploymentModelContent deploymentModelContent)
      throws InvalidNumberOfContentException {
    if (content.size() == 1) {
      throw new InvalidNumberOfContentException(INVALIDNUMBEROFCONTENTEXCEPTIONMESSAGE);
    } else {
      this.content.remove(deploymentModelContent);
    }
  }

  public void addEmbeddedDeploymentModel(
      TechnologySpecificDeploymentModel embeddedDeploymentModel) {
    this.embeddedDeploymentModels.add(embeddedDeploymentModel);
  }

  public void addCommand(String command) {
    this.commands.add(command);
  }

  /**
   * Add a new embeddedDeploymentModel or update it if it already exists.
   *
   * @param newEmbeddedDeploymentModel
   * @return the index of the new embeddedDeploymentModel in the embeddedDeploymentModels List
   *     field.
   */
  public int addOrUpdateEmbeddedDeploymentModel(
      TechnologySpecificDeploymentModel newEmbeddedDeploymentModel) {
    for (TechnologySpecificDeploymentModel embeddedDeploymentModel :
        this.embeddedDeploymentModels) {
      if (newEmbeddedDeploymentModel.getId().equals(embeddedDeploymentModel.getId())) {
        int index = this.embeddedDeploymentModels.indexOf(embeddedDeploymentModel);
        this.embeddedDeploymentModels.set(index, newEmbeddedDeploymentModel);
        return index;
      }
    }
    addEmbeddedDeploymentModel(newEmbeddedDeploymentModel);
    return this.embeddedDeploymentModels.indexOf(newEmbeddedDeploymentModel);
  }
}
