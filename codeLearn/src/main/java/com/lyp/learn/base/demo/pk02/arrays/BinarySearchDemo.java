package com.lyp.learn.base.demo.pk02.arrays;

import java.util.Arrays;

public class BinarySearchDemo {
    public static void main(String[] args) {
        //定义一个整型数组
        int [] arrayInt = null;

        /**
         * 二分查找法找指定元素的索引值（下标）
         * binarySearch()方法的返回值为：
         * 1、如果找到关键字，则返回值为关键字在数组中的位置索引，且索引从0开始
         * 2、如果没有找到关键字，返回值为负的插入点值，所谓插入点值就是第一个比关键字大的元素在数组中的位置索引，而且这个位置索引从1开始。
         *
         * 注意：
         * 1、调用binarySearch()方法前要先调用sort方法对数组进行排序，否则得出的返回值不定，这时二分搜索算法决定的。
         * 2、排序后的返回结果是负数就一定说明没找到
         *
         */
        arrayInt = new int[] {10,20,30,40,50,60,70,80,90,100};
        System.out.println("-----二分查找法找指定元素的索引值（下标）-----");
        System.out.println(Arrays.toString(arrayInt));
        System.out.println(Arrays.binarySearch(arrayInt,30));
        System.out.println(Arrays.binarySearch(arrayInt,31));
        System.out.println(Arrays.binarySearch(arrayInt,39));
        System.out.println(Arrays.binarySearch(arrayInt,41));
        System.out.println(Arrays.binarySearch(arrayInt,49));
        System.out.println(Arrays.binarySearch(arrayInt,101));
        System.out.println(Arrays.toString(Arrays.copyOfRange(arrayInt,3,5)));
        System.out.println(Arrays.binarySearch(arrayInt,3,5,30));
        System.out.println(Arrays.binarySearch(arrayInt,3,5,101));

    }
}
