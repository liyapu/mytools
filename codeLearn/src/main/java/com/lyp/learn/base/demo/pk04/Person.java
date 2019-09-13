package com.lyp.learn.base.demo.pk04;

public class Person {
    String name;

    public Person(){

    }

    public Person(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void getInfo(String name){
        //当函数里面有参数时,如果函数的参数和成员变量一样,这时不加this的话,程序就会根据"就近原则",自动调用最近的值
        System.out.println("getInfo name: " + name);
        //如果想要输出Person 对象内部的name,就要加上 this 关键字
        System.out.println("getInfo this.name: " + this.name);
    }

    public static void main(String[] args) {
        Person p1 = new Person("张三丰");
        p1.getInfo("张");

    }

}
