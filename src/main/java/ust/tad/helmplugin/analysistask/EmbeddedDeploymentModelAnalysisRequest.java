package ust.tad.helmplugin.analysistask;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class EmbeddedDeploymentModelAnalysisRequest {
   
    private UUID parentTaskId;

    private UUID transformationProcessId;
    
    private String technology;

    private List<String> commands = new ArrayList<>();

    private List<String> options = new ArrayList<>();

    private List<Location> locations = new ArrayList<>();

    public EmbeddedDeploymentModelAnalysisRequest() {
    }

    public EmbeddedDeploymentModelAnalysisRequest(UUID parentTaskId, UUID transformationProcessId, String technology, List<String> commands,  List<String> options, List<Location> locations) {
        this.parentTaskId = parentTaskId;
        this.transformationProcessId = transformationProcessId;
        this.technology = technology;
        this.commands = commands;
        this.options = options;
        this.locations = locations;
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

    public List<String> getOptions() { return this.options; }

    public void setOptions(List<String> options) { this.options = options; }

    public List<Location> getLocations() {
        return this.locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public EmbeddedDeploymentModelAnalysisRequest parentTaskId(UUID parentTaskId) {
        setParentTaskId(parentTaskId);
        return this;
    }

    public EmbeddedDeploymentModelAnalysisRequest transformationProcessId(UUID transformationProcessId) {
        setTransformationProcessId(transformationProcessId);
        return this;
    }

    public EmbeddedDeploymentModelAnalysisRequest technology(String technology) {
        setTechnology(technology);
        return this;
    }

    public EmbeddedDeploymentModelAnalysisRequest commands(List<String> commands) {
        setCommands(commands);
        return this;
    }

    public EmbeddedDeploymentModelAnalysisRequest options(List<String> options) {
        setOptions(options);
        return this;
    }

    public EmbeddedDeploymentModelAnalysisRequest locations(List<Location> locations) {
        setLocations(locations);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmbeddedDeploymentModelAnalysisRequest)) {
            return false;
        }
        EmbeddedDeploymentModelAnalysisRequest embeddedDeploymentModelAnalysisRequest = (EmbeddedDeploymentModelAnalysisRequest) o;
        return Objects.equals(parentTaskId, embeddedDeploymentModelAnalysisRequest.parentTaskId)
                && Objects.equals(transformationProcessId, embeddedDeploymentModelAnalysisRequest.transformationProcessId)
                && Objects.equals(technology, embeddedDeploymentModelAnalysisRequest.technology)
                && Objects.equals(commands, embeddedDeploymentModelAnalysisRequest.commands)
                && Objects.equals(options, embeddedDeploymentModelAnalysisRequest.options)
                && Objects.equals(locations, embeddedDeploymentModelAnalysisRequest.locations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(parentTaskId, transformationProcessId, technology, commands, options, locations);
    }

    @Override
    public String toString() {
        return "{" +
            " parentTaskId='" + getParentTaskId() + "'" +
            ", transformationProcessId='" + getTransformationProcessId() + "'" +
            ", technology='" + getTechnology() + "'" +
            ", commands='" + getCommands() + "'" +
            ", options='" + getOptions() + "'" +
            ", locations='" + getLocations() + "'" +
            "}";
    }
}
