package com.lyp.learn.base.reflect;

import com.lyp.learn.base.threads.pk01.Object;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-01-11 21:31
 */
public class FieldDemo {
    public static void main(String[] args) throws Exception {
        Class studentClazz = new Student().getClass();
        Student student = (Student) studentClazz.newInstance();
        Object studentObj = studentClazz.newInstance();

        //getField  Object 类型的
        Field studentOfPersonFather = studentClazz.getField("father");
        studentOfPersonFather.set(studentObj,"重新赋值 父爱如山");
        System.out.println(studentOfPersonFather.get(studentObj));
        System.out.println();

        //getDeclaredField  Object 类型的
        Field studentOfHeight = studentClazz.getDeclaredField("height");
        studentOfHeight.set(studentObj,100);
        System.out.println(studentOfHeight.get(studentObj));
        System.out.println();


        //getDeclaredField  Student 类型的
        Field studentOfSchool = studentClazz.getDeclaredField("school");
        studentOfSchool.set(student,"河南大学");
        System.out.println(student.getSchool());
        System.out.println(studentOfSchool.get(student));
        System.out.println();


        //getDeclaredField  私有属性
        Field studentOfLevel = studentClazz.getDeclaredField("level");
        //Exception in thread "main" java.lang.IllegalAccessException:
        // Class FieldDemo can not access a member of class Student with modifiers "private"
        //studentOfLevel.set(studentObj,5);
        //System.out.println(studentOfLevel.get(studentObj));
        /**
         * 启用和禁用访问安全检查的开关，值为 true，则表示反射的对象在使用时应该取消 java 语言的访问检查；反之不取消
         * 使用反射机制可以打破封装性，导致了java对象的属性不安全。
         */
        studentOfLevel.setAccessible(true);
        studentOfLevel.set(studentObj,5);
        System.out.println(studentOfLevel.get(studentObj));
        System.out.println();

        /**
         * 父类的私有属性值，
         * 这里要注意的是直接通过反射获取子类的对象是不能得到父类的私有属性值的，
         * 必须根据反射获得的子类 Class 对象在调用  getSuperclass() 方法获取父类对象，
         * 然后在通过父类对象去获取父类的属性值
         */
        Class personClazz = studentClazz.getSuperclass();
        Person person = (Person) personClazz.newInstance();
        Field [] personDeclaredField = personClazz.getDeclaredFields();
        for(Field field : personDeclaredField){
            field.setAccessible(true);
            System.out.println(field.get(person));
        }
        System.out.println();


        Student student1 = (Student) Class.forName("com.lyp.learn.reflect.Student").newInstance();
        student1.setSchool("郑州大学啊");
        student1.setLevel(10);
        student1.setLevelName("十级 水平");
        student1.setName("三号学生");
        System.out.println(student1.printMessage());
        System.out.println(student1.getName());
        System.out.println(student1.getSchool());
    }


    @Test
    public void test1() throws Exception{
        testForTest1("com.lyp.learn.reflect.Student", "address", "ss");
        testForTest1("com.lyp.learn.reflect.Student", "weight", 100);

    }

    private void testForTest1(String className, String fieldName, Object val) throws Exception {
        Object obj = null;
        //1.创建className 对应类的对象
        Class clazz = Class.forName(className);
        //2.创建fieldName 对象字段的对象
        Field field = getField(clazz, fieldName);
        //3.为此对象赋值
        obj = clazz.newInstance();
        setFieldValue(obj, field, val);
        //4.获取此对象的值
        Object value = getFieldValue(obj,field);
        System.out.println(value);
    }

    public Object getFieldValue(Object obj, Field field) throws Exception{
        field.setAccessible(true);
        return field.get(obj);
    }

    public void setFieldValue(Object obj, Field field, Object val) throws Exception {
        field.setAccessible(true);
        field.set(obj, val);
    }

    public Field getField(Class clazz, String fieldName) throws Exception {
        Field field = null;
        for(Class clazz2 = clazz; clazz2 != Object.class;clazz2 = clazz2.getSuperclass()){
            try {
                field = clazz2.getDeclaredField(fieldName);
            } catch (NoSuchFieldException e) {
                //e.printStackTrace();
            } catch (SecurityException e) {
                //e.printStackTrace();
            }
        }
        return field;
    }
}
