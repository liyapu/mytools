package com.lyp.learn.base.proxy.dongtai;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

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
        method.invoke(target, args);

        //在代理真实对象后我们也可以添加一些自己的操作
        System.out.println("SubjectDynamicHandler ------清理工作");
        System.out.println();
        return null;
    }
}
