package ust.tad.helmplugin.models.tadm;

import java.util.Objects;

public class Property {

  private String key;

  private PropertyType type;

  private boolean required;

  private Object value;

  private Confidence confidence;

  private static final String INVALIDPROPERTYVALUEEXCEPTIONMESSAGE =
      "The value '%s' with type '%s' of the property does not match the given type %s";

  public Property() {}

  public Property(
      String key, PropertyType type, boolean required, Object value, Confidence confidence)
      throws InvalidPropertyValueException {
    if (isValueMatchingType(type, value)) {
      this.key = key;
      this.type = type;
      this.required = required;
      this.value = value;
      this.confidence = confidence;
    } else {
      throw new InvalidPropertyValueException(
          String.format(
              INVALIDPROPERTYVALUEEXCEPTIONMESSAGE,
              value.toString(),
              value.getClass().toString(),
              type.toString()));
    }
  }

  public String getKey() {
    return this.key;
  }

  public void setKey(String key) {
    this.key = key;
  }

  public PropertyType getType() {
    return this.type;
  }

  public void setType(PropertyType type) throws InvalidPropertyValueException {
    if (isValueMatchingType(type, this.value)) {
      this.type = type;
    } else {
      throw new InvalidPropertyValueException(
          String.format(
              INVALIDPROPERTYVALUEEXCEPTIONMESSAGE,
              this.value.toString(),
              value.getClass().toString(),
              type.toString()));
    }
  }

  public boolean isRequired() {
    return this.required;
  }

  public boolean getRequired() {
    return this.required;
  }

  public void setRequired(boolean required) {
    this.required = required;
  }

  public Object getValue() {
    return this.value;
  }

  public void setValue(Object value) throws InvalidPropertyValueException {
    if (isValueMatchingType(this.type, value)) {
      this.value = value;
    } else {
      throw new InvalidPropertyValueException(
          String.format(
              INVALIDPROPERTYVALUEEXCEPTIONMESSAGE,
              value.toString(),
              value.getClass().toString(),
              this.type.toString()));
    }
  }

  public Confidence getConfidence() {
    return this.confidence;
  }

  public void setConfidence(Confidence confidence) {
    this.confidence = confidence;
  }

  public Property key(String key) {
    setKey(key);
    return this;
  }

  public Property type(PropertyType type) throws InvalidPropertyValueException {
    setType(type);
    return this;
  }

  public Property required(boolean required) {
    setRequired(required);
    return this;
  }

  public Property value(Object value) throws InvalidPropertyValueException {
    setValue(value);
    return this;
  }

  public Property confidence(Confidence confidence) {
    setConfidence(confidence);
    return this;
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Property)) {
      return false;
    }
    Property property = (Property) o;
    return Objects.equals(key, property.key)
        && Objects.equals(type, property.type)
        && required == property.required
        && Objects.equals(value, property.value)
        && Objects.equals(confidence, property.confidence);
  }

  @Override
  public int hashCode() {
    return Objects.hash(key, type, required, value, confidence);
  }

  @Override
  public String toString() {
    return "{"
        + " key='"
        + getKey()
        + "'"
        + ", type='"
        + getType()
        + "'"
        + ", required='"
        + isRequired()
        + "'"
        + ", value='"
        + getValue()
        + "'"
        + ", confidence='"
        + getConfidence()
        + "'"
        + "}";
  }

  private boolean isValueMatchingType(PropertyType type, Object value) {
    if (value == null || type == null) {
      return true;
    } else {
      switch (type) {
        case BOOLEAN:
          return value.getClass() == Boolean.class;
        case DOUBLE:
          return value.getClass() == Double.class || value.getClass() == Float.class;
        case INTEGER:
          return value.getClass() == Integer.class || value.getClass() == Long.class;
        case STRING:
          return value.getClass() == String.class;
        default:
          return false;
      }
    }
  }
}
