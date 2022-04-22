package ust.tad.helmplugin.models.tadm;

import java.util.List;

public abstract class ModelElement extends ModelEntity{

    protected ModelElement() {
        super();
    }

    protected ModelElement(String name, String description, List<Property> properties, List<Operation> operations) {
        super(name, description, properties, operations);
    }

}
