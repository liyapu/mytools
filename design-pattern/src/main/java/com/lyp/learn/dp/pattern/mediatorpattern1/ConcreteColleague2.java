package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 * 具体同事2
 */
public class ConcreteColleague2 extends Colleague {

    public ConcreteColleague2(Mediator mediator) {
        super(mediator);
    }

    //实现具体行动方法
    @Override
    public void action() {
        System.out.println("这是同事2 执行的方法");
    }
}
