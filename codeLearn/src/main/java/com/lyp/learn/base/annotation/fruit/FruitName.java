package com.lyp.learn.base.annotation.fruit;

import java.lang.annotation.*;

/**
 * @Author: liyapu
 * @Description: 水果名称注解
 * @create: 2018-11-02 09:50
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface FruitName {
    String value() default "";
}
