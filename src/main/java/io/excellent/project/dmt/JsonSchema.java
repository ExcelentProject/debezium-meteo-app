package io.excellent.project.dmt;

import java.util.List;

public class JsonSchema {
    List<String> databases;
    String table;
    String primary;
    List<JsonTableAttribute> payload;

    public JsonSchema(List<String> databases, String table, String primary, List<JsonTableAttribute> payload) {
        this.databases = databases;
        this.table = table;
        this.primary = primary;
        this.payload = payload;
    }

    public JsonSchema() {
    }

    public List<String> getDatabases() {
        return databases;
    }

    public void setDatabases(List<String> databases) {
        this.databases = databases;
    }

    public String getTable() {
        return table;
    }

    public void setTable(String table) {
        this.table = table;
    }

    public String getPrimary() {
        return primary;
    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public List<JsonTableAttribute> getPayload() {
        return payload;
    }

    public void setPayload(List<JsonTableAttribute> payload) {
        this.payload = payload;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonSchema that = (JsonSchema) o;

        if (!getDatabases().equals(that.getDatabases())) return false;
        if (!getTable().equals(that.getTable())) return false;
        if (!getPrimary().equals(that.getPrimary())) return false;
        return getPayload().equals(that.getPayload());
    }

    @Override
    public int hashCode() {
        int result = getDatabases().hashCode();
        result = 31 * result + getTable().hashCode();
        result = 31 * result + getPrimary().hashCode();
        result = 31 * result + getPayload().hashCode();
        return result;
    }
}
