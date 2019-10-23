package com.lyp.learn.apachecommons.bean;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 20:59
 */
public class ReflectBean {
    private String name;
    private String address;
    private Integer age;

    public ReflectBean() {
    }

    public ReflectBean(String name, String address, Integer age) {
        this.name = name;
        this.address = address;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getInfo(String msg){
        return "info msg is:" + msg;
    }
    public String getInfo(String msg,Integer level) {
        return "info msg is :" + msg + ", level is " + level;
    }

    public static String staticHello(){
        return "static method hello";
    }
}
