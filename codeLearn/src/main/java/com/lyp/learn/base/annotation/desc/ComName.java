package com.lyp.learn.base.annotation.desc;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-03 13:17
 */

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ComName {
    //社区
    String commuity() default "";

    //创建者
    String namer();
}
