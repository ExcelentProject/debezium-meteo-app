package io.excellent.project.dmt;

public class JsonTableAttribute {
    String name;
    String dataType;
    String value;

    public JsonTableAttribute(String name, String dataType, String value) {
        this.name = name;
        this.dataType = dataType;
        this.value = value;
    }

    public JsonTableAttribute() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JsonTableAttribute that = (JsonTableAttribute) o;

        if (!getName().equals(that.getName())) return false;
        if (!getDataType().equals(that.getDataType())) return false;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getDataType().hashCode();
        result = 31 * result + getValue().hashCode();
        return result;
    }
}
