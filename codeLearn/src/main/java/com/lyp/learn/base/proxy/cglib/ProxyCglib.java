package com.lyp.learn.base.proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * 无需接口都可使用该代理
 */
public class ProxyCglib implements MethodInterceptor {

    private Object target;

    /**
     * 创建代理对象方法
     */
    @SuppressWarnings("unchecked")
    public <T>  T createProxy(Object target) {
        // 要代理的目标类
        this.target = target;
        //创建Enhancer对象
        Enhancer enhancer = new Enhancer();
        //设置要代理的目标类，以扩展功能
        enhancer.setSuperclass(this.target.getClass());
        //设置单一回调对象，在回调中拦截对目标方法的调用
        enhancer.setCallback(this);
        //设置类加载器
        enhancer.setClassLoader(target.getClass().getClassLoader());
        //创建代理对象
        return (T)enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("前置......");
//        methodProxy.invokeSuper(obj, objects);
        methodProxy.invoke(target, objects);
        System.out.println("后置.....");
        return null;
    }
}
