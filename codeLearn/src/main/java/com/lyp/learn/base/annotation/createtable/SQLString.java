package com.lyp.learn.base.annotation.createtable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liyapu
 * @Description: 注解String类型字段
 * @create: 2018-11-08 22:30
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface SQLString {
    //该字段对应数据库表列名
    String name() default "";
    //列类型分配的长度，如varchar(30) 的30
    int value() default 0;
    //嵌套约束注解
    Constraints constraint() default @Constraints;
}
