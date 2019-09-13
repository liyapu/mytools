package com.lyp.learn.base.demo.pk04;

public class CodeBlock {
    static{
        System.out.println("静态代码块");
    }
    {
        System.out.println("构造代码块");
    }
    public CodeBlock(){
        System.out.println("无参构造函数");
    }

    public void sayHello(){
        System.out.println("普通代码 start");
        {
            System.out.println("普通代码块");
        }
        System.out.println("普通代码 end");

    }

    public static void main(String[] args) {
        new CodeBlock().sayHello();
        System.out.println("---------------");
        new CodeBlock().sayHello();;
    }
}
