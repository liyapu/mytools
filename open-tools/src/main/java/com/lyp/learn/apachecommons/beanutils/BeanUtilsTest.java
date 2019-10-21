package com.lyp.learn.apachecommons.beanutils;

import com.lyp.learn.apachecommons.bean.Person;
import org.apache.commons.beanutils.BeanUtils;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-21 14:15
 */
public class BeanUtilsTest {

    /**
     * 设置字段属性
     */
    @Test
    public void testSetProperty() throws InvocationTargetException, IllegalAccessException {
        Person person = new Person();
        System.out.println(person);

        BeanUtils.setProperty(person,"name","张三");
        BeanUtils.setProperty(person,"age",18);
        System.out.println(person);
    }

    /**
     * 读取字段属性值
     */
    @Test
    public void testGetProperty() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        Person person = new Person("李四","北京",20);

        String name = BeanUtils.getProperty(person, "name");
        String age = BeanUtils.getProperty(person, "age");

        System.out.println(name);
        System.out.println(age);
    }


}
