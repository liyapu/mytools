//package com.lyp.learn.pk2;
//
//import sun.misc.Launcher;
//import sun.misc.URLClassPath;
//import sun.security.ec.CurveDB;
//
//import java.io.File;
//import java.lang.reflect.Array;
//import java.net.URL;
//import java.security.Provider;
//import java.util.Arrays;
//
///**
// * @author: liyapu
// * @description:
// * @date 2020-04-30 21:56
// */
//public class ClassLoadTest2 {
//    public static void main(String[] args) {
//        System.out.println("*************启动类加载器*******************");
//        // 获取 BootstrapClassLoader 能够加载的api的路径
//        //java 8 版本可以正常运行的代码
//        URL[] urLs = Launcher.getBootstrapClassPath().getURLs();
//        Arrays.stream(urLs)
//                .forEach(System.out::println);
//
//        // 从上面的路径汇总随意选择一个类(先解压jar)，来看看他的类加载器是什么：：：为null 是 引导类加载器
//        ClassLoader classLoader = Provider.class.getClassLoader();
//        System.out.println(classLoader);
//
//        System.out.println("************扩展类加载器***************");
//        String extDirs = System.getProperty("java.ext.dirs");
////        System.out.println(extDirs);
//        Arrays.stream(extDirs.split(File.pathSeparator))
//                .forEach(System.out::println);
//
//        System.out.println();
//
//        // 从上面的路径中随意选择一个类，来看看他的类加载器是什么: 扩展类加载器
//        ClassLoader classLoader1 = CurveDB.class.getClassLoader();
//        System.out.println(classLoader1);
//
//
//    }
//}
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
//
