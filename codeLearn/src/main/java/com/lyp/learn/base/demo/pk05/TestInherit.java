package com.lyp.learn.base.demo.pk05;

/**
 * 父类
 * @author gacl
 */
class FatherClass {
    public int value;

    public void f() {
        value = 100;
        System.out.println("父类的value属性值=" + value);
    }
}

/**
 * 子类ChildClass从父类FatherClass继承
 * @author gacl
 */
class ChildClass extends FatherClass {
    /**
     * 子类除了继承父类所具有的valu属性外，自己又另外声明了一个value属性，
     * 也就是说，此时的子类拥有两个value属性。
     */
    public int value;

    /**
     * 在子类ChildClass里面重写了从父类继承下来的f()方法里面的实现，即重写了f()方法的方法体。
     */
    public void f() {
        super.f();//使用super作为父类对象的引用对象来调用父类对象里面的f()方法
        value = 200;//这个value是子类自己定义的那个value，不是从父类继承下来的那个value
        System.out.println("子类的value属性值=" + value);
        System.out.println(value);//打印出来的是子类自定义的那个value的值，这个值是200
        /**
         * 打印出来的是父类里面的value值，由于子类在重写从父类继承下来的f()方法时，
         * 第一句话“super.f();”是让父类对象的引用对象调用父类对象的f()方法，
         * 即相当于是这个父类对象自己调用f()方法去改变自己的value属性的值，由0变了100。
         * 所以这里打印出来的value值是100。
         */
        System.out.println(super.value);
    }
}

/**
 * 测试类
 * @author gacl
 */
public class TestInherit {
    public static void main(String[] args) {
        ChildClass cc = new ChildClass();
        cc.f();
    }
}
