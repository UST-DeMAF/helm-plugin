package ust.tad.helmplugin.analysistask;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class AnalysisTaskStartRequest {

  @JsonProperty("taskId")
  private UUID taskId;

  @JsonProperty("transformationProcessId")
  private UUID transformationProcessId;

  @JsonProperty("commands")
  private List<String> commands;

  @JsonProperty("options")
  private List<String> options;

  @JsonProperty("locations")
  private List<Location> locations;

  @JsonProperty("tadmEntities")
  private  List<TADMEntities> tadmEntities;

  public AnalysisTaskStartRequest() {}

  /**
   * Constructs an AnalysisTaskStartRequest object with the specified task ID, transformation
   * process ID, list of commands, and list of locations.
   *
   * @param taskId The unique identifier of the task.
   * @param transformationProcessId The unique identifier of the transformation process.
   * @param commands The list of commands to be executed.
   * @param options The list of options to be used.
   * @param locations The list of locations to be analyzed.
   * @param tadmEntities The list of tadmEntities to be analyzed.
   */
  public AnalysisTaskStartRequest(
          UUID taskId,
          UUID transformationProcessId,
          List<String> commands,
          List<String> options,
          List<Location> locations,
          List<TADMEntities> tadmEntities) {
    this.taskId = taskId;
    this.transformationProcessId = transformationProcessId;
    this.commands = commands;
    this.options = options;
    this.locations = locations;
    this.tadmEntities = tadmEntities;
  }

  public UUID getTaskId() {
    return this.taskId;
  }

  public void setTaskId(UUID taskId) {
    this.taskId = taskId;
  }

  public UUID getTransformationProcessId() {
    return this.transformationProcessId;
  }

  public void setTransformationProcessId(UUID transformationProcessId) {
    this.transformationProcessId = transformationProcessId;
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

  public List<TADMEntities> getTadmEntities() {
    return tadmEntities;
  }

  public void setTadmEntities(List<TADMEntities> tadmEntities) {
    this.tadmEntities = tadmEntities;
  }

  /**
   * Sets the task ID and returns the current AnalysisTaskStartRequest object.
   *
   * @param taskId The unique identifier of the task.
   * @return The current AnalysisTaskStartRequest object.
   */
  public AnalysisTaskStartRequest taskId(UUID taskId) {
    setTaskId(taskId);
    return this;
  }

  /**
   * Sets the transformation process ID and returns the current AnalysisTaskStartRequest object.
   *
   * @param transformationProcessId The unique identifier of the transformation process.
   * @return The current AnalysisTaskStartRequest object.
   */
  public AnalysisTaskStartRequest transformationProcessId(UUID transformationProcessId) {
    setTransformationProcessId(transformationProcessId);
    return this;
  }

  /**
   * Sets the list of commands and returns the current AnalysisTaskStartRequest object.
   *
   * @param commands The list of commands to be executed.
   * @return The current AnalysisTaskStartRequest object.
   */
  public AnalysisTaskStartRequest commands(List<String> commands) {
    setCommands(commands);
    return this;
  }

  /**
   * Sets the list of options and returns the current AnalysisTaskStartRequest object.
   *
   * @param options The list of options to be used.
   * @return The current AnalysisTaskStartRequest object.
   */
  public AnalysisTaskStartRequest options(List<String> options) {
    setOptions(options);
    return this;
  }

  /**
   * Sets the list of locations and returns the current AnalysisTaskStartRequest object.
   *
   * @param locations The list of locations to be analyzed.
   * @return The current AnalysisTaskStartRequest object.
   */
  public AnalysisTaskStartRequest locations(List<Location> locations) {
    setLocations(locations);
    return this;
  }

  /**
   * Sets the list of tadmEntities and returns the current AnalysisTaskStartRequest object.
   *
   * @param tadmEntities The list of tadm entities to be analyzed.
   * @return The current AnalysisTaskStartRequest object.
   */
  public AnalysisTaskStartRequest tadmEntities(List<TADMEntities> tadmEntities) {
    setTadmEntities(tadmEntities);
    return this;
  }

  /**
   * Compares this AnalysisTaskStartRequest object to another object.
   *
   * @param o The object to compare to.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof AnalysisTaskStartRequest)) {
      return false;
    }
    AnalysisTaskStartRequest analysisTaskStartRequest = (AnalysisTaskStartRequest) o;
    return Objects.equals(taskId, analysisTaskStartRequest.taskId)
            && Objects.equals(transformationProcessId, analysisTaskStartRequest.transformationProcessId)
            && Objects.equals(commands, analysisTaskStartRequest.commands)
            && Objects.equals(options, analysisTaskStartRequest.options)
            && Objects.equals(locations, analysisTaskStartRequest.locations)
            && Objects.equals(tadmEntities, analysisTaskStartRequest.tadmEntities);
  }

  /**
   * Generates a hash code for this AnalysisTaskStartRequest object.
   *
   * @return The hash code of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(taskId, transformationProcessId, commands, options, locations, tadmEntities);
  }

  /**
   * Returns a string representation of this AnalysisTaskStartRequest object.
   *
   * @return A string representation of this object.
   */
  @Override
  public String toString() {
    return "{"
            + " taskId='"
            + getTaskId()
            + "'"
            + ", transformationProcessId='"
            + getTransformationProcessId()
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
