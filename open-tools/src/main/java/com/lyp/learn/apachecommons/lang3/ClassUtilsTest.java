package com.lyp.learn.apachecommons.lang3;

import org.apache.commons.lang3.ClassUtils;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-22 20:47
 */
public class ClassUtilsTest {


    @Test
    public void test01() {
        Class clazz = ClassUtilsTest.class;

        // 获取ClassUtilsTest类所有实现的接口
        List<Class<?>> allInterfaces = ClassUtils.getAllInterfaces(clazz);
        allInterfaces.stream()
                .forEach(System.out::println);
        System.out.println();

        // 获取ClassUtilsTestt类所有父类
        List<Class<?>> allSuperclasses = ClassUtils.getAllSuperclasses(Test.class);
        allSuperclasses.stream()
                .forEach(System.out::println);

        // 获取Test类所在的包名
        String packageName = ClassUtils.getPackageName(clazz);
        System.out.println(packageName);

        // 获取Test类简单类名
        String shortClassName = ClassUtils.getShortClassName(clazz);
        System.out.println(shortClassName);

        // 判断是否可以转型
        boolean assignable = ClassUtils.isAssignable(Test.class, Object.class);
        System.out.println(assignable);

        // 判断是否有内部类
        boolean innerClass = ClassUtils.isInnerClass(Test.class);
        System.out.println(innerClass);
    }
}
