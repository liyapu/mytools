package com.lyp.mt.yapi.entity.yapientity;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 20:44
 */
public class Page {
    private String type;
    private String description;
    private Object properties;
    private List<String> required;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getProperties() {
        return properties;
    }

    public void setProperties(Object properties) {
        this.properties = properties;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Page{" +
                "type='" + type + '\'' +
                ", description='" + description + '\'' +
                ", properties=" + properties +
                ", required=" + required +
                '}';
    }
}
