package com.lyp.learn.guava.eventbus.demo2;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 不想让外部看到，包可见，去掉了public
 *
 * topic1 -> subscriber->subscribe
 *        -> subscriber->subscribe
 *        -> subscriber->subscribe
 *
 * topic2 -> subscriber->subscribe
 *        -> subscriber->subscribe
 *        -> subscriber->subscribe
 *
 */
class MyRegistry {
    private final ConcurrentHashMap<String, ConcurrentLinkedQueue<MySubscriber>> subscriberContainer =
            new ConcurrentHashMap<>();
    /**
     * 绑定
     * @param subscriber
     */
    public void bind(Object subscriber){
        List<Method> subscribeMethods =  getSubscribeMethods(subscriber);
        subscribeMethods.forEach(m -> tierSubscriber(subscriber,m));
    }

    private List<Method> getSubscribeMethods(Object subscriber) {
        List<Method> methods = new ArrayList<>();
        Class<?> temp = subscriber.getClass();
        while (temp != null){
            Method[] declaredMethods = temp.getDeclaredMethods();
            Arrays.stream(declaredMethods)
                    // 有@MySubscribe注解，参数1个，public修饰
                    .filter(m -> m.isAnnotationPresent(MySubscribe.class) && m.getParameterCount() == 1 && m.getModifiers() == Modifier.PUBLIC)
                    .forEach(methods::add);
            temp = temp.getSuperclass();
        }
        return methods;
    }

    private void tierSubscriber(Object subscriber,Method method){
        //获取注解
        MySubscribe mySubscribe = method.getDeclaredAnnotation(MySubscribe.class);
        String topic = mySubscribe.topic();

        subscriberContainer.computeIfAbsent(topic,key -> new ConcurrentLinkedQueue<>());
        //等价于下面的几句
//        ConcurrentLinkedQueue<MySubscriber> mySubscribers = subscriberContainer.get(topic);
//        if(null == mySubscribers){
//            mySubscribers = new ConcurrentLinkedQueue<>();
//            subscriberContainer.put(topic,mySubscribers);
//        }
        subscriberContainer.get(topic).add(new MySubscriber(subscriber,method));
    }

    /**
     * 解绑
     * 通过标记位进行解绑
     * @param subscriber
     */
    public void unbind(Object subscriber){
        subscriberContainer.forEach((key,queue)->
                queue.forEach(s -> {
                    if(subscriber == s.getSubscribeObject()){
                        s.setDisable(true);
                    }
                }));
    }

    public ConcurrentLinkedQueue<MySubscriber> scanSubscriber(String topic){
        return subscriberContainer.get(topic);
    }
}
