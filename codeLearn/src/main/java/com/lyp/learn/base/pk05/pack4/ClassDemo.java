package com.lyp.learn.base.pk05.pack4;

/**
 * 外部类
 */
class Outer {
    private String name = "lili";

    public void show(){
        int phoneNum = 10086;

        class Inner {
            final int age = 20;
            public void getAge() {
                //从内部类引用的本地变量必须是最终变量或实际上的最终变量
                //phoneNum = 110;
                int miniPhoneNum = phoneNum;//可以访问

                System.out.println(age);
                System.out.println(phoneNum);
                System.out.println(name);
            }
        }
        new Inner().getAge();
    }


}


public class ClassDemo {
    public static void main(String[] args) {
        Outer outer = new Outer();
        outer.show();
    }
}
