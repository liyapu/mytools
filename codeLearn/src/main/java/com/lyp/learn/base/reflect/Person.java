package com.lyp.learn.base.reflect;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-08 13:45
 */
public class Person {
    public String name;
    public String father = "父爱如山";
    public int age;

    protected  String address;

    String hobby;
    int money;

    private int gender;

    public Person() {
    }

    public Person(String name, String father, int age, String address, String hobby, int money, int gender) {
        this.name = name;
        this.father = father;
        this.age = age;
        this.address = address;
        this.hobby = hobby;
        this.money = money;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    @AgeValidator(min=18,max=35)
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getGender() {
        return gender;
    }

    public void setGender(int gender) {
        this.gender = gender;
    }


    public String getPersonInfo(){
        return "Person info,name: " + getName() + " ,address:" + getAddress();
    }

    protected int getPersonAge(){
        return getAge();
    }

    private String getPersonGender(){
        return "Person info .gender:" + getGender();
    }
}
