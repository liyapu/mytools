package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.lyp.learn.guava.eventbus.demo1.listeners.ExceptionListener;

/**
 * 订阅方法，处理时出现异常的
 */
public class ExceptionListenerEventBusExample2 {

    public static void main(String[] args) {
        //java 8 使用 Lambda 表达式
        EventBus eventBus = new EventBus(((exception, context) -> {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        }));
        eventBus.register(new ExceptionListener());

        eventBus.post("test ");
    }

}
