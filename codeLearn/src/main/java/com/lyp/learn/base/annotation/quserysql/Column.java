package com.lyp.learn.base.annotation.quserysql;

import java.lang.annotation.*;

/**
 * @Author: liyapu
 * @Description: 定义 表字段注解
 * @create: 2018-11-04 20:38
 */


@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    String value();
}
