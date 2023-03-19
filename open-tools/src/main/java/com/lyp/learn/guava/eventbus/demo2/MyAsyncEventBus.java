package com.lyp.learn.guava.eventbus.demo2;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 异步的执行
 */
public class MyAsyncEventBus extends MyEventBus {

    public MyAsyncEventBus(ThreadPoolExecutor executor){
        this("default-sync",null,executor);
    }

    public MyAsyncEventBus(String busName,ThreadPoolExecutor executor){
        this(busName,null, executor);
    }

    public MyAsyncEventBus(MyEventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        this("default-sync",exceptionHandler,executor);
    }

    public MyAsyncEventBus(String busName, MyEventExceptionHandler exceptionHandler, ThreadPoolExecutor executor) {
        super(busName,exceptionHandler,executor);
    }
}
