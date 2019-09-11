package com.lyp.learn.dp.pattern.mediatorpattern2;

/**
 * 抽象同事对象
 */
public abstract class Person {

    protected String name;
    protected Mediator mediator;

    Person(String name,Mediator mediator){
        this.name = name;
        this.mediator = mediator;
    }

    //定义人要执行的动作方法
    public abstract void action(String message);

}
