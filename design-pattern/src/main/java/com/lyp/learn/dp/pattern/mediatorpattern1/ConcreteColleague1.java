package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 * 具体同事1
 */
public class ConcreteColleague1 extends Colleague {

    public ConcreteColleague1(Mediator mediator) {
        super(mediator);
    }

    //实现具体行动方法
    @Override
    public void action() {
        System.out.println("这是同事1 执行的方法");
    }
}
