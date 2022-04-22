package ust.tad.helmplugin.models.tadm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Operation {

    private String name;

    private List<Artifact> artifacts = new ArrayList<>();

    private Confidence confidence;


    public Operation() {
    }

    public Operation(String name, List<Artifact> artifacts, Confidence confidence) {
        this.name = name;
        this.artifacts = artifacts;
        this.confidence = confidence;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Artifact> getArtifacts() {
        return this.artifacts;
    }

    public void setArtifacts(List<Artifact> artifacts) {
        this.artifacts = artifacts;
    }

    public Confidence getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public Operation name(String name) {
        setName(name);
        return this;
    }

    public Operation artifacts(List<Artifact> artifacts) {
        setArtifacts(artifacts);
        return this;
    }

    public Operation confidence(Confidence confidence) {
        setConfidence(confidence);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Operation)) {
            return false;
        }
        Operation operation = (Operation) o;
        return Objects.equals(name, operation.name) && Objects.equals(artifacts, operation.artifacts) && Objects.equals(confidence, operation.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, artifacts, confidence);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", artifacts='" + getArtifacts() + "'" +
            ", confidence='" + getConfidence() + "'" +
            "}";
    }   

    public Boolean isConfirmed() {
        return this.getConfidence().equals(Confidence.CONFIRMED);
    }

    
}
