package com.lyp.learn.guava.eventbus.demo2;

import java.util.Objects;

/**
 * 模拟eventBus
 * 门面模式，外部只知道 EventBus 和 Executor,ExceptionHandler
 */
public interface Bus {

    void register(Object subscriber);

    void unregister(Objects subscriber);

    void post(Object event);

    void post(Object event,String topic);

    void close();

    String getBusName();
}
