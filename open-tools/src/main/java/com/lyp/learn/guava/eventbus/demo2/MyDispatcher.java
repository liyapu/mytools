package com.lyp.learn.guava.eventbus.demo2;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class MyDispatcher {
    private Executor executorService;
    private MyEventExceptionHandler exceptionHandler;

    public static final Executor SEQUENCE_EXECUTOR_SERVICE = SequenceExecutorService.INSTANCE;
    public static final Executor PER_THREAD_EXECUTOR_SERVICE = PerThreadExecutorService.INSTANCE;

    private MyDispatcher(Executor executorService, MyEventExceptionHandler exceptionHandler) {
        this.executorService = executorService;
        this.exceptionHandler = exceptionHandler;
    }

    static MyDispatcher newDispatcher(Executor executor,MyEventExceptionHandler exceptionHandler){
        return new MyDispatcher(executor,exceptionHandler);
    }

    static MyDispatcher newSequenceDispatcher(MyEventExceptionHandler exceptionHandler){
        return new MyDispatcher(SEQUENCE_EXECUTOR_SERVICE,exceptionHandler);
    }

    static MyDispatcher newPerThreadDispatcher(MyEventExceptionHandler exceptionHandler){
        return new MyDispatcher(PER_THREAD_EXECUTOR_SERVICE,exceptionHandler);
    }

    public void close(){
        //如果是ExecutorService类型的资源，需要关闭
        if(executorService instanceof ExecutorService){
            ((ExecutorService) executorService).shutdown();
        }
    }

    public void dispatcher(Bus bus,MyRegistry registry,Object event,String topic){
        ConcurrentLinkedQueue<MySubscriber> subscribers = registry.scanSubscriber(topic);
        if(null == subscribers){
            if(null != exceptionHandler){
                exceptionHandler.handle(new IllegalArgumentException("the topic " + topic + " not register yet"),
                        new BaseEventContext(bus.getBusName(),null,event));
            }
            return;
        }
        subscribers.stream()
                    .filter(mySubscriber -> !mySubscriber.isDisable())
//                    .peek(System.out::println)
                    .filter(mySubscriber -> {
                        Method subscribeMethod = mySubscriber.getSubscribeMethod();
                        Class<?> aClass = subscribeMethod.getParameterTypes()[0];
                        return aClass.isAssignableFrom(event.getClass());
                    })
//                .peek(System.out::println)
                .forEach(mySubscriber -> realInvokeSubscribe(mySubscriber,event,bus));
    }

    public void realInvokeSubscribe(MySubscriber subscriber,Object event,Bus bus){
        Object subscribeObject = subscriber.getSubscribeObject();
        Method subscribeMethod = subscriber.getSubscribeMethod();
        executorService.execute(() ->{
            try {
                subscribeMethod.invoke(subscribeObject,event);
            } catch (Exception e) {
              if(null != exceptionHandler){
                  exceptionHandler.handle(e,new BaseEventContext(bus.getBusName(),subscriber,event));
              }
            }
        });
    }
    /**
     * 串行方式执行
     */
    public static class SequenceExecutorService implements Executor{
        private final static SequenceExecutorService INSTANCE = new SequenceExecutorService();

        @Override
        public void execute(Runnable command) {
            command.run();
        }
    }

    /**
     * 并方式执行
     * 一个线程一个
     */
    public static class PerThreadExecutorService implements Executor{
        private final static PerThreadExecutorService INSTANCE = new PerThreadExecutorService();

        @Override
        public void execute(Runnable command) {
            new Thread(command).start();
        }
    }


    public static class BaseEventContext implements MyEventContext{
        private final String eventBusName;
        private final MySubscriber subscriber;
        private final Object event;

        public BaseEventContext(String eventBusName, MySubscriber subscriber, Object event) {
            this.eventBusName = eventBusName;
            this.subscriber = subscriber;
            this.event = event;
        }

        @Override
        public String getSource() {
            return this.eventBusName;
        }

        @Override
        public Object getSubscriber() {
            return subscriber != null ? subscriber.getSubscribeObject() : null;
        }

        @Override
        public Method getSubscribe() {
            return subscriber != null ? subscriber.getSubscribeMethod() : null;
        }

        @Override
        public Object getEvent() {
            return this.event;
        }
    }
}
