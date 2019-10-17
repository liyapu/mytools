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


        System.out.println("----------beanDescriptor------------");
        BeanDescriptor beanDescriptor = beanInfo.getBeanDescriptor();
        System.out.println(beanDescriptor.getBeanClass());
        System.out.println(beanDescriptor.getName());
        System.out.println(beanDescriptor.getDisplayName());
        System.out.println(beanDescriptor.getShortDescription());
        System.out.println();

        System.out.println("---------methodDescriptors-----------");
        MethodDescriptor[] methodDescriptors = beanInfo.getMethodDescriptors();
        for(MethodDescriptor methodDescriptor : methodDescriptors){
            System.out.println(methodDescriptor.getDisplayName());
            System.out.println(methodDescriptor.getName());
            System.out.println(methodDescriptor.getMethod());

            ParameterDescriptor[] parameterDescriptors = methodDescriptor.getParameterDescriptors();
            if(parameterDescriptors != null && parameterDescriptors.length > 0){
                for(ParameterDescriptor parameterDescriptor : parameterDescriptors){
                    System.out.println(parameterDescriptor.getName());
                    System.out.println(parameterDescriptor.getDisplayName());
                }
            }
            System.out.println();
        }

        System.out.println("-------------propertyDescriptors-------------------");
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
            Method readMethod = propertyDescriptor.getReadMethod();
            Method writeMethod = propertyDescriptor.getWriteMethod();

            System.out.println(propertyDescriptor.getName());
            System.out.println(propertyDescriptor.getDisplayName());
            System.out.println(propertyDescriptor.getShortDescription());
            System.out.println(propertyDescriptor.getPropertyType());
            System.out.println(readMethod);
            System.out.println(writeMethod);
            System.out.println("----------------");
        }

    }

    @Test
    public  void setProperty()throws Exception{
        User user = new User();
        user.setId(100);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("河南省 商丘市");
        user.setTelephone("13566668888");
        user.setHeight(170);
        user.setWeight(62);

        String address = "address";
        String addressValue = "北京";

        PropertyDescriptor propDesc =new PropertyDescriptor(address,User.class);
        Method writeMethodAddress = propDesc.getWriteMethod();
        writeMethodAddress.invoke(user, addressValue);
        System.out.println("set address :" + user.getAddress());
    }

    @Test
    public void getProperty()throws Exception{
        User user = new User();
        user.setId(100);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("河南省 商丘市");
        user.setTelephone("13566668888");
        user.setHeight(170);
        user.setWeight(62);

        String address = "address";

        PropertyDescriptor proDescriptor =new PropertyDescriptor(address,User.class);
        Method methodGetAddress = proDescriptor.getReadMethod();
        Object userAddress = methodGetAddress.invoke(user);
        System.out.println("get address :" + userAddress.toString());
    }

}
