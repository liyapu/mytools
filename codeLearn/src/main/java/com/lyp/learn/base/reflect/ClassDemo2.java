package com.lyp.learn.base.reflect;

public class ClassDemo2 {
    public static void main(String[] args) throws Exception {
        Class studentClazz = Student.class;
        Class personClazz = Person.class;

        //获得类的加载器
        System.out.println(studentClazz.getClassLoader());
        System.out.println(personClazz.getClassLoader());
        System.out.println();

        //返回一个数组，数组中包含该类中所有公共类和接口类的对象
        System.out.println(studentClazz.getClasses());
        System.out.println(personClazz.getClasses());
        System.out.println();

        System.out.println(studentClazz.getClasses().length);
        System.out.println(personClazz.getClasses().length);



        System.out.println("-------------------------");

        //获得类的完整路径名字
        System.out.println(studentClazz.getName());
        //获得类的包
        System.out.println(studentClazz.getPackage());
        //获得类的名字
        System.out.println(studentClazz.getSimpleName());
        //获得当前类继承的父类的名字
        System.out.println(studentClazz.getSuperclass());
        System.out.println(personClazz.getSuperclass());
        System.out.println();

        //获得当前类实现或继承的接口
        System.out.println(studentClazz.getInterfaces());
        System.out.println(personClazz.getInterfaces());

        System.out.println(studentClazz.getInterfaces().length);
        System.out.println(personClazz.getInterfaces().length);

        System.out.println(studentClazz.getInterfaces()[0].getName());
        System.out.println(studentClazz.getInterfaces()[1].getName());

        System.out.println("===============================");
        /**
         * Student类必须有无参的构造方法
         * 若没有声明构造方法，JVM会默认为其声明一个无参的构造方法
         *
         * 若声明有参的构造方法，则必须显示声明一个无参构造方法,
         * 否则下面的 newInstance() 方法，报java.lang.NoSuchMethodException: com.lyp.learn.reflect.Student.<init>()
         */
        Student student2 = (Student) studentClazz.newInstance();
        System.out.println(student2.getStuName());
        System.out.println(student2.printMessage());
    }
}
