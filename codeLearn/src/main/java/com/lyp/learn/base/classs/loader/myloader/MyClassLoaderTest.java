package com.lyp.learn.base.classs.loader.myloader;

import java.lang.reflect.Method;

/**
 * @author liyapu
 * @date 2023-04-09 16:31
 * @description
 */
public class MyClassLoaderTest {

    public static void main(String[] args) throws Exception {
        String classPathStr = Thread.currentThread().getContextClassLoader().getResource("").toString();
        System.out.println("classPathStr = " + classPathStr);

        //初始化自定义类加载器，会先初始化父类ClassLoader，其中会把自定义类加载器的父加载器设置为应用程序类加载器AppClassLoader
        MyClassLoader myClassLoader = new MyClassLoader(classPathStr);
        //loadClass 加载类，内部有双亲委派机制，会自动的调用到 MyClassLoader.findClass()方法中
        Class clazz = myClassLoader.loadClass("com.lyp.learn.base.classs.loader.myloader.Bird");

        Object obj = clazz.newInstance();
        Method flyMethod = clazz.getDeclaredMethod("fly", null);
        flyMethod.invoke(obj, null);

        Method eatMethod = clazz.getDeclaredMethod("eat", null);
        eatMethod.invoke(obj, null);

        System.out.println();
        System.out.println(clazz.getClassLoader().getClass().getName());
        System.out.println(clazz.getClassLoader());

    }

/*
https://blog.csdn.net/qq_38128551/article/details/121876429
打破双亲委派机制
再来一个沙箱安全机制示例，尝试打破双亲委派机制，用自定义类加载器加载我们自己实现的 java.lang.String.class
重写类加载方法 loadClass ，实现自己的加载逻辑，不委派给双亲加载
*/

}
