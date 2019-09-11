package com.lyp.learn.doc;

import lombok.Data;
import lombok.ToString;

/**
 * @author: liyapu
 * @description:
 * @date 2019-09-10 20:55
 */
@Data
@ToString
public class Person {
    private String name;
    private Integer age;
    private String address;

    public Person(){

    }

    public Person(String name, Integer age, String address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }
}
