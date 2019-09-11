package com.lyp.learn.base.annotation.repeatable;

import java.lang.annotation.Repeatable;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-07 18:06
 *
 * @Repeatable 括号内的就相当于用来保存该注解内容的容器
 */


@Repeatable(Roles.class)
public @interface Role {
    String value();
}
