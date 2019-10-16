package com.lyp.learn.utils;


import com.lyp.learn.bean.User;
import com.lyp.learn.bean.UserVo;
import net.sf.cglib.beans.BeanCopier;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;


/**
 */
public class MyBeanUtils {

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
        BeanCopier copier = BeanCopier.create(source.getClass(), target.getClass(), false);
        copier.copy(source, target, null);
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


}
