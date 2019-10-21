package com.lyp.learn.apachecommons.beanutils;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 14:38
 *
 * PropertyUtils类和BeanUtils不同在于，运行getProperty、setProperty操作时，没有类型转换，使用属性的原有类型或者包装类。
 * 由于age属性的数据类型是int，所以方法PropertyUtils.setProperty(userInfo, "age", "8")会爆出数据类型不匹配，无法将值赋给属性。
 *
 */
public class PropertyUtils
{
}
