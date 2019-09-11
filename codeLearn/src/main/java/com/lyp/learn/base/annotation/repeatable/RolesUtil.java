package com.lyp.learn.base.annotation.repeatable;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2018-11-07 18:13
 */
public class RolesUtil {
    public static void getInfo(Class<?> clazz){
        boolean isRolesAnnotation = clazz.isAnnotationPresent(Roles.class);
        if(isRolesAnnotation){
            Roles roles = clazz.getAnnotation(Roles.class);
            Role[] roleArr = roles.value();
            System.out.println("此人拥有的角色是:");
            for(Role role : roleArr){
                System.out.println(role.value());
            }
        }else{
            System.out.println("没有角色注解！");
        }
    }
}
