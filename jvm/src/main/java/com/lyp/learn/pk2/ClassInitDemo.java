package com.lyp.learn.pk2;

/**
 * @author: liyapu
 * @description:
 * @date 2020-04-30 20:49
 */
public class ClassInitDemo {
    private static int num = 6;

    static {
        num = 8;
        number = 10;
        System.out.println(num);

        // 非法前向引用
//        System.out.println(number); // ByteCode
    }
    // linking 之 prepare： number = 0 --> 10 ---> 20
    // 静态代码块和静态变量，从上到下依次执行
    private  static  int number = 20;

    public static void main(String[] args) {
        System.out.println(num);
        System.out.println(number);
    }
}
