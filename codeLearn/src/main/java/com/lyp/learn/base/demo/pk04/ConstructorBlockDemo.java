package com.lyp.learn.base.demo.pk04;

public class ConstructorBlockDemo {
    /**
     * 构造代码
     */
    {
        System.out.println("执行构造代码块块块...");
    }

    /**
     * 无参构造函数
     */
    public ConstructorBlockDemo(){
        System.out.println("执行无五五参构造函数函数...");
    }

    /**
     * 有参构造函数
     * @param id  id
     */
    public ConstructorBlockDemo(String id){
        System.out.println("执行有有有参构造函数函数...");
    }

    public static void main(String[] args) {
        ConstructorBlockDemo d1 = new ConstructorBlockDemo();
        System.out.println("---------------------------");
        ConstructorBlockDemo d2 = new ConstructorBlockDemo("aaa");
    }
}
