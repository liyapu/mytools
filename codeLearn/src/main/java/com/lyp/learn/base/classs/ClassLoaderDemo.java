package com.lyp.learn.base.classs;

public class ClassLoaderDemo {
    public static void main(String[] args) throws Exception {
        //1. 获取一个系统的类加载器(可以获取，当前这个类ClassLoaderDemo就是它加载的)
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        System.out.println(classLoader);


        //2. 获取系统类加载器的父类加载器（扩展类加载器，可以获取）.
        classLoader = classLoader.getParent();
        System.out.println(classLoader);


        //3. 获取扩展类加载器的父类加载器（引导类加载器，不可获取）.
        classLoader = classLoader.getParent();
        System.out.println(classLoader);


        //4. 测试当前类由哪个类加载器进行加载（系统类加载器）:
        classLoader = Class.forName("com.lyp.learn.classs.ClassLoaderDemo")
                .getClassLoader();
        System.out.println(classLoader);


        //5. 测试 JDK 提供的 Object 类由哪个类加载器负责加载（引导类）
        classLoader = Class.forName("com.lyp.learn.base.threads.pk01.Object")
                .getClassLoader();
        System.out.println(classLoader);
    }
}
