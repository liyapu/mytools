package com.lyp.learn.base.proxy.dongtai2;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-13 19:08
 */
public class SubjectDynamicHandler implements InvocationHandler {

    //这个就是我们要代理的真实对象
    private Object target;

    //构造方法，给我们要代理的真实对象赋初值
    public SubjectDynamicHandler(Object target){
        this.target = target;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        //　　在代理真实对象前我们可以添加一些自己的操作
        System.out.println("SubjectDynamicHandler ------准备工作");

        //System.out.println("Method:" + method);

        //当代理对象调用真实对象的方法时，其会自动的跳转到代理对象关联的handler对象的invoke方法来进行调用
        Object result = method.invoke(target, args);

        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("SubjectDynamicHandler ------清理工作");
        System.out.println();
        return result;
    }


    public static <T> T createProxyObject(T realSubject) {
        //我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
        InvocationHandler handler = new SubjectDynamicHandler(realSubject);

        /*
         * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数
         * 第一个参数 handler.getClass().getClassLoader() ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
         * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了
         * 第三个参数handler， 我们这里将这个代理对象关联到了上方的 InvocationHandler 这个对象上
         */
        return (T) Proxy.newProxyInstance(handler.getClass().getClassLoader(), realSubject
                .getClass().getInterfaces(), handler);
    }
}
