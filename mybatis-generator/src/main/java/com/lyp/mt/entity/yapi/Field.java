package com.lyp.mt.entity.yapi;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 20:48
 */
public class Field {
    private String type;
    private String description;
    private Object mock;

    public Field() {
    }

    public Field(String type, String description) {
        this.type = type;
        this.description = description;
    }

    public Field(String type, String description, Object mock) {
        this.type = type;
        this.description = description;
        this.mock = mock;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Object getMock() {
        return mock;
    }

    public void setMock(Object mock) {
        this.mock = mock;
    }

    @Override
    public String toString() {
        return "Field{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", mock=" + mock +
                '}';
    }
}
