package com.lyp.learn.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-11 21:31
 */
public class ClassFieldDemo {
    public static void main(String[] args) throws Exception {
        Class studentClazz = new Student().getClass();

        System.out.println("--------------getFields-----------------");
        Field[] studentFields = studentClazz.getFields();
        for(Field field : studentFields){
            System.out.println(field.getName());
            System.out.println(field.toString());
        }
        System.out.println();

        System.out.println("----------getField(String name)---------");
        Field studentOfStuName = studentClazz.getField("stuName");
        System.out.println(studentOfStuName);
        System.out.println(studentOfStuName.getName());
        System.out.println(studentOfStuName.toString());
        System.out.println();

//        System.out.println("-----访问私有属性---此处访问报错:java.lang.NoSuchFieldException: levelName --");
//        Field studentOfLevelName = studentClazz.getField("levelName");
//        studentOfLevelName.setAccessible(true);
//        System.out.println(studentOfLevelName);
//        System.out.println(studentOfLevelName.getName());
//        System.out.println(studentOfLevelName.toString());
//        System.out.println();

        System.out.println("----------getDeclaredFields--------------");
        Field [] studentDeclaredFields = studentClazz.getDeclaredFields();
        for(Field field : studentDeclaredFields){
            System.out.println(field.getName());
            System.out.println(field.toString());
        }
        System.out.println();
        System.out.println("=============================");
        for(Field field : studentDeclaredFields){
            System.out.print(Modifier.toString(field.getModifiers()) + " ");
            System.out.print(field.getType() + " " + field.getName() + ";");
            System.out.println();
        }

        System.out.println("-----------getDeclaredField(String name)--------------------");
        Field studentOfHeight = studentClazz.getDeclaredField("height");
        System.out.println(studentOfHeight);
        System.out.println(studentOfHeight.getName());
        System.out.println(studentOfHeight.toString());
        System.out.println();


        System.out.println("------访问私有属性-------");
        Field studentOfLevel = studentClazz.getDeclaredField("level");
        System.out.println(studentOfLevel);
        System.out.println(studentOfLevel.getName());
        System.out.println(studentOfLevel.toString());
        System.out.println();

        System.out.println("------访问私有属性-------");
        Field studentOfLevelName = studentClazz.getDeclaredField("levelName");
        //启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
        studentOfLevelName.setAccessible(true);
        System.out.println(studentOfLevelName);
        System.out.println(studentOfLevelName.getName());
        System.out.println(studentOfLevelName.toString());
        System.out.println();
    }
}
