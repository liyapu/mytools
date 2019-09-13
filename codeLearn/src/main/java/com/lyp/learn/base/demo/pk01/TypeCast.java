package com.lyp.learn.base.demo.pk01;

public class TypeCast {
    public static void main(String[] args) {

        int i = 100;
        char c = 'a';
        byte b = 3;
        long long1 = 300L;
        float f = 1.56F;
        double d = 2.13;

        //char类型的变量c自动转换为与i一致的int类型参加运算
        int i1 = i + c;
        //int类型的变量i自动转换为与long1一致的long类型变量参加运算
        long long2 = long1 - i;
        //byte类型的变量b自动转换为与f一致的float类型参加运算。
        float f1 = b*f;
        //int类型的变量i自动转换为与f一致的float类型，f/i计算结果为float类型，
        // 然后再转换为与d一致的double类型
        double d1 = d + f/i;

        System.out.println("i1 = " + i1);
        System.out.println("long2 = " + long2);
        System.out.println("f1 = " + f1);
        System.out.println("d1 = " + d1);

    }
}
