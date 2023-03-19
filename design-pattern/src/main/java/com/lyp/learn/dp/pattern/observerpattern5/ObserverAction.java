package com.lyp.learn.dp.pattern.observerpattern5;

import com.google.common.base.Preconditions;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author liyapu
 * @date 2023-03-19 11:31
 * @description ObserverAction类用来表示@Subscribe注解的方法，其中，target表示观察者类，method表示方法。它主要用在ObserverRegistry观察者注册表中。
 */
public class ObserverAction {

    private Object target;
    private Method method;

    public ObserverAction(Object target, Method method) {
        this.target = Preconditions.checkNotNull(target);
        this.method = method;
        this.method.setAccessible(true);
    }

    public void execute(Object event) { // event是method方法的参数
        try {
            method.invoke(target, event);
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
