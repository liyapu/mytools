package com.lyp.learn.base.demo.pk02.arrays;

import java.util.Arrays;

public class FillDemo {
    public static void main(String[] args) {
        //定义一个整型数组
        int [] arrayInt = null;

        /**
         * 填充整个数组
         */
        System.out.println("----填充整个数组------");
        arrayInt = new int[5];
        System.out.println(Arrays.toString(arrayInt));
        Arrays.fill(arrayInt,2);
        System.out.println(Arrays.toString(arrayInt));
        /**
         * 填充指定位置之间的数组
         */
        System.out.println("----填充指定位置之间的数组------");
        arrayInt = new int[5];
        Arrays.fill(arrayInt,1,4,8);
        System.out.println(Arrays.toString(arrayInt));
    }
}
