package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 * 抽象同事
 */
public abstract class Colleague {

    private Mediator mediator;

    public Colleague(Mediator mediator){
        this.mediator = mediator;
    }

    public Mediator getMediator() {
        return mediator;
    }

    public void setMediator(Mediator mediator) {
        this.mediator = mediator;
    }

    //业务方法，调用此方法改变对象的内部状态
    public void change(){
        this.mediator.colleagueChanged(this);
    }

    //抽象行为方法，由子类实现
    public abstract void action();
}
