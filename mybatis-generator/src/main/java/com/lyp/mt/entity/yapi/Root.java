package com.lyp.mt.entity.yapi;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-09 20:41
 */
public class Root {
    private String type;
    private String title;
    private WebResult properties;
    private List<String> required;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public WebResult getProperties() {
        return properties;
    }

    public void setProperties(WebResult properties) {
        this.properties = properties;
    }

    public List<String> getRequired() {
        return required;
    }

    public void setRequired(List<String> required) {
        this.required = required;
    }
}
