package com.lyp.learn.base.classs.pack1;

/**
 * 外部类
 */
class Outer {
    private String name="Hello World";

    public int outField1 = 1;
    protected int outField2 = 2;
    int outField3 = 3;
    private int outField4 = 4;

    public void print(){//定义外部类方法
        new Inner().say();//通过内部类的实例化对象调用方法
    }

    public void getValue(){
        // 在外部类对象内部，直接通过 new InnerClass(); 创建内部类对象
        Inner innerObj = new Inner();
        System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
        System.out.println("其内部类的 field1 字段的值为: " + innerObj.field1);
        System.out.println("其内部类的 field2 字段的值为: " + innerObj.field2);
        System.out.println("其内部类的 field3 字段的值为: " + innerObj.field3);
        System.out.println("其内部类的 field4 字段的值为: " + innerObj.field4);
    }


    /**
     * 成员内部类
     */
    public class Inner{
        private String name = "Hi";

        public int field1 = 5;
        protected int field2 = 6;
        int field3 = 7;
        private int field4 = 8;
        // 编译错误！成员内部类中不能定义 static 属性
        //static int field5 = 5;

        public void say(){
            //内部类中如何区分多层同名变量
            String name = "iiiii";
            System.out.println(name);
            System.out.println(this.name);
            System.out.println(Outer.this.name);
            System.out.println();
        }

        public void getFieldValue(){
            System.out.println("创建 " + this.getClass().getSimpleName() + " 对象");
            System.out.println("其外部类的 outField1 字段的值为: " + outField1);
            System.out.println("其外部类的 outField2 字段的值为: " + outField2);
            System.out.println("其外部类的 outField3 字段的值为: " + outField3);
            System.out.println("其外部类的 outField4 字段的值为: " + outField4);
        }

        public Outer getOuterClass(){
            return Outer.this;
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

        Outer.Inner inner = outer.new Inner();
        inner.say();
        inner.getFieldValue();
        System.out.println("---------------");
        System.out.println(inner.getOuterClass());
    }
}
