package com.lyp.learn.dp.pattern.decorator;

public class ConcreteDecorator extends Decorator {

    public ConcreteDecorator(Component component) {
        super(component);
    }

    //定义自己的方法，装饰用
    private void decoratorMethod(){
        System.out.println("装饰方法.....");
    }

    //重写 operation 方法
    @Override
    public void operation(){
        this.decoratorMethod();
        super.operation();
    }
}
