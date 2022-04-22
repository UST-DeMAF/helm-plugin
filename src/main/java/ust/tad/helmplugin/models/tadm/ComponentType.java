package ust.tad.helmplugin.models.tadm;

import java.util.List;
import java.util.Objects;

public class ComponentType extends ModelElementType{

    private ComponentType parentType;

    public ComponentType() {
        super();
    }

    public ComponentType(String name, String description, List<Property> properties, List<Operation> operations, ComponentType parentType) {
        super(name, description, properties, operations);
        this.parentType = parentType;
    }

    public ComponentType getParentType() {
        return this.parentType;
    }

    public void setParentType(ComponentType parentType) {
        this.parentType = parentType;
    }

    public ComponentType parentType(ComponentType parentType) {
        setParentType(parentType);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ComponentType)) {
            return false;
        }
        ComponentType componentType = (ComponentType) o;
        return Objects.equals(getId(), componentType.getId())
            && Objects.equals(getName(), componentType.getName()) 
            && Objects.equals(getDescription(), componentType.getDescription()) 
            && Objects.equals(getProperties(), componentType.getProperties()) 
            && Objects.equals(getOperations(), componentType.getOperations()) 
            && Objects.equals(parentType, componentType.parentType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getProperties(), getOperations(), parentType);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", parentType='" + getParentType() + "'" +
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            "}";
    }


}
