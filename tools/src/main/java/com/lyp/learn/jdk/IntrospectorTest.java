package com.lyp.learn.jdk;

import com.lyp.learn.bean.User;
import org.junit.jupiter.api.Test;

import java.beans.*;
import java.lang.reflect.Method;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 15:27
 *
 * Introspector(内省)是jdk提供的用于描述Java bean支持的属性、方法以及事件的工具；
 * 利用此类可得到BeanInfo接口的实现对象:
 *
 */
public class IntrospectorTest {

    /**
     * getBeanDescriptor()返回的BeanDescriptor提供了java bean的一些全局的信息，如class类型、类名称等。
     *
     * getPropertyDescriptors()返回PropertyDescriptor[]，PropertyDescriptor描述了java bean中一个属性和它们的getter & setter方法的SoftReference。
     */
    @Test
    public void test01() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(User.class);
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();

        System.out.println(beanDescriptor.getBeanClass());
        System.out.println(beanDescriptor.getName());
        System.out.println(beanDescriptor.getDisplayName());
        System.out.println(beanDescriptor.getShortDescription());
        System.out.println("--------------------");

        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();

            System.out.println(propertyDescriptor.getName());
            System.out.println(propertyDescriptor.getDisplayName());
            System.out.println(propertyDescriptor.getShortDescription());
            System.out.println(readMethod);
            System.out.println(writeMethod);
        }
    }


    @Test
    public void test(){
        Integer a1 = new Integer(126);
        Integer a2 = new Integer(126);
        System.out.println(a1 == 126);
    }
}
