package com.lyp.learn.dp.pattern.decorator;

public class Client {
    public static void main(String[] args) {
        //第一种使用方式:单层装饰

        //客户端都持有 Component 对象，而不是具体对象
        Component component = new ConcreteComponent();
        Component componentDecorator = new ConcreteDecorator(component);
        componentDecorator.operation();

        System.out.println("----------------------");

        //第二种使用方式: 多层嵌套装饰
        Component component1 = new ConcreteComponent();
        Component componentDecorator1 = new ConcreteDecorator(component1);
        Component componentDecoratorA = new ConcreteDecoratorA(componentDecorator1);
        Component componentDecoratorB = new ConcreteDecoratorB(componentDecoratorA);
        componentDecoratorB.operation();


    }
}
