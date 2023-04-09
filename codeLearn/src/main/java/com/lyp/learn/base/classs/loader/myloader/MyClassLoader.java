package com.lyp.learn.base.classs.loader.myloader;

import java.io.File;
import java.io.FileInputStream;

/**
 * @author liyapu
 * @date 2023-04-09 16:18
 * @description 自定义类加载器
 *
 * 自定义类加载器只需要继承 java.lang.ClassLoader 类，该类有两个核心方法，
 * 一个是loadClass(String, boolean)，实现了双亲委派机制，
 * 还有一个方法是findClass，默认实现是空方法，
 * 所以我们自定义类加载器主要是重写findClass方法。
 */
public class MyClassLoader extends ClassLoader {

    private String classPath;

    public MyClassLoader(String classPath) {
        this.classPath = classPath;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassCastException {
        try {
            byte[] data = loadByte(name);
            //defineClass将一个字节数组转为Class对象，这个字节数组是class文件读取后最终的字节数组
            return defineClass(name, data, 0, data.length);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("eee " + e.getMessage());
            throw new ClassCastException(e.getMessage());
        }
    }

    private byte[] loadByte(String name) throws Exception {
        System.out.println("name = " + name);
        String dotName = name.replaceAll("\\.", File.separator);
        System.out.println("dotName = " + dotName);

        String classPathName = classPath + dotName + ".class";
        System.out.println("classPathName = " + classPathName);

        FileInputStream fis = new FileInputStream(classPathName);
        int len = fis.available();
        byte[] data = new byte[len];
        fis.read(data);
        fis.close();
        return data;
    }
}





















