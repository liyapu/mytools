package com.lyp.learn.bean;


import lombok.Data;

@Data
public class User {
    private Integer id;
    private String name;
    private int age;
    private String telephone;
    private String address;
    //身高
    private Integer height;
    //体重
    private int weight;

    public User(){

    }

    public User(Integer id,String name,int age,String telephone){
        this.id = id;
        this.name = name;
        this.age = age;
        this.telephone = telephone;
    }
}
