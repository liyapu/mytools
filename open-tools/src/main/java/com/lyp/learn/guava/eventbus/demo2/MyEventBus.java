package com.lyp.learn.guava.eventbus.demo2;

import java.util.Objects;
import java.util.concurrent.Executor;

public class MyEventBus implements Bus {

    private String busName;

    private static final String DEFAULT_BUS_NAME = "default";
    //没有主题的，放到默认主题中
    private static final String DEFAULT_TOPIC = "default-topic";

    private final MyRegistry registry = new MyRegistry();

    private final MyDispatcher dispatcher;

    public MyEventBus(){
        this(DEFAULT_BUS_NAME,null,MyDispatcher.SEQUENCE_EXECUTOR_SERVICE);
    }

    public MyEventBus(String busName){
        this(busName,null,MyDispatcher.SEQUENCE_EXECUTOR_SERVICE);
    }

    public MyEventBus(MyEventExceptionHandler exceptionHandler){
        this(DEFAULT_BUS_NAME,exceptionHandler,MyDispatcher.SEQUENCE_EXECUTOR_SERVICE);
    }

    MyEventBus(String busName, MyEventExceptionHandler exceptionHandler, Executor executor){
        this.busName = busName;
        this.dispatcher = MyDispatcher.newDispatcher(executor,exceptionHandler);
    }

    @Override
    public void register(Object subscriber) {
        this.registry.bind(subscriber);
    }

    @Override
    public void unregister(Objects subscriber) {
        this.registry.unbind(subscriber);
    }

    @Override
    public void post(Object event) {
        this.post(event,DEFAULT_TOPIC);
    }

    @Override
    public void post(Object event, String topic) {
        dispatcher.dispatcher(this,registry,event,topic);
    }

    @Override
    public void close() {
        dispatcher.close();
    }

    @Override
    public String getBusName() {
        return busName;
    }
}
