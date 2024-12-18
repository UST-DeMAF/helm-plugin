package ust.tad.helmplugin.analysistask;

import java.util.*;

public class EmbeddedDeploymentModelAnalysisRequest {

  private UUID parentTaskId;

  private UUID transformationProcessId;

  private String technology;

  private List<String> commands = new ArrayList<>();

  private List<String> options = new ArrayList<>();

  private List<Location> locations = new ArrayList<>();

  private Map<String, List<String>> tadmEntities = new HashMap<>();

  public EmbeddedDeploymentModelAnalysisRequest() {}

  /**
   * Constructs an EmbeddedDeploymentModelAnalysisRequest object with the specified parent task ID,
   * transformation process ID, technology, list of commands, list of locations, and map of tadm
   * entities.
   *
   * @param parentTaskId The unique identifier of the parent task.
   * @param transformationProcessId The unique identifier of the transformation process.
   * @param technology The technology used in the deployment model.
   * @param commands The list of commands to be executed.
   * @param locations The list of locations to be analyzed.
   * @param tadmEntities The map of tadm entities to be analyzed.
   */
  public EmbeddedDeploymentModelAnalysisRequest(
          UUID parentTaskId,
          UUID transformationProcessId,
          String technology,
          List<String> commands,
          List<String> options,
          List<Location> locations,
          Map<String, List<String>> tadmEntities) {
    this.parentTaskId = parentTaskId;
    this.transformationProcessId = transformationProcessId;
    this.technology = technology;
    this.commands = commands;
    this.options = options;
    this.locations = locations;
    this.tadmEntities = tadmEntities;
  }

  public UUID getParentTaskId() {
    return this.parentTaskId;
  }

  public void setParentTaskId(UUID parentTaskId) {
    this.parentTaskId = parentTaskId;
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

  public List<String> getOptions() {
    return this.options;
  }

  public void setOptions(List<String> options) {
    this.options = options;
  }

  public List<Location> getLocations() {
    return this.locations;
  }

  public void setLocations(List<Location> locations) {
    this.locations = locations;
  }

  public Map<String, List<String>> getTadmEntities() {
    return tadmEntities;
  }

  public void setTadmEntities(Map<String, List<String>> tadmEntities) {
    this.tadmEntities = tadmEntities;
  }

  /**
   * Sets the parent task ID and returns the current EmbeddedDeploymentModelAnalysisRequest object.
   *
   * @param parentTaskId The unique identifier of the parent task.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest parentTaskId(UUID parentTaskId) {
    setParentTaskId(parentTaskId);
    return this;
  }

  /**
   * Sets the transformation process ID and returns the current
   * EmbeddedDeploymentModelAnalysisRequest object.
   *
   * @param transformationProcessId The unique identifier of the transformation process.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest transformationProcessId(
          UUID transformationProcessId) {
    setTransformationProcessId(transformationProcessId);
    return this;
  }

  /**
   * Sets the technology and returns the current EmbeddedDeploymentModelAnalysisRequest object.
   *
   * @param technology The technology used in the deployment model.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest technology(String technology) {
    setTechnology(technology);
    return this;
  }

  /**
   * Sets the list of commands and returns the current EmbeddedDeploymentModelAnalysisRequest
   * object.
   *
   * @param commands The list of commands to be executed.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest commands(List<String> commands) {
    setCommands(commands);
    return this;
  }

  /**
   * Sets the list of options and returns the current EmbeddedDeploymentModelAnalysisRequest object.
   *
   * @param options The list of options to be used.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest options(List<String> options) {
    setOptions(options);
    return this;
  }

  /**
   * Sets the list of locations and returns the current EmbeddedDeploymentModelAnalysisRequest
   * object.
   *
   * @param locations The list of locations to be analyzed.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest locations(List<Location> locations) {
    setLocations(locations);
    return this;
  }

  /**
   * Sets the map of tadm entities and returns the current EmbeddedDeploymentModelAnalysisRequest
   * object.
   *
   * @param tadmEntities The map of tadm entities to be analyzed.
   * @return The current EmbeddedDeploymentModelAnalysisRequest object.
   */
  public EmbeddedDeploymentModelAnalysisRequest tadmEntities(Map<String, List<String>> tadmEntities) {
    setTadmEntities(tadmEntities);
    return this;
  }

  /**
   * Compares this EmbeddedDeploymentModelAnalysisRequest object to another object.
   *
   * @param o The object to compare to.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof EmbeddedDeploymentModelAnalysisRequest)) {
      return false;
    }
    EmbeddedDeploymentModelAnalysisRequest embeddedDeploymentModelAnalysisRequest =
            (EmbeddedDeploymentModelAnalysisRequest) o;
    return Objects.equals(parentTaskId, embeddedDeploymentModelAnalysisRequest.parentTaskId)
            && Objects.equals(
            transformationProcessId, embeddedDeploymentModelAnalysisRequest.transformationProcessId)
            && Objects.equals(technology, embeddedDeploymentModelAnalysisRequest.technology)
            && Objects.equals(commands, embeddedDeploymentModelAnalysisRequest.commands)
            && Objects.equals(options, embeddedDeploymentModelAnalysisRequest.options)
            && Objects.equals(locations, embeddedDeploymentModelAnalysisRequest.locations)
            && Objects.equals(tadmEntities, embeddedDeploymentModelAnalysisRequest.tadmEntities);
  }

  /**
   * Returns the hash code of the EmbeddedDeploymentModelAnalysisRequest object.
   *
   * @return The hash code of the EmbeddedDeploymentModelAnalysisRequest object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(
            parentTaskId, transformationProcessId, technology, commands, options, locations, tadmEntities);
  }

  /**
   * Returns a string representation of the EmbeddedDeploymentModelAnalysisRequest object.
   *
   * @return A string representation of the EmbeddedDeploymentModelAnalysisRequest object.
   */
  @Override
  public String toString() {
    return "{"
            + " parentTaskId='"
            + getParentTaskId()
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
            + ", options='"
            + getOptions()
            + "'"
            + ", locations='"
            + getLocations()
            + "'"
            + ", tadmEntities='"
            + getTadmEntities()
            + "'"
            + "}";
  }
}
