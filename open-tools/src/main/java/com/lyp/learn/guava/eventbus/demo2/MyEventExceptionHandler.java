package com.lyp.learn.guava.eventbus.demo2;

public interface MyEventExceptionHandler {
    /**
     *
     * @param cause 异常信息
     * @param context  异常上下文
     */
    void handle(Throwable cause,MyEventContext context);
}
