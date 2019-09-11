package com.lyp.learn.base.annotation.complex;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-04 22:39
 */

@Retention(RetentionPolicy.RUNTIME)
public @interface MetaAnnotation {
    String value() default "";
}
