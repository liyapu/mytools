package com.lyp.learn.guava.eventbus.demo1;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.SubscriberExceptionContext;
import com.google.common.eventbus.SubscriberExceptionHandler;
import com.lyp.learn.guava.eventbus.demo1.listeners.ExceptionListener;

/**
 * 订阅方法，处理时出现异常的
 */
public class ExceptionListenerEventBusExample1 {

    public static void main(String[] args) {
        EventBus eventBus = new EventBus();
        eventBus.register(new ExceptionListener());

        eventBus.post("test ");
    }
}
