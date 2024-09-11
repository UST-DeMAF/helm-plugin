package ust.tad.helmplugin.analysistask;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

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

    public AnalysisTaskStartRequest() {}

    public AnalysisTaskStartRequest(UUID taskId, UUID transformationProcessId, List<String> commands, List<String> options, List<Location> locations) {
        this.taskId = taskId;
        this.transformationProcessId = transformationProcessId;
        this.commands = commands;
        this.options = options;
        this.locations = locations;
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

    public List<String> getOptions() { return this.options; }

    public void setOptions(List<String> options) { this.options = options; }

    public List<Location> getLocations() {
        return this.locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public AnalysisTaskStartRequest taskId(UUID taskId) {
        setTaskId(taskId);
        return this;
    }

    public AnalysisTaskStartRequest transformationProcessId(UUID transformationProcessId) {
        setTransformationProcessId(transformationProcessId);
        return this;
    }

    public AnalysisTaskStartRequest commands(List<String> commands) {
        setCommands(commands);
        return this;
    }

    public AnalysisTaskStartRequest options(List<String> options) {
        setOptions(options);
        return this;
    }

    public AnalysisTaskStartRequest locations(List<Location> locations) {
        setLocations(locations);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AnalysisTaskStartRequest)) {
            return false;
        }
        AnalysisTaskStartRequest analysisTaskStartRequest = (AnalysisTaskStartRequest) o;
        return Objects.equals(taskId, analysisTaskStartRequest.taskId)
                && Objects.equals(transformationProcessId, analysisTaskStartRequest.transformationProcessId)
                && Objects.equals(commands, analysisTaskStartRequest.commands)
                && Objects.equals(options, analysisTaskStartRequest.options)
                && Objects.equals(locations, analysisTaskStartRequest.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(taskId, transformationProcessId, commands, options, locations);
    }

    @Override
    public String toString() {
        return "{" +
            " taskId='" + getTaskId() + "'" +
            ", transformationProcessId='" + getTransformationProcessId() + "'" +
            ", commands='" + getCommands() + "'" +
            ", options='" + getOptions() + "'" +
            ", locations='" + getLocations() + "'" +
            "}";
    }
}
