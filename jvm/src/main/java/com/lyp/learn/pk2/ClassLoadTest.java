package com.lyp.learn.pk2;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-30 21:56
 */
public class ClassLoadTest {
    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        System.out.println(systemClassLoader);

        //获取其上层: 扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        System.out.println(extClassLoader);

        //获取其上层: 获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        System.out.println(bootstrapClassLoader);

        System.out.println("--------------------------");


        // 对于用户自定义类来说: 默认使用系统类加载器进行加载
        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        System.out.println(classLoader);
        System.out.println(classLoader == systemClassLoader);

        // 获取 本类的类加载器的 类加载器
        ClassLoader superClassLoader = classLoader.getClass().getClassLoader();
        System.out.println(superClassLoader);

        System.out.println("--------------------------");


        //获取平台类加载器
//        ClassLoader platformClassLoader = ClassLoader.getPlatformClassLoader();
//        System.out.println(platformClassLoader);

        System.out.println("--------------------------");

        // 获取不到 String 类的 类加载器
        // String类使用引导类加载器进行加载的 ---> Java 的核心类库都是使用引导类加载器进行加载的
        ClassLoader classLoaderStr = String.class.getClassLoader();
        System.out.println(classLoaderStr);
    }
}
