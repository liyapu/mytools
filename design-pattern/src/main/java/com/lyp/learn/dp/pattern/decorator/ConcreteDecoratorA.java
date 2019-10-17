package com.lyp.learn.dp.pattern.decorator;

public class ConcreteDecoratorA extends Decorator{

    public ConcreteDecoratorA(Component component) {
        super(component);
    }

    private void music(){
        System.out.println("听着音乐 工作......");
    }

    @Override
    public void operation(){
        music();
        super.operation();
    }
}
