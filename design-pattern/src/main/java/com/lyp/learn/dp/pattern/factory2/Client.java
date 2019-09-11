package com.lyp.learn.dp.pattern.factory2;

public class Client {
    public static void main(String[] args) throws Exception {
        //创建不同的工厂
        Factory addFactory = new AddFactory();
        Factory subFactory = new SubFactory();
        Factory mulFactory = new MulFactory();
        Factory divFactory = new DivFactory();

        // 通过工厂对象创建相应的实例对象
        Operation add = addFactory.CreateOperation();
        Operation sub = subFactory.CreateOperation();
        Operation mul = mulFactory.CreateOperation();
        Operation div = divFactory.CreateOperation();

        System.out.println(add.getResult(20,10));
        System.out.println(sub.getResult(20,10));
        System.out.println(mul.getResult(20,10));
        System.out.println(div.getResult(20,10));
    }
}
