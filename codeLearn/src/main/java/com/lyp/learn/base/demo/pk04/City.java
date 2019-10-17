package com.lyp.learn.base.demo.pk04;

class Country{
    String name = "中国";

    public void  printName(){
        System.out.println("Country 类 printName :" + name);
    }
}


public class City extends Country {
    String name;
    public void setName(){
        name = "河南";
    }

    @Override
    public void  printName(){
        System.out.println("City 类 printName : name :" + name);
        super.printName();
        System.out.println("City 类 printName : super.name :" + super.name);
    }

    public static void main(String[] args) {
        City city = new City();
        city.setName();
        city.printName();
    }
}
