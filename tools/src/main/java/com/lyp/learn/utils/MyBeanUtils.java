package com.lyp.learn.utils;


import com.lyp.learn.bean.User;
import com.lyp.learn.bean.UserVo;
import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 优化方案
 * 最优用时 cglib  < org.springframework.cglib.beans.BeanCopier < spring BeanUtils < apache commons
 */
public class MyBeanUtils {

    public MyBeanUtils(){

    }

    /**
     * 复制相同字段的source对象到target对象中
     * 或者直接使用 spring的  BeanUtils.copyProperties(source,target);
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties(Object source, Object target){
        copyProperties_cglib(source,target);
    }

    /**
     * 复制相同字段的source对象到 targetClass类对象中
     * @param source
     * @param targetClass
     * @param <T>
     * @return
     */
    public static <T> T copyProperties(Object source,Class<T> targetClass){
        T t;
        try {
            t = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(String.format("Create new instance of %s failed source: %s. ", targetClass,source.getClass(), e));
        }
        BeanUtils.copyProperties(source,t);
        return t;
    }

    /**
     * cglib
     * @param source 源对象
     * @param target 目标对象
     */
    public static void copyProperties_cglib(Object source, Object target){
        //第三个参数表示是否使用转换器，false表示不使用，true表示使用
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
    }

    public static void copyProperties_cglib_convert(Object source, Object target){
        //第三个参数表示是否使用转换器，false表示不使用，true表示使用
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), true);
        copier.copy(source, target, new Converter() {
            @Override
            public Object convert(Object value, Class target, Object context) {
                String s = value.toString();
                if (target.equals(int.class) || target.equals(Integer.class)) {
                    return Integer.parseInt(s);
                }
                if (target.equals(long.class) || target.equals(Long.class)) {
                    return Long.parseLong(s);
                }
                if (target.equals(float.class) || target.equals(Float.class)) {
                    return Float.parseFloat(s);
                }
                if (target.equals(double.class) || target.equals(Double.class)) {
                    return Double.parseDouble(s);
                }
                if(target.equals(Date.class)){
                    while(s.indexOf("-")>0){
                        s = s.replace("-", "/");
                    }
                    return  new Date(s);
                }
                if(target.equals(BigDecimal.class)){
                    if(!StringUtils.isEmpty(s)&&!s.equals("NaN")){
                        return  new BigDecimal(s);
                    }
                }
                return value ;
            }
        });
    }

    public static <T> T copyProperties_class_cglib(Object source,Class<T> targetClass){
        T t;
        try {
            t = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(String.format("Create new instance of %s failed source: %s. ", targetClass,source.getClass(), e));
        }
        BeanCopier copier = BeanCopier.create(source.getClass(), targetClass, false);
        copier.copy(source, t, null);
        return t;
    }

    /**
     * spring中cglib
     * @param source
     * @param target
     */
    public static void copyProperties_spring_cglib(Object source, Object target){
        org.springframework.cglib.beans.BeanCopier copier = org.springframework.cglib.beans.BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
     }

     public static void copyProperties_spring_beanUtils(Object source,Object target){
         BeanUtils.copyProperties(source,target);
     }

    public static void copyProperties_spring_beanUtils_ignore(Object source,Object target,String... ignoreProperties){
        BeanUtils.copyProperties(source,target,ignoreProperties);
    }

    public static <T> T copyProperties_class_spring_beanUtils(Object source,Class<T> targetClass){
        T t;
        try {
            t = targetClass.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(String.format("Create new instance of %s failed source: %s. ", targetClass,source.getClass(), e));
        }
        BeanUtils.copyProperties(source,t);
        return t;
    }

    /**
     * apache commons org.apache.commons.BeanUtils
     * 内部调用时，颠倒一下: 第一个参数是：目标对象
     *
     * 利用apache commons-beanutils的开源实现
     *     BeanUtils.copyProperties(dst, src)。方法能够将源对象和目标对象中相同名称的属性值复制过去。
     *     注意的是参数前面的是目标对象，后面是源对象。
     *     使用该方法需要注意：
     *     不能将入口方法与源对象、目标对象之一放在同一源文件之内，否者将没有任何效果
     * @param source
     * @param target
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties_commons_beanUtils(Object source,Object target) throws InvocationTargetException, IllegalAccessException {
        org.apache.commons.beanutils.BeanUtils.copyProperties(target,source);
    }

    /**
     * apache commons org.apache.commons.PropertyUtils
     * 内部调用时，颠倒一下: 第一个参数是：目标对象
     * @param source
     * @param target
     * @throws InvocationTargetException
     * @throws IllegalAccessException
     */
    public static void copyProperties_commons_propertyUtils(Object source,Object target) throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        org.apache.commons.beanutils.PropertyUtils.copyProperties(target,source);
    }

    /**
     * 拷贝实体集合，sourceList
     *只支持自定义实体集合拷贝
     *应用场景：DTO <=> DO 等
     */
    public static void copyPropertiesList(List sourceList, List targetList, Class clazz) throws InstantiationException,IllegalAccessException {
        if (CollectionUtils.isEmpty(sourceList)) {
            throw new IllegalArgumentException();
        }
        for (Object items : sourceList) {
            Object target = clazz.newInstance();
            BeanUtils.copyProperties(items, target);
            targetList.add(target);
        }
    }


    /**
     * Bean --> Map 1
     * 利用Introspector和PropertyDescriptor 将Bean --> Map
     *
     * @param obj
     */
    public static Map<String, Object> transBean2Map(Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException {

        if (obj == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();

            // 过滤class属性
            if (!key.equals("class")) {
                // 得到property对应的getter方法
                Method getter = property.getReadMethod();
                Object value = getter.invoke(obj);

                map.put(key, value);
            }

        }
        return map;
    }

    /**
     * Map --> Bean 1:
     * 利用Introspector,PropertyDescriptor实现 Map --> Bean
     *
     * @param map
     * @param obj
     */
    public static void transMap2Bean(Map<String, Object> map, Object obj) throws IntrospectionException, InvocationTargetException, IllegalAccessException, IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
        PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();

        for (PropertyDescriptor property : propertyDescriptors) {
            String key = property.getName();
            if (map.containsKey(key)) {
                Object value = map.get(key);
                // 得到property对应的setter方法
                Method setter = property.getWriteMethod();
                setter.invoke(obj, value);
            }
        }
    }
    /**
     * Map --> Bean 2:
     * 利用org.apache.commons.beanutils 工具类实现 Map --> Bean
     *
     * @param map
     * @param obj
     */
    public static void transMap2Bean2(Map<String, Object> map, Object obj) throws InvocationTargetException, IllegalAccessException {
        if (map == null || obj == null) {
            return;
        }
        org.apache.commons.beanutils.BeanUtils.populate(obj, map);
    }


    public static User getUser(){
        User user = new User();
        user.setId(100);
        user.setName("张三");
        user.setAge(18);
        user.setAddress("河南省 商丘市");
        user.setTelephone("13566668888");
        user.setHeight(170);
        user.setWeight(62);
        return user;
    }

    @Test
    public void testCopyProperties() throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        User user = getUser();

        UserVo userVo1 = new UserVo();
        copyProperties_cglib(user,userVo1);
        System.out.println("userVo1 :" + userVo1);

        UserVo userVo2 = new UserVo();
        copyProperties_spring_cglib(user,userVo2);
        System.out.println("userVo2 :" + userVo2);

        UserVo userVo3 = new UserVo();
        copyProperties_spring_beanUtils(user,userVo3);
        System.out.println("userVo3 :" + userVo3);

        UserVo userVo4 = new UserVo();
        copyProperties_commons_beanUtils(user,userVo4);
        System.out.println("userVo4 :" + userVo4);

        UserVo userVo5 = new UserVo();
        copyProperties_commons_propertyUtils(user,userVo5);
        System.out.println("userVo5 :" + userVo5);
    }

    @Test
    public void testCopyPropertiesClass(){
        User user = getUser();

        UserVo userVo1 = copyProperties_class_cglib(user, UserVo.class);
        userVo1.setPhone(user.getTelephone());
        System.out.println(userVo1);

        UserVo userVo2 = copyProperties_class_spring_beanUtils(user,UserVo.class);
        userVo2.setPhone(user.getTelephone());
        System.out.println(userVo2);

        copyProperties_class_cglib(user, LocalDate.class);
    }


    /**
     * 复制忽略某些字段
     */
    @Test
    public void testCopyProperties_spring_beanUtils_ignore(){
        User user = getUser();

        UserVo userVo1 = new UserVo();
        copyProperties(user,userVo1);
        System.out.println("userVo1 :" + userVo1);

        UserVo userVo2 = new UserVo();
        copyProperties_spring_beanUtils_ignore(user,userVo2,"id","name");
        System.out.println("userVo2 :" + userVo2);

        UserVo userVo3 = new UserVo();
        String[] ignoreProperties = new String[]{"name","age"};
        copyProperties_spring_beanUtils_ignore(user,userVo3,ignoreProperties);
        System.out.println("userVo3 :" + userVo3);
    }

    @Test
    public void testTransBean2Map() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        User user = getUser();
        Map<String, Object> userMap = transBean2Map(user);
        System.out.println(userMap);
    }

    @Test
    public void testTransMap2Bean() throws IllegalAccessException, IntrospectionException, InvocationTargetException {
        Map<String,Object> userMap = new HashMap<>();
        userMap.put("id",1);
        userMap.put("name","张三");
        userMap.put("age",68);
        userMap.put("address","河南省 商丘市");
        userMap.put("telephone","13566668888");
        userMap.put("height",175);
        userMap.put("weight",62);

        User user1 = new User();
        transMap2Bean(userMap,user1);
        System.out.println("user1 :" + user1);

        User user2 = new User();
        transMap2Bean2(userMap,user2);
        System.out.println("user2 :" + user2);

    }


}
