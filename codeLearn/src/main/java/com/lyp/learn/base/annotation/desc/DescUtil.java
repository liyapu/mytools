package com.lyp.learn.base.annotation.desc;

import java.lang.reflect.Method;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-03 13:24
 */
public class DescUtil {

    public static void getInfo(Class<?> clazz){

        boolean isDescriptionAnnotation = clazz.isAnnotationPresent(Description.class);
        String strDesc = "类的描述是：";
        if(isDescriptionAnnotation){
            Description description = clazz.getAnnotation(Description.class);
            strDesc = strDesc + description.value();
            System.out.println(strDesc);
        }


        Method[] methods = clazz.getMethods();
        for(Method method : methods){
            String strMethod = "方法描述是：";
            boolean isComNameAnnotation = method.isAnnotationPresent(ComName.class);
            if(isComNameAnnotation){
                ComName on = method.getAnnotation(ComName.class);
                strMethod = strMethod + "社区 " + on.commuity() + ", 创建者 " + on.namer();
                System.out.println(strMethod);
            }
        }
    }


}
