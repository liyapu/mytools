package com.lyp.learn.base.demo.pk02.arrays;

import java.util.Arrays;

public class ToStringDemo {
    public static void main(String[] args) {
        //定义一个整型数组
        int [] arrayInt = null;

        /**
         * 输出一维数组
         */
        System.out.println("----输出一维数组----");
        arrayInt = new int [] {10,20,30,40,50};
        System.out.println(Arrays.toString(arrayInt));
        /**
         * 输出二维数组
         * deepEquals,deepToString 能够进行比较多维数组，而且是任意层次的嵌套数组。
         */
        System.out.println("----输出二维数组----");
        int [] [] arrayIntTwo = new int [][] {{1,2,3},{10,20,30},{100,200,300}};
        System.out.println(Arrays.toString(arrayIntTwo));
        System.out.println(Arrays.deepToString(arrayIntTwo));

        /**
         * 输出三维数组
         */
        int [][][] arrayIntThree = new int [][][]
                {
                        { { 1, 2, 3 }, { 4, 5, 6 } },
                        { { 7, 8, 9 }, { 10, 11, 12 } },
                        { { 13, 14, 15 }, { 16, 17, 18 } }
                };
        System.out.println("-------输出三维数组----");
        System.out.println(Arrays.deepToString(arrayIntThree));
    }
}
