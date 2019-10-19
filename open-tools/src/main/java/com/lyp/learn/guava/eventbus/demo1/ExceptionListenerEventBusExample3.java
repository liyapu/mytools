package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.lyp.learn.guava.eventbus.demo1.listeners.ExceptionListener;
import com.lyp.learn.guava.eventbus.demo1.listeners.SimpleListener;

/**
 * 订阅方法，处理时出现异常的
 */
public class ExceptionListenerEventBusExample3 {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus(new ExceptionHandler());
        eventBus.register(new ExceptionListener());

        eventBus.post("test ");
    }

    //定义异常处理类
    static class ExceptionHandler implements SubscriberExceptionHandler {

        @Override
        public void handleException(Throwable exception, SubscriberExceptionContext context) {
            System.out.println(context.getEvent());
            System.out.println(context.getEventBus());
            System.out.println(context.getSubscriber());
            System.out.println(context.getSubscriberMethod());
        }
    }
}
