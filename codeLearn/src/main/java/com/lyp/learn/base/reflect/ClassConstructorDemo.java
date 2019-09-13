package com.lyp.learn.base.reflect;

import java.lang.reflect.Constructor;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-11 21:31
 */
public class ClassConstructorDemo {
    public static void main(String[] args) throws Exception {
        Class studentClazz = new Student().getClass();
        Class personClazz = Class.forName("com.lyp.learn.reflect.Person");

        Constructor[] studentConstructor = studentClazz.getConstructors();
        for(Constructor constructor : studentConstructor){
            System.out.println(constructor.getName());
            System.out.println(constructor);
        }
        System.out.println();

        Constructor [] studentDeclaredConstructor = studentClazz.getDeclaredConstructors();
        for(Constructor constructor : studentDeclaredConstructor){
            System.out.println(constructor.getName());
            System.out.println(constructor);
        }
        System.out.println("---------------");


        Constructor<?> studentDeclaredConstructorOfParam =  studentClazz.getDeclaredConstructor(String.class,int.class);
        studentDeclaredConstructorOfParam.setAccessible(true);
        Student student2 = (Student) studentDeclaredConstructorOfParam.newInstance("好学生",12);
        System.out.println(student2.getName());
        System.out.println(student2.getAge());

    }
}
