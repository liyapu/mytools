package com.lyp.learn.base.reflect;

import java.lang.reflect.Constructor;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-11 21:31
 */
public class ClassDemo3 {
    public static void main(String[] args) throws Exception {
        //直接new一个实例
        Person person = new Person();

        //通过newInstance()方法获得Person的实例
        //需要注意的是，在使用newInstance()方法的前提是该类必须要有无参构造方法
        Person person1 = Person.class.newInstance();
        System.out.println(person1.getFather());
        System.out.println();

        //在学习JAVAEE时候，newInstance()方法我们最常见于获取数据库驱动
        Class.forName("com.mysql.jdbc.Driver").newInstance();
        System.out.println();

        Class personClazz = Class.forName("com.lyp.learn.reflect.Person");
        Constructor personConstructor = personClazz.getConstructor(new Class[]{String.class, String.class, int.class, String.class, String.class, int.class, int.class});
        Person person2 = (Person) personConstructor.newInstance("张三","父爱如山赋值",10,"aaa","bbb",88,99);
        System.out.println(person2.getFather());
        System.out.println(person2.getName());



    }
}
