package com.lyp.learn.base.annotation.fruit;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liyapu
 * @Description: 水果供应商注解
 * @create: 2018-11-02 09:58
 */


@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FruitProvider {
    /**
     * 供应商编号
     */
    int id() default -1;

    /**
     * 供应商名称
     * @return
     */
    String name() default "";

    /**
     * 供应商地址
     * @return
     */
    String address() default "";

}
