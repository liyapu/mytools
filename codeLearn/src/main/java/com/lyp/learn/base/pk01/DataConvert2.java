package com.lyp.learn.base.pk01;

public class DataConvert2 {
    public static void main(String[] args) {
        /**
         * int i1 赋值给 byte 类型的 b1
         * 需要强制类型转换，
         */
        int i1 = 7;
        byte b1 = (byte) i1;
        System.out.println(b1);
        System.out.println();

        /**
         * i2的二进制表示
         * 0000 0000  1000 0010
         * 转换为byte,一个字节，剩下
         * 1000 0010
         * 计算机中数字是，补码形式存在，
         * 最高位是1，表示负数
         * 最高位不变，其它位取反
         * 1111 1101
         * 减一
         * 1111 1100
         * 等于 -126
         *
         */
        int i2 =130;
        System.out.println(Integer.toBinaryString(i2));
        byte b2 = (byte) i2;
        System.out.println(b2);
        System.out.println();


        /**
         * i3的二进制表示
         * 0000 0001 0000 0100
         * 变为byte ,一个字节剩余 0000 0100
         * 最高位是0，表示正数
         * 4
         */
        int i3 = 260;
        byte b3 = (byte) i3;
        System.out.println(Integer.toBinaryString(i3));
        System.out.println(b3);
        System.out.println();

        /**
         * i2 除以 i1,除不尽，有小数
         * 由于结果类型时 int,小数舍弃，结果为整数部分
         */
        int i4 = i2/i1;
        System.out.println(i4);
        System.out.println();

        /**
         * i2,i1均为整数int,
         * 但是通过（double）i2,将i2转换为匿名变量，该变量类型为double,
         * 但是注意，i2依旧是int类型，而不是double
         * 即为double/int，结果自然是double类型的
         *
         */
        double d1 = (double)i2/i1;
        System.out.println(i1);
        System.out.println(d1);
    }
}
