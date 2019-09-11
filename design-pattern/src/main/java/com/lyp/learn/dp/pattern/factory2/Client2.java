package com.lyp.learn.dp.pattern.factory2;

public class Client2 {
    public static void main(String[] args) throws Exception {
        // 使用反射机制实例化工厂对象，因为字符串是可以通过变量改变的
        Factory addFactory = (Factory) Class.forName("com.lyp.learn.DesignPattern.pk02.factory2.AddFactory").newInstance();
        Factory subFactory = (Factory) Class.forName("com.lyp.learn.DesignPattern.pk02.factory2.SubFactory").newInstance();
        Factory mulFactory = (Factory) Class.forName("com.lyp.learn.DesignPattern.pk02.factory2.MulFactory").newInstance();
        Factory divFactory = (Factory) Class.forName("com.lyp.learn.DesignPattern.pk02.factory2.DivFactory").newInstance();

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
