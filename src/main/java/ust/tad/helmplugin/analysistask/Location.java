package ust.tad.helmplugin.analysistask;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.net.URL;
import java.util.Objects;
import java.util.UUID;

public class Location {

  @JsonProperty("id")
  private UUID id;

  @JsonProperty("url")
  private URL url;

  @JsonProperty("startLineNumber")
  private int startLineNumber;

  @JsonProperty("endLineNumber")
  private int endLineNumber;

  public Location() {}

  /**
   * Constructs a Location object with the specified URL, start line number, and end line number.
   *
   * @param url The URL of the location.
   * @param startLineNumber The start line number.
   * @param endLineNumber The end line number.
   */
  public Location(URL url, int startLineNumber, int endLineNumber) {
    this.url = url;
    this.startLineNumber = startLineNumber;
    this.endLineNumber = endLineNumber;
  }

  public UUID getId() {
    return this.id;
  }

  public URL getUrl() {
    return this.url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public int getStartLineNumber() {
    return this.startLineNumber;
  }

  public void setStartLineNumber(int startLineNumber) {
    this.startLineNumber = startLineNumber;
  }

  public int getEndLineNumber() {
    return this.endLineNumber;
  }

  public void setEndLineNumber(int endLineNumber) {
    this.endLineNumber = endLineNumber;
  }

  /**
   * Sets the url and returns the current Location object.
   *
   * @param url The url of the location.
   * @return The current Location object.
   */
  public Location url(URL url) {
    setUrl(url);
    return this;
  }

  /**
   * Sets the start line number and returns the current Location object.
   *
   * @param startLineNumber The start line number.
   * @return The current Location object.
   */
  public Location startLineNumber(int startLineNumber) {
    setStartLineNumber(startLineNumber);
    return this;
  }

  /**
   * Sets the end line number and returns the current Location object.
   *
   * @param endLineNumber The end line number.
   * @return The current Location object.
   */
  public Location endLineNumber(int endLineNumber) {
    setEndLineNumber(endLineNumber);
    return this;
  }

  /**
   * Compares this Location object to another object.
   *
   * @param o The object to compare to.
   * @return {@code true} if the objects are equal, {@code false} otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == this) return true;
    if (!(o instanceof Location)) {
      return false;
    }
    Location location = (Location) o;
    return Objects.equals(id, location.id)
            && Objects.equals(url, location.url)
            && startLineNumber == location.startLineNumber
            && endLineNumber == location.endLineNumber;
  }

  /**
   * Generates a hash code for this Location object.
   *
   * @return The hash code of this object.
   */
  @Override
  public int hashCode() {
    return Objects.hash(id, url, startLineNumber, endLineNumber);
  }

  /**
   * Returns a string representation of this Location object.
   *
   * @return A string representation of this object.
   */
  @Override
  public String toString() {
    return "{"
            + " id='"
            + getId()
            + "'"
            + ", url='"
            + getUrl()
            + "'"
            + ", startLineNumber='"
            + getStartLineNumber()
            + "'"
            + ", endLineNumber='"
            + getEndLineNumber()
            + "'"
            + "}";
  }
}
