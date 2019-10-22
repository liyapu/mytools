package com.lyp.learn.base.pk04;

/**
 * 父类
 */
class SuperClass {
    static{
        System.out.println("父类静态代码块");
    }
    {
        System.out.println("父类构造代码块");
    }
    public SuperClass(){
        System.out.println("父类构造函数");
    }
}

/**
 * 子类
 */
class SubClass extends SuperClass{
    static{
        System.out.println("子类静态代码块");
        System.out.println();
    }

    {
        System.out.println("子类构造代码块");
    }

    public SubClass(){
        System.out.println("子类构造函数");
    }

}

public class CodeOrderDemo {
    public static void main(String[] args) {
        SubClass sb = new SubClass();
        System.out.println("------------");
        SubClass sb1 = new SubClass();
    }
}
