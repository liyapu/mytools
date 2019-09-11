package com.lyp.learn.dp.pattern.mediatorpattern2;

/**
 * 抽象中介者
 */
public interface Mediator {
    //申明一个联络方法
    void constact(String message, Person person);
}
