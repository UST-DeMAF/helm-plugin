package ust.tad.helmplugin.models.tadm;

import java.util.List;
import java.util.Objects;

public class Relation extends ModelElement{

    private RelationType type;

    private Component source;

    private Component target;

    private Confidence confidence;


    private static final String INVALIDRELATIONEXCEPTIONMESSAGE = "The source and the target of a relation must not be the same component.";


    public Relation() {
        super();
    }

    public Relation(String name, String description, List<Property> properties, List<Operation> operations, RelationType type, Component source, Component target, Confidence confidence) throws InvalidRelationException {
        super(name, description, properties, operations);
        if(Boolean.TRUE.equals(areSourceAndTargetEqual(source, target))) {
            throw new InvalidRelationException(INVALIDRELATIONEXCEPTIONMESSAGE);
        } else {
            this.type = type;
            this.source = source;
            this.target = target;
            this.confidence = confidence;
        }
    }
    
    public RelationType getType() {
        return this.type;
    }

    public void setType(RelationType type) {
        this.type = type;
    }

    public Component getSource() {
        return this.source;
    }

    public void setSource(Component source) throws InvalidRelationException {
        if(Boolean.TRUE.equals(areSourceAndTargetEqual(source, this.target))) {
            throw new InvalidRelationException(INVALIDRELATIONEXCEPTIONMESSAGE);
        } else {
            this.source = source;
        }
    }

    public Component getTarget() {
        return this.target;
    }

    public void setTarget(Component target) throws InvalidRelationException {
        if(Boolean.TRUE.equals(areSourceAndTargetEqual(this.source, target))) {
            throw new InvalidRelationException(INVALIDRELATIONEXCEPTIONMESSAGE);
        } else {
            this.target = target;
        }
    }
    
    public Confidence getConfidence() {
        return this.confidence;
    }

    public void setConfidence(Confidence confidence) {
        this.confidence = confidence;
    }

    public Relation type(RelationType type) {
        setType(type);
        return this;
    }

    public Relation source(Component source) throws InvalidRelationException {
        setSource(source);
        return this;
    }

    public Relation target(Component target) throws InvalidRelationException {
        setTarget(target);
        return this;
    }
    
    public Relation confidence(Confidence confidence) {
        setConfidence(confidence);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Relation)) {
            return false;
        }
        Relation relation = (Relation) o;
        return Objects.equals(getId(), relation.getId())
            && Objects.equals(getName(), relation.getName()) 
            && Objects.equals(getDescription(), relation.getDescription()) 
            && Objects.equals(getProperties(), relation.getProperties()) 
            && Objects.equals(getOperations(), relation.getOperations())
            && Objects.equals(type, relation.type)
            && Objects.equals(source, relation.source) 
            && Objects.equals(target, relation.target)
            && Objects.equals(confidence, relation.confidence);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getProperties(), getOperations(), type, source, target, confidence);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", type='" + getType() + "'" +            
            ", description='" + getDescription() + "'" +
            ", properties='" + getProperties() + "'" +
            ", operations='" + getOperations() + "'" +
            ", source='" + getSource() + "'" +
            ", target='" + getTarget() + "'" +
            ", confidence='" + getConfidence() + "'" +
            "}";
    }

    private Boolean areSourceAndTargetEqual(Component source, Component target) {
        return source.equals(target);
    }

    public Boolean isConfirmed() {
        return this.getConfidence().equals(Confidence.CONFIRMED);
    }

    
}
