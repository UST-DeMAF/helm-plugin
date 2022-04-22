package ust.tad.helmplugin.models.tadm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public abstract class ModelEntity {
    
    private String id = UUID.randomUUID().toString();

    private String name;

    private String description;

    private List<Property> properties = new ArrayList<>();

    private List<Operation> operations = new ArrayList<>();
    

    protected ModelEntity() {
    }

    protected ModelEntity(String name, String description, List<Property> properties, List<Operation> operations) {
        this.name = name;
        this.description = description;
        this.properties = properties;
        this.operations = operations;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }    

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Operation> getOperations() {
        return this.operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public ModelEntity id(String id) {
        setId(id);
        return this;
    }
    
    public ModelEntity name(String name) {
        setName(name);
        return this;
    }

    public ModelEntity description(String description) {
        setDescription(description);
        return this;
    }

    public ModelEntity properties(List<Property> properties) {
        setProperties(properties);
        return this;
    }

    public ModelEntity operations(List<Operation> operations) {
        setOperations(operations);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof ModelEntity)) {
            return false;
        }
        ModelEntity modelEntity = (ModelEntity) o;
        return Objects.equals(id, modelEntity.id) && Objects.equals(name, modelEntity.name) && Objects.equals(description, modelEntity.description) && Objects.equals(properties, modelEntity.properties) && Objects.equals(operations, modelEntity.operations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, properties, operations);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            "}";
    }

    
}
