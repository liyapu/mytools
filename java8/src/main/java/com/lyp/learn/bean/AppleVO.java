package com.lyp.learn.bean;

/**
 * @author liyapu
 * @date 2022-11-14 15:15
 * @description
 */

public class AppleVO {

    private Integer weight;
    private String address;

    public AppleVO() {
    }

    public AppleVO(Integer weight, String address) {
        this.weight = weight;
        this.address = address;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "AppleVO{" +
            "weight=" + weight +
            ", address='" + address + '\'' +
            '}';
    }
}
