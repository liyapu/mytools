package com.lyp.learn.base.annotation.complex;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 22:46
 */
public class MyAnnotationUtil {

    public static void getInfo(Class<?> clazz){

        //解析类上的注解
        boolean isMyAnnotation = clazz.isAnnotationPresent(MyAnnotation.class);
        if(isMyAnnotation){
            MyAnnotation myAnnotation = clazz.getDeclaredAnnotation(MyAnnotation.class);
            System.out.println(myAnnotation.value());
            System.out.println(myAnnotation.address());
            System.out.println(myAnnotation.arrayAttr().length + ":" + Arrays.toString(myAnnotation.arrayAttr()));
            System.out.println(myAnnotation.lamp());
            System.out.println(myAnnotation.annotationAttr().value());
        }

        System.out.println("---------------------------");
        //解析方法上的注解
        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            boolean isMyAnnotationMethod = method.isAnnotationPresent(MyAnnotation.class);
            if(isMyAnnotationMethod){
                MyAnnotation myAnnotationMethod = method.getDeclaredAnnotation(MyAnnotation.class);
                System.out.println(myAnnotationMethod.value());
            }
        }


    }
}
