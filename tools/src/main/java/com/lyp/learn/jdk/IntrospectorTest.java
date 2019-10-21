package com.lyp.learn.jdk;

import com.lyp.learn.bean.User;
import org.junit.jupiter.api.Test;

import java.beans.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: liyapu
 * @description:
 * @date 2019-10-17 15:27
 *
 * Introspector(内省)是jdk提供的用于描述Java bean支持的属性、方法以及事件的工具；
 * 利用此类可得到BeanInfo接口的实现对象:
 *
 * 一言以蔽之： Introspector 是操作 javaBean 的 API，用来访问某个属性的 getter/setter 方法。
 * 对于一个标准的 javaBean 来说，它包括属性、get 方法和 set 方法，这是一个约定俗成的规范。
 * 为此 sun 提供了 Introspector 工具包，来使开发者更好或者更灵活的操作 javaBean。
 *
 * 内省一般写业务代码的时候是用不到的，大部分是在写一些框架或者工具的时候会用到。
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
//        在Object类时候停止检索，可以选择在任意一个父类停止
        //上述的意思是，只得到User类的BeanInfo,（stop）不要Object的BeanInfo,就可以忽略掉 Object中的getClass属性了
//        BeanInfo beanInfo = Introspector.getBeanInfo(User.class, Object.class);
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

    /**
     * 设置某个属性
     */
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

    /**
     * 获取某个属性
     */
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

    @Test
    public void testInvoke() throws IntrospectionException, InvocationTargetException, IllegalAccessException {
        User user = new User("jack", 21);

        String propertyName = "name";
        PropertyDescriptor namePd = new PropertyDescriptor(propertyName, User.class);

        System.out.println("名字：" + namePd.getReadMethod().invoke(user));
        namePd.getWriteMethod().invoke(user, "tom");
        System.out.println("名字：" + namePd.getReadMethod().invoke(user));

        System.out.println("========================================");

        String agePropertyName = "age";
        PropertyDescriptor agePd = new PropertyDescriptor(agePropertyName, User.class);

        System.out.println("年龄：" + agePd.getReadMethod().invoke(user));
        agePd.getWriteMethod().invoke(user, 22);
        System.out.println("年龄：" + agePd.getReadMethod().invoke(user));

    }

    /**
     * 利用反射
     */
    @Test
    public void testReflect() throws Exception {
        //模拟从 xml 中获得到了数据
        //<bean id="user" class="com.lyp.learn.bean.User">
        //    <property name="name" value="chengfan" />
        //    <property name="age" value="22" />
        //</bean>

        String clazz = "com.lyp.learn.bean.User";
        Map<String, Object> properties = new HashMap<>();

        properties.put("Name", "chengfan");
        properties.put("Age", 22);

        reflect(clazz, properties);
    }

    public void reflect(String clazz, Map<String, Object> properties) throws Exception {
        //反射创建实例
        Class target = Class.forName(clazz);
        Object bean = target.newInstance();

        //初始化容器时，调用setter注入
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String _setName = "set" + entry.getKey();
            if ("name".equalsIgnoreCase(entry.getKey())) {
                Method setMethod = target.getMethod(_setName, String.class);
                setMethod.invoke(bean, entry.getValue().toString());
            } else {
                Method setMethod = target.getMethod(_setName, int.class);
                setMethod.invoke(bean, Integer.parseInt(entry.getValue().toString()));
            }
        }

        // show
        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            String _getName = "get" + entry.getKey();
            if ("name".equalsIgnoreCase(entry.getKey())) {
                Method setMethod = target.getMethod(_getName);
                System.out.println(setMethod.invoke(bean));
            } else {
                Method setMethod = target.getMethod(_getName);
                System.out.println(setMethod.invoke(bean));
            }
        }
    }

    /**
     * 内省的方式
     */
    @Test
    public void testIntrospector1() throws Exception {
        //模拟从 xml 中获得到了数据
        //<bean id="user" class="com.lyp.learn.bean.User">
        //    <property name="name" value="chengfan" />
        //    <property name="age" value="22" />
        //</bean>

        String clazz = "com.lyp.learn.bean.User";
        Map<String, Object> properties = new HashMap<>();

        properties.put("name", "chengfan");
        properties.put("age", 22);
        introspector(clazz, properties);
    }

    public void introspector(String clazz, Map<String, Object> properties) throws Exception {
        //反射创建实例
        Class target = Class.forName(clazz);
        Object bean = target.newInstance();

        BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
        PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor pd : pds) {
            Method setMethod = pd.getWriteMethod();
            String fieldName = pd.getName();

            if ("name".equalsIgnoreCase(fieldName)) {
                setMethod.invoke(bean, properties.get(fieldName));
            } else if ("age".equalsIgnoreCase(fieldName)){
                setMethod.invoke(bean, properties.get(fieldName));
            }
        }


        // show
        for (PropertyDescriptor pd : pds) {
            System.out.println(pd.getReadMethod().invoke(bean));
        }
    }
}
