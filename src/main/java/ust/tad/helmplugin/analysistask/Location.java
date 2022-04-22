package ust.tad.helmplugin.analysistask;

import java.net.URL;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Location {

    @JsonProperty("id")
    private UUID id;
    
    @JsonProperty("url")
    private URL url;

    @JsonProperty("startLineNumber")
    private int startLineNumber;
    
    @JsonProperty("endLineNumber")
    private int endLineNumber;    


    public Location() {
    }

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

    public Location url(URL url) {
        setUrl(url);
        return this;
    }

    public Location startLineNumber(int startLineNumber) {
        setStartLineNumber(startLineNumber);
        return this;
    }

    public Location endLineNumber(int endLineNumber) {
        setEndLineNumber(endLineNumber);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Location)) {
            return false;
        }
        Location location = (Location) o;
        return Objects.equals(id, location.id) && Objects.equals(url, location.url) && startLineNumber == location.startLineNumber && endLineNumber == location.endLineNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, url, startLineNumber, endLineNumber);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", url='" + getUrl() + "'" +
            ", startLineNumber='" + getStartLineNumber() + "'" +
            ", endLineNumber='" + getEndLineNumber() + "'" +
            "}";
    }
    
}
