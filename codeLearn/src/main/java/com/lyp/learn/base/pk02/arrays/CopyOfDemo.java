package com.lyp.learn.base.pk02.arrays;

import java.util.Arrays;

public class CopyOfDemo {
    public static void main(String[] args) {
        //定义一个整型数组
        int [] arrayInt = null;

        /**
         * 复制指定长度的数组
         * 长度可大于被复制数组的长度
         */
        arrayInt = new int [] {10,20,30,40,50};
        System.out.println("-----复制指定长度的数组------");
        int [] arrayInt2 = Arrays.copyOf(arrayInt,4);
        System.out.println(Arrays.toString(arrayInt2));
        System.out.println(Arrays.toString(Arrays.copyOf(arrayInt,arrayInt.length)));
        System.out.println(Arrays.toString(Arrays.copyOf(arrayInt,arrayInt.length * 2 + 3)));

        /**
         * 复制指定范围的数组
         * 所有的from - to的区间都是左闭右开的！！即[from, to)
         */
        arrayInt = new int [] {10,20,30,40,50};
        System.out.println("-----复制指定范围的数组------");
        System.out.println(Arrays.toString(Arrays.copyOfRange(arrayInt,1,3)));
    }
}
