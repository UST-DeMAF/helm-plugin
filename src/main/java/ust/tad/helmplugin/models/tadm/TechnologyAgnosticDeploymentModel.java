package ust.tad.helmplugin.models.tadm;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TechnologyAgnosticDeploymentModel {
    
    private UUID id = UUID.randomUUID();

    private UUID transformationProcessId;

    private List<Property> properties = new ArrayList<>();

    private List<Component> components = new ArrayList<>();

    private List<Relation> relations = new ArrayList<>();

    private List<ComponentType> componentTypes = new ArrayList<>();

    private List<RelationType> relationTypes = new ArrayList<>();


    public TechnologyAgnosticDeploymentModel() {
    }

    public TechnologyAgnosticDeploymentModel(UUID transformationProcessId, List<Property> properties, List<Component> components, List<Relation> relations, List<ComponentType> componentTypes, List<RelationType> relationTypes) {
        this.transformationProcessId = transformationProcessId;
        this.properties = properties;
        this.components = components;
        this.relations = relations;
        this.componentTypes = componentTypes;
        this.relationTypes = relationTypes;
    }

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getTransformationProcessId() {
        return this.transformationProcessId;
    }

    public void setTransformationProcessId(UUID transformationProcessId) {
        this.transformationProcessId = transformationProcessId;
    }

    public List<Property> getProperties() {
        return this.properties;
    }

    public void setProperties(List<Property> properties) {
        this.properties = properties;
    }

    public List<Component> getComponents() {
        return this.components;
    }

    public void setComponents(List<Component> components) {
        this.components = components;
    }

    public List<Relation> getRelations() {
        return this.relations;
    }

    public void setRelations(List<Relation> relations) {
        this.relations = relations;
    }

    public List<ComponentType> getComponentTypes() {
        return this.componentTypes;
    }

    public void setComponentTypes(List<ComponentType> componentTypes) {
        this.componentTypes = componentTypes;
    }

    public List<RelationType> getRelationTypes() {
        return this.relationTypes;
    }

    public void setRelationTypes(List<RelationType> relationTypes) {
        this.relationTypes = relationTypes;
    }

    public TechnologyAgnosticDeploymentModel id(UUID id) {
        setId(id);
        return this;
    }

    public TechnologyAgnosticDeploymentModel transformationProcessId(UUID transformationProcessId) {
        setTransformationProcessId(transformationProcessId);
        return this;
    }

    public TechnologyAgnosticDeploymentModel properties(List<Property> properties) {
        setProperties(properties);
        return this;
    }

    public TechnologyAgnosticDeploymentModel components(List<Component> components) {
        setComponents(components);
        return this;
    }

    public TechnologyAgnosticDeploymentModel relations(List<Relation> relations) {
        setRelations(relations);
        return this;
    }

    public TechnologyAgnosticDeploymentModel componentTypes(List<ComponentType> componentTypes) {
        setComponentTypes(componentTypes);
        return this;
    }

    public TechnologyAgnosticDeploymentModel relationTypes(List<RelationType> relationTypes) {
        setRelationTypes(relationTypes);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof TechnologyAgnosticDeploymentModel)) {
            return false;
        }
        TechnologyAgnosticDeploymentModel technologyAgnosticDeploymentModel = (TechnologyAgnosticDeploymentModel) o;
        return Objects.equals(id, technologyAgnosticDeploymentModel.id) && Objects.equals(transformationProcessId, technologyAgnosticDeploymentModel.transformationProcessId) && Objects.equals(properties, technologyAgnosticDeploymentModel.properties) && Objects.equals(components, technologyAgnosticDeploymentModel.components) && Objects.equals(relations, technologyAgnosticDeploymentModel.relations) && Objects.equals(componentTypes, technologyAgnosticDeploymentModel.componentTypes) && Objects.equals(relationTypes, technologyAgnosticDeploymentModel.relationTypes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, transformationProcessId, properties, components, relations, componentTypes, relationTypes);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", transformationProcessId='" + getTransformationProcessId() + "'" +
            ", properties='" + getProperties() + "'" +
            ", components='" + getComponents() + "'" +
            ", relations='" + getRelations() + "'" +
            ", componentTypes='" + getComponentTypes() + "'" +
            ", relationTypes='" + getRelationTypes() + "'" +
            "}";
    }
    
    
}
