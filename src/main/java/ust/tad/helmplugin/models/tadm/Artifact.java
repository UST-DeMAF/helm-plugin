package ust.tad.helmplugin.models.tadm;

import java.net.URI;
import java.util.Objects;

public class Artifact {

  private String name;

  private String type;

  private URI fileURI;

  private Confidence confidence;

  public Artifact() {}

  public Artifact(String name, String type, URI fileURI, Confidence confidence) {
    this.name = name;
    this.type = type;
    this.fileURI = fileURI;
    this.confidence = confidence;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getType() {
    return this.type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public URI getFileURI() {
    return this.fileURI;
  }

  public void setFileURI(URI fileURI) {
    this.fileURI = fileURI;
  }

  public Confidence getConfidence() {
    return this.confidence;
  }

  public void setConfidence(Confidence confidence) {
    this.confidence = confidence;
  }

  public Artifact name(String name) {
    setName(name);
    return this;
  }

  public Artifact type(String type) {
    setType(type);
    return this;
  }

  public Artifact fileURI(URI fileURI) {
    setFileURI(fileURI);
    return this;
  }

  public Artifact confidence(Confidence confidence) {
    setConfidence(confidence);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Artifact)) {
      return false;
    }
    Artifact artifact = (Artifact) o;
    return Objects.equals(name, artifact.name)
        && Objects.equals(type, artifact.type)
        && Objects.equals(fileURI, artifact.fileURI)
        && Objects.equals(confidence, artifact.confidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(name, type, fileURI, confidence);
  }

  @Override
  public String toString() {
    return "{"
        + " name='"
        + getName()
        + "'"
        + ", type='"
        + getType()
        + "'"
        + ", fileURI='"
        + getFileURI()
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
