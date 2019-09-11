package com.lyp.learn.dp.pattern.decorator;

public class ConcreteComponent implements Component{
    @Override
    public void operation() {
        System.out.println("工作中:写代码....");
    }
}
