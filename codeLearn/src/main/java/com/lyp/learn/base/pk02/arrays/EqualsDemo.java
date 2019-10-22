package com.lyp.learn.base.pk02.arrays;

import java.util.Arrays;

public class EqualsDemo {
    public static void main(String[] args) {

        /**
         * 比较一维数组内容是否相等
         * 如果是arr1.equals(arr2),则返回false，因为equals比较的是两个对象的地址，不是里面的数，
         * 而Arrays.equals重写了equals，所以，这里能比较元素是否相等
         */
        int [] arr1 = {1,2,3};
        int [] arr2 = {1,2,3};
        System.out.println("----比较一维数组内容是否相等-----");
        System.out.println(arr1 == arr2);
        System.out.println(arr1.equals(arr2));
        System.out.println(Arrays.equals(arr1,arr1));
        System.out.println(Arrays.equals(arr1,arr2));

        /**
         * 比较二维数组内容是否相等
         */
        System.out.println("------比较二维数组内容是否相等----");
        int [] [] arrayIntTwo1 = new int [][] {{1,2,3},{10,20,30},{100,200,300}};
        int [] [] arrayIntTwo2 = new int [][] {{1,2,3},{10,20,30},{100,200,300}};
        System.out.println(Arrays.deepEquals(arrayIntTwo1,arrayIntTwo2));
    }
}
