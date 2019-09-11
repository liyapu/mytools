package com.lyp.learn.base.annotation.fruit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liyapu
 * @Description: 水果颜色注解
 * @create: 2018-11-02 09:54
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitColor {

    /**
     * 颜色属性
     */
    Color fruitColor() default Color.GREEN;

    /**
     * 颜色枚举
     */
    public enum Color{
        RED,GREEN,YELLOW ;
    };

}
