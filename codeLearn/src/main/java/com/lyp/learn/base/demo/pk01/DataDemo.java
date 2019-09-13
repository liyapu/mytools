package com.lyp.learn.base.demo.pk01;

public class DataDemo {
    public static void main(String[] args) {
        /**
         * 二进制表示
         * 二进制： 由0和1两个数字组成
         * 从 Java 7 开始， 加上前缀 0b 或 0B 就可以写二进制数
         */
        int a1 = 0b10;
        int a2 =0B10;
        System.out.println("二进制表示");
        System.out.println(a1);
        System.out.println(a2);
        System.out.println();

        /**
         * 八进制表示
         * 八进制： 由0-7数字组成，为了区分与其他进制的数字区别，开头都是以0开始
         * 前缀是一个0
         */
        int b1 = 010;
        System.out.println("八进制表示");
        System.out.println(b1);
        System.out.println();

        /**
         * 十进制表示
         * 十进制： 都是以0-9这九个数字组成，不能以0开头
         */
        int c1 = 10;
        System.out.println("十进制表示");
        System.out.println(c1);
        System.out.println();

        /**
         * 十六进制
         * 十六进制：由0-9和A-F组成。为了区分于其他数字的区别，开头都是以0x或0X开始。
         * 十六进制的特点：由0~9 a(10) b(11) c(12) d(13) e(14) f(15) 表示。
         * 不区分大小写，比如 A a 都表示10
         */
        int d1 = 0x10;
        int d2 = 0X10;
        int d3 = 0xA;
        int d4 = 0Xa;
        int d5 = 0xAb0;
        int d6 = 0XaB0;
        System.out.println(" 十六进制");
        System.out.println(d1);
        System.out.println(d2);
        System.out.println(d3);
        System.out.println(d4);
        System.out.println(d5);
        System.out.println(d6);
        System.out.println();

        /**
         * 数字加下划线
         * 从 Java 7 开始，还可以为数字字面量加下划线，这些下划线只是为丫让人更易读。
         * Java 编译器会去除这些下划线
         * 给数字，二进制加
         */
        int f1 = 10_000_000;
        int f2 = 10_100_300;
        int f3 = 0b10_0000;
        int f4 = 0B100_1111_0011;
        System.out.println("数字加下划线");
        System.out.println(f1);
        System.out.println(f2);
        System.out.println(f3);
        System.out.println(f4);
        System.out.println();

        int g1 = 33;
        System.out.println("进制格式化输出");
        //二进制输出
        System.out.println(Integer.toBinaryString(g1));
        //八进制输出
        System.out.printf("%o",g1);
        System.out.println();
        //按10位八进制输出，向右靠齐，左边用0补齐
        System.out.printf("%010o",g1);

        System.out.println();
        //十六进制输出
        System.out.printf("%x",g1);
        System.out.println();
        //按10位十六进制输出，向右靠齐，左边用0补齐
        System.out.printf("%010x",g1);

        System.out.println();
    }
}
