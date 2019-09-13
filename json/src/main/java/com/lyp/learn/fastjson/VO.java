package com.lyp.learn.fastjson;

import java.util.HashMap;
import java.util.Map;

public class VO {
    private int id;
    private String address;
    private Map<String, Object> attributes = new HashMap<String, Object>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "VO{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", attributes=" + attributes +
                '}';
    }
}
