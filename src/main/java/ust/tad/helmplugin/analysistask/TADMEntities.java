package ust.tad.helmplugin.analysistask;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class TADMEntities {

    @JsonProperty("id")
    private UUID id;

    @JsonProperty("tadmEntitiesType")
    private String tadmEntitiesType;

    @JsonProperty("tadmEntityIds")
    private List<String> tadmEntityIds = new ArrayList<>();

    public TADMEntities() {
    }

    public TADMEntities(String tadmEntitiesType, List<String> tadmEntityIds) {
        this.tadmEntitiesType = tadmEntitiesType;
        this.tadmEntityIds = tadmEntityIds;
    }

    public UUID getId() {
        return id;
    }

    public String getTadmEntitiesType() {
        return tadmEntitiesType;
    }

    public void setTadmEntitiesType(String tadmEntitiesType) {
        this.tadmEntitiesType = tadmEntitiesType;
    }

    public List<String> getTadmEntityIds() {
        return tadmEntityIds;
    }

    public void setTadmEntityIds(List<String> tadmEntityIds) {
        this.tadmEntityIds = tadmEntityIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TADMEntities that = (TADMEntities) o;
        return Objects.equals(id, that.id) && Objects.equals(tadmEntitiesType, that.tadmEntitiesType) && Objects.equals(tadmEntityIds, that.tadmEntityIds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, tadmEntitiesType, tadmEntityIds);
    }

    @Override
    public String toString() {
        return "TADMEntities{" +
                "id=" + id +
                ", entityType='" + tadmEntitiesType + '\'' +
                ", entities=" + tadmEntityIds +
                '}';
    }
}
