package com.lyp.learn.base.annotation.test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  元注解：
 *    元注解：JDK提供了几个特殊的注解，只能用在注解上面。
 *          @Target：表示该注解可以用于什么地方，取值如下：
 *                  ElementType.ANNOTATION_TYPE 可以给一个注解进行注解
 *                  ElementType.CONSTRUCTOR 可给构造方法进行注解
 *                  ElementType.FIELD 可给属性进行注解
 *                  ElementType.LOCAL_VARIABLE 可给局部变量进行注解
 *                  ElementType.METHOD 可给方法进行注解
 *                  ElementType.PACKAGE 可给一个包进行注解
 *                  ElementType.PARAMETER 可给一个方法内的参数进行注解
 *                  ElementType.TYPE 可给一个类型进行注解，比如类、接口、枚举
 *
 *           @Retention：表示注解传递存活时间。取值如下：
 *                   RetentionPolicy.SOURCE 注解只在源码阶段保留，在编译器编译时它将被丢弃。
 *                   RetentionPolicy.CLASS 注解只被保留到编译进行的时候，它并不会被加载到 JVM 中。
 *                   RetentionPolicy.RUNTIME 注解可保留到程序运行时并被加载到 JVM 中，因此可通过反射机制读取注解的信息。
 *
 *           @Documented： 将注解包含到 Javadoc 中
 *
 *            @Inherited： 允许子类继承父类中的注解
 *                          如父类Animal有一个注解@Test，且@Test注解上有@Inherited注解，
 *                          那么该父类Animal的子类Cat也默认有@Test注解
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface MyTest {
}
