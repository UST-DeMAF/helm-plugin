package ust.tad.helmplugin.models.tadm;

import java.util.List;
import java.util.Objects;

public class RelationType extends ModelElementType {

  private RelationType parentType;

  public RelationType() {
    super();
  }

  public RelationType(
      String name,
      String description,
      List<Property> properties,
      List<Operation> operations,
      RelationType parentType) {
    super(name, description, properties, operations);
    this.parentType = parentType;
  }

  public RelationType getParentType() {
    return this.parentType;
  }

  public void setParentType(RelationType parentType) {
    this.parentType = parentType;
  }

  public RelationType parentType(RelationType parentType) {
    setParentType(parentType);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof RelationType)) {
      return false;
    }
    RelationType relationType = (RelationType) o;
    return Objects.equals(getId(), relationType.getId())
        && Objects.equals(getName(), relationType.getName())
        && Objects.equals(getDescription(), relationType.getDescription())
        && Objects.equals(getProperties(), relationType.getProperties())
        && Objects.equals(getOperations(), relationType.getOperations())
        && Objects.equals(parentType, relationType.parentType);
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        getId(), getName(), getDescription(), getProperties(), getOperations(), parentType);
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
        + ", parentType='"
        + getParentType()
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
        + "}";
  }
}
