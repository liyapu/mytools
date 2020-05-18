package com.lyp.learn.pk5;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import sun.java2d.cmm.Profile;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *  借助 CGLib 使得方法区出现内存溢出异常
 *
 *  jdk7及以前
 *      -XX:PermSize=10m -XX:MaxPermSize=10m
 *
 *  jdk8及以后
 *    -XX:MetaspaceSize=10m  -XX:MaxMetaspaceSize=10m
 *
 *    Caused by: java.lang.OutOfMemoryError: Metaspace
 */
public class JavaMethodAreaOOM {

    public static void main(String[] args) {
        while (true){
            Enhancer  enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                    return proxy.invokeSuper(obj,args);
                }
            });
            enhancer.create();
        }
    }

    static class OOMObject{

    }
}
