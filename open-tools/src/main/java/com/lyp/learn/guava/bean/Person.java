package com.lyp.learn.guava.bean;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-26 10:40
 */
public class Person {
    private String name;
    private String address;
    private Integer age;

    public Person(){

    }
    public Person(String name,Integer age){
        this.name = name;
        this.age = age;
    }
    public Person(String name, String address, Integer age) {
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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                '}';
    }
}
