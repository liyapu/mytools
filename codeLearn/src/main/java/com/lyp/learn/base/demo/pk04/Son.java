package com.lyp.learn.base.demo.pk04;

class Father{

    public void eat(){
        System.out.println("父亲吃了");
    }
    public void walk(){
        System.out.println("父亲步行");
    }
}


public class Son extends Father{

    public void eat(){
        System.out.println("儿子吃了");
    }

    public static void main(String[] args) {
        Father father = new Father();
        father.eat();
        father.walk();
        System.out.println();

        Son son = new Son();
        son.eat();
        son.walk();
    }
}
