package ust.tad.helmplugin.models.tadm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Component extends ModelElement {

  private ComponentType type;

  private List<Artifact> artifacts = new ArrayList<>();

  private Confidence confidence;

  public Component() {
    super();
  }

  public Component(
      String name,
      String description,
      List<Property> properties,
      List<Operation> operations,
      ComponentType type,
      List<Artifact> artifacts,
      Confidence confidence) {
    super(name, description, properties, operations);
    this.type = type;
    this.artifacts = artifacts;
    this.confidence = confidence;
  }

  public ComponentType getType() {
    return this.type;
  }

  public void setType(ComponentType type) {
    this.type = type;
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

  public Component type(ComponentType type) {
    setType(type);
    return this;
  }

  public Component artifacts(List<Artifact> artifacts) {
    setArtifacts(artifacts);
    return this;
  }

  public Component confidence(Confidence confidence) {
    setConfidence(confidence);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Component)) {
      return false;
    }
    Component component = (Component) o;
    return Objects.equals(getId(), component.getId())
        && Objects.equals(getName(), component.getName())
        && Objects.equals(getDescription(), component.getDescription())
        && Objects.equals(getProperties(), component.getProperties())
        && Objects.equals(getOperations(), component.getOperations())
        && Objects.equals(type, component.type)
        && Objects.equals(artifacts, component.artifacts)
        && Objects.equals(confidence, component.confidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(),
        getName(),
        getDescription(),
        getProperties(),
        getOperations(),
        type,
        artifacts,
        confidence);
  }

  @Override
  public String toString() {
    return "{"
        + " id='"
        + getId()
        + "'"
        + ", name='"
        + getName()
        + "'"
        + ", type='"
        + getType()
        + "'"
        + ", description='"
        + getDescription()
        + "'"
        + ", properties='"
        + getProperties()
        + "'"
        + ", operations='"
        + getOperations()
        + "'"
        + ", artifacts='"
        + getArtifacts()
        + "'"
        + ", confidence='"
        + getConfidence()
        + "'"
        + "}";
  }

  public Boolean isConfirmed() {
    return this.getConfidence().equals(Confidence.CONFIRMED);
  }
}
