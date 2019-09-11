package com.lyp.learn.base.annotation.quserysql;

import java.lang.annotation.*;

/**
 * @Author: liyapu
 * @Description:  定义表注解
 * @create: 2018-11-04 20:36
 */

@Documented
@Inherited
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value();
}
