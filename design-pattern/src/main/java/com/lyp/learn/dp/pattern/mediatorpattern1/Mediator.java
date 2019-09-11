package com.lyp.learn.dp.pattern.mediatorpattern1;

/**
 *  抽象中介者
 */
public interface Mediator {

    //中介者模式的业务逻辑方法
    public abstract void colleagueChanged(Colleague colleague);
}
