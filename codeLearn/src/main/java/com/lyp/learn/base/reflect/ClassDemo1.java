package com.lyp.learn.base.reflect;

public class ClassDemo1 {

    /**
     * 得到 Class 的三种方式
     */
    public static void main(String[] args) throws Exception {
        /**
         * 1、通过对象调用 getClass() 方法来获取,通常应用在：比如你传过来一个 Object
         * 类型的对象，而我不知道你具体是什么类，用这种方法
         *
         */
        Person p1 = new Person();
        Class c1 = p1.getClass();
        System.out.println(c1);

        /**
         * 2、直接通过 类名.class 的方式得到,该方法最为安全可靠，程序性能更高
         * 这说明任何一个类都有一个隐含的静态成员变量 class
         *
         */
        Class c2 = Person.class;
        System.out.println(c2);
        System.out.println();

        Class intClazz = int.class;
        Class IntegerClazz2 = Integer.class;
        int count = 10;
        Integer sum = 100;
        Class IntegerClazz22 = sum.getClass();
        System.out.println(IntegerClazz22);
        //primitive wrapper classes的TYPE 语法
        Class IntegerClazz3 = Integer.TYPE;
        Class BooleanClazz4 = Boolean.TYPE;
        System.out.println(intClazz);
        System.out.println(IntegerClazz2);
        System.out.println(IntegerClazz2);
        System.out.println(IntegerClazz3);
        System.out.println();
        System.out.println(BooleanClazz4);
        System.out.println();

        /**
         * 3、通过 Class 对象的 forName() 静态方法来获取，用的最多
         * 但可能抛出 ClassNotFoundException 异常
         *
         */
        Class c3 = Class.forName("com.lyp.learn.base.reflect.Person");
        System.out.println(c3);

        System.out.println();
        System.out.println(c1 == c2);
        System.out.println(c2 == c3);
        System.out.println();

        System.out.println(c1.equals(c2));
        System.out.println(c2.equals(c3));
    }

}
