package com.lyp.learn.base.classs.pack2;

/**
 * 外部类
 */
class Outer {
    private String name="Hello World";
    private static String staticAddress = "中国";

    public void print(){//定义外部类方法
        new InnerStatic().say();//通过内部类的实例化对象调用方法
    }

    public void getValue(){
        //创建静态内部类对象
        InnerStatic innerObj = new InnerStatic();
        System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
        System.out.println("其内部类的 field1 字段的值为: " + innerObj.field1);
        System.out.println("其内部类的 field2 字段的值为: " + innerObj.field2);
        System.out.println("其内部类的 field3 字段的值为: " + innerObj.field3);
        System.out.println("其内部类的 field4 字段的值为: " + innerObj.field4);
    }


    /**
     * 静态内部类
     */
    static class InnerStatic{
        private String name = "hi";

        public int field1 = 5;
        protected int field2 = 6;
        int field3 = 7;
        private int field4 = 8;
        // 静态内部类中可以定义 static 属性
        static int field5 = 5;


        public void say(){
            System.out.println(name);
            System.out.println(this.name);
            System.out.println();
        }

        public void getFieldValue(){
            System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
            //静态类中不能访问非静态属性
           // System.out.println("其外部类的 outField1 字段的值为: " + outField1);
             System.out.println("其外部类的 staticAddress 字段的值为: " + staticAddress);

        }
    }
}


public class ClassDemo {
    public static void main(String[] args) {
        Outer outer=new Outer();
        //调用外部类的方法
        outer.print();
        outer.getValue();
        System.out.println("-----------");

        Outer.InnerStatic inner = new Outer.InnerStatic();
        inner.say();
        inner.getFieldValue();
    }
}
