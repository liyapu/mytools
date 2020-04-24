package com.lyp.learn.base.reflect;

import org.testng.collections.Lists;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-24 15:42
 */
public class ClassTest {

    public static void main(String[] args) {

        /**
         *  class类提供了isPrimitive()方法
         *  这个方法判断指定的 Class 对象是否表示一个基本类型或者void类型，为什么要把void放到这个方法中我也不太清楚，
         *  如果Class对象为表示八个基本类型和void则为true，否则false。
         *  所以以后我们可以直接使用这个方法来判断对象是不是基本类型。
         *
         *  注意基本类型的包装类不属于基本类型，调用这个方法的返回值为false
         */
        System.out.println("\r\n---------- 基本类型判断 ----isPrimitive-----");
        System.out.println(byte.class.isPrimitive());
        System.out.println(short.class.isPrimitive());
        System.out.println(int.class.isPrimitive());
        System.out.println(long.class.isPrimitive());

        System.out.println(float.class.isPrimitive());
        System.out.println(double.class.isPrimitive());
        System.out.println(boolean.class.isPrimitive());
        System.out.println(char.class.isPrimitive());

        System.out.println(void.class.isPrimitive());

        System.out.println("\r\n---------- 包装类型判断 ------isPrimitive---");
        System.out.println(Byte.class.isPrimitive());
        System.out.println(Short.class.isPrimitive());
        System.out.println(Integer.class.isPrimitive());
        System.out.println(Long.class.isPrimitive());

        System.out.println(Float.class.isPrimitive());
        System.out.println(Double.class.isPrimitive());
        System.out.println(Boolean.class.isPrimitive());
        System.out.println(Character.class.isPrimitive());

        System.out.println(Void.class.isPrimitive());

        System.out.println(BigInteger.class.isPrimitive());
        System.out.println(BigDecimal.class.isPrimitive());
        System.out.println(ClassTest.class.isPrimitive());
        System.out.println(Class.class.isPrimitive());

        System.out.println("\r\n---------- 基本类型判断 ----getName-------getSimpleName-------getTypeName-------");
        System.out.println(byte.class.getName() + "  " + byte.class.getSimpleName() + "  " + byte.class.getTypeName());
        System.out.println(short.class.getName() + "  " + short.class.getSimpleName() + "  " + short.class.getTypeName());
        System.out.println(byte.class.getName() + "  " + byte.class.getSimpleName() + "  " + byte.class.getTypeName());
        System.out.println(int.class.getName() + "  " + int.class.getSimpleName() + "  " + int.class.getTypeName());
        System.out.println(long.class.getName() + "  " + long.class.getSimpleName() + "  " + long.class.getTypeName());

        System.out.println(float.class.getName() + "  " + float.class.getSimpleName() + "  " + float.class.getTypeName());
        System.out.println(double.class.getName() + "  " + double.class.getSimpleName() + "  " + double.class.getTypeName());
        System.out.println(boolean.class.getName() + "  " + boolean.class.getSimpleName() + "  " + boolean.class.getTypeName());
        System.out.println(char.class.getName() + "  " + char.class.getSimpleName() + "  " + char.class.getTypeName());

        System.out.println(void.class.getName() + "  " + void.class.getSimpleName() + "  " + void.class.getTypeName());


        System.out.println("\r\n---------- 包装类型判断----getName-------getSimpleName-------getTypeName----------");
        System.out.println(Byte.class.getName() + "  " + Byte.class.getSimpleName() + "  " + Byte.class.getTypeName());
        System.out.println(Short.class.getName() + "  " + Short.class.getSimpleName() + "  " + Short.class.getTypeName());
        System.out.println(Integer.class.getName() + "  " + Integer.class.getSimpleName() + "  " + Integer.class.getTypeName());
        System.out.println(Long.class.getName() + "  " + Long.class.getSimpleName() + "  " + Long.class.getTypeName());

        System.out.println(Float.class.getName() + "  " + Float.class.getSimpleName() + "  " + Float.class.getTypeName());
        System.out.println(Double.class.getName() + "  " + Double.class.getSimpleName() + "  " + Double.class.getTypeName());
        System.out.println(Boolean.class.getName() + "  " + Boolean.class.getSimpleName() + "  " + Boolean.class.getTypeName());
        System.out.println(Character.class.getName() + "  " + Character.class.getSimpleName() + "  " + Character.class.getTypeName());

        System.out.println(Void.class.getName() + "  " + Void.class.getSimpleName() + "  " + Void.class.getTypeName());

        System.out.println(BigInteger.class.getName() + "  " + BigInteger.class.getSimpleName() + "  " + BigInteger.class.getTypeName());
        System.out.println(BigDecimal.class.getName() + "  " + BigDecimal.class.getSimpleName() + "  " + BigDecimal.class.getTypeName());
        System.out.println(ClassTest.class.getName() + "  " + ClassTest.class.getSimpleName() + "  " + ClassTest.class.getTypeName());
        System.out.println(Class.class.getName() + "  " + Class.class.getSimpleName() + "  " + Class.class.getTypeName());


        /**
         * 我们也来扩展一下其他的知识点。
         *
         * （2）class类也提供了判断是不是数组类型方法isArray()。
         * （3）getName()返回此 Class对象表示的实体名称。
         *   如果此类对象表示的引用类型不是数组类型，则返回该类的二进制名称
         *   如果此类对象表示基本类型或void，则返回的名称是一个 String 字符串
         *   如果此类对象表示一类数组，则名称的内部形式由元素类型的名称组成，其后一个或多个“[”字符表示数组的深度嵌套。
         *   关于第三点中说的元素类型，列出来一张元素类型表：
         *            元素类型              编码
         *            boolean               Z
         *            byte                   B
         *            char                   C
         *            class or interface     Lclassname
         *            double                 D
         *            float                  F
         *            int                    I
         *            long                   J
         *            short                  S
         *
         *
         */

        System.out.println("\r\n---------- 基本类型判断 ----array getName()-----");
         byte[] bArr = new byte[] {1,2,3};
         int[] iArr = new int[] {1,2,3};
         long[] lArr = new long[] {1,2,3};
         String[] sArr = new String[] {"a","b","c"};

         Dog[] dogArr = new Dog[2];
         dogArr[0] = Dog.of("小黑",2);
         dogArr[1] = Dog.of("警犬",5);

        System.out.println(bArr.getClass().getName());
        System.out.println(iArr.getClass().getName());
        System.out.println(lArr.getClass().getName());
        System.out.println(sArr.getClass().getName());
        System.out.println(dogArr.getClass().getName());


        System.out.println("\r\n---------- 包装类型判断 ----array getName()-----");
        Byte[] bArr2 = new Byte[] {1,2,3};
        Integer[] iArr2 = new Integer[] {1,2,3};
        Long[] lArr2 = new Long[] {1L,2L,3L};
        String[] sArr2 = new String[] {"a","b","c"};


        System.out.println(bArr2.getClass().getName());
        System.out.println(iArr2.getClass().getName());
        System.out.println(lArr2.getClass().getName());
        System.out.println(sArr2.getClass().getName());



        System.out.println("\r\n---------- 包装类型判断 ----List getName()-----");
        List<Integer> intList = Lists.newArrayList(2,3,4);
        List<Long> longList = Lists.newArrayList(2L,3L,4L);
        List<String> strList = Lists.newArrayList("aa","bb","cc");
        List<Dog> dogList = Lists.newArrayList(Dog.of("小黑",2),Dog.of("小花",26));

        System.out.println(intList.getClass().getName());
        System.out.println(longList.getClass().getName());
        System.out.println(strList.getClass().getName());
        System.out.println(dogList.getClass().getName());

        /**
         *  判断数组中元素类型方法 getComponentType()
         *     getComponentType()方法返回表示数组的组件类型的Class，也就是说返回数组的每个元素的类型。
         *     如果此类不表示数组类，则此方法返回null
         *
         */
        System.out.println("\r\n---------- 基本类型判断 ----array getComponentType()-----");

        System.out.println(bArr.getClass().getComponentType());
        System.out.println(iArr.getClass().getComponentType());
        System.out.println(lArr.getClass().getComponentType());
        System.out.println(sArr.getClass().getComponentType());

        System.out.println(int.class.getClass().getComponentType());
        System.out.println(Integer.class.getClass().getComponentType());

        System.out.println(bArr2.getClass().getComponentType());
        System.out.println(iArr2.getClass().getComponentType());
        System.out.println(lArr2.getClass().getComponentType());
        System.out.println(sArr2.getClass().getComponentType());
        System.out.println(dogArr.getClass().getComponentType());



    }
}

class Dog{
    private String name;
    private int age;

    public Dog(){

    }

    public Dog(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static Dog of(String name,int age){
        return new Dog(name,age);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
