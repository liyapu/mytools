package com.lyp.learn.dp.pattern.decorator;

public class ConcreteDecoratorB extends Decorator{

    public ConcreteDecoratorB(Component component) {
        super(component);
    }

    private void tea(){
        System.out.println("喝着茶工作......");
    }

    public void operation(){
        tea();
        super.operation();
    }
}
