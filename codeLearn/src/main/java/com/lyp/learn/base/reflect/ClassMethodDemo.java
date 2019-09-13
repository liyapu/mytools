package com.lyp.learn.base.reflect;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-11 21:31
 */
public class ClassMethodDemo {
    public static void main(String[] args) throws Exception {
        Class studentClazz = new Student().getClass();
        Class personClazz = Class.forName("com.lyp.learn.reflect.Person");

        System.out.println("--------------getMethods-------------------");
        Method [] studentMethodArr = studentClazz.getMethods();
        for(Method method : studentMethodArr){
            System.out.println(method.getName());
            System.out.println(method.toString());
        }
        System.out.println();

       Method methodaaa =  studentClazz.getMethod("getStudentNameScope",String.class,Integer.class);
        System.out.println(methodaaa.getName());

        System.out.println("----------------getDeclaredMethods---------------------");
        Method [] studentDeclaredMethods = studentClazz.getDeclaredMethods();
        for(Method method : studentDeclaredMethods){
            System.out.println(method.getName());
            System.out.println(method.toString());
        }
        System.out.println();
        System.out.println("=========================");

        for(Method method : studentDeclaredMethods){
            System.out.print(Modifier.toString(method.getModifiers()) + " ");
            System.out.print(method.getReturnType().getName() + " ");
            System.out.print(method.getName() + "(");
            Class[] paramterTypes =  method.getParameterTypes();
            for(int i = 0; i < paramterTypes.length ;i++){
                if(i > 0){
                    System.out.print(",");
                }
                System.out.print(paramterTypes[i].getName());

            }
            System.out.print(");");
            System.out.println();
        }
    }
}
