package com.lyp.learn.base.annotation.inherited;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-07 17:26
 */
public class InheritedAnnotationUtil {
    public static void getInfo(Class<?> clazz){
        boolean isInheritedAnnotation = clazz.isAnnotationPresent(InheritedAnnotation.class);
        if(isInheritedAnnotation){
            InheritedAnnotation inheritedAnnotation = clazz.getAnnotation(InheritedAnnotation.class);
            String value = inheritedAnnotation.value();
            System.out.println(value);
        }else{
            System.out.println("没有继承注解的描述！");
        }
    }
}
