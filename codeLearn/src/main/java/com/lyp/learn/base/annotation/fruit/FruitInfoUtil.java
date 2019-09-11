package com.lyp.learn.base.annotation.fruit;

import java.lang.reflect.Field;

/**
 * @Author: liyapu
 * @Description: 注解处理器
 * @create: 2018-11-02 10:00
 */


public class FruitInfoUtil {

    public static void getFruitInfo(Class<?> clazz){

        String strFruitName = "水果名称: ";
        String strFruitColor = "水果颜色: ";
        String strFruitProvider = "水果供应商信息: ";

        Field[] declaredFields = clazz.getDeclaredFields();

        for(Field field : declaredFields){
            if(field.isAnnotationPresent(FruitName.class)){
                FruitName fruitName = field.getAnnotation(FruitName.class);
                strFruitName = strFruitName + fruitName.value();
                System.out.println(strFruitName);
            }else if(field.isAnnotationPresent(FruitColor.class)){
                FruitColor fruitColor = field.getAnnotation(FruitColor.class);
                strFruitColor = strFruitColor + fruitColor.fruitColor().toString();
                System.out.println(strFruitColor);
            }else if(field.isAnnotationPresent(FruitProvider.class)){
                FruitProvider fruitProvider = field.getAnnotation(FruitProvider.class);
                strFruitProvider = strFruitProvider +"供应商编号:" + fruitProvider.id()
                                 + "，供应商名称:" + fruitProvider.name()
                                 + "，供应商地址:" + fruitProvider.address();
                System.out.println(strFruitProvider);
            }
        }
    }

}
