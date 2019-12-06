package com.lyp.learn.base.classs;

class Person{
    public String name;
    public String address;
    public int age;

    public Person(){
    }

    public Person(String name,String address){
        this.name = name;
        this.address = address;
    }

    public Person(String name,String address,int age){
        this(name,address);
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
public class Son  extends  Person{
    public String city;

    public Son(){
        super();
    }

    public Son(String city){
        this.city = city;
    }

    public Son(String name,String address,int age,String city){
        super(name,address,age);
        //Call to 'this()' must be first statement in constructor body
        //this(city);
        this.city = city;
    }

    @Override
    public String toString() {
        return "Son{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", age=" + age +
                ", city='" + city + '\'' +
                '}';
    }

    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println(s1);
        System.out.println();

        Son s2 = new Son("商丘");
        System.out.println(s2);
        System.out.println();

        Son s3 = new Son("张三","大街路",100,"郑州");
        System.out.println(s3);
    }
}
