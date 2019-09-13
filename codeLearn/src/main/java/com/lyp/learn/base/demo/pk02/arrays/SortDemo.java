package com.lyp.learn.base.demo.pk02.arrays;

import java.util.Arrays;
import java.util.Comparator;

public class SortDemo {
    public static void main(String[] args) {
        //定义一个整型数组
        int [] arrayInt = null;

        /**
         * 排序整个数组(升序)
         * 这将改变原数组
         */
        System.out.println("----排序整个数组(升序)-----");
        arrayInt = new int [] {4,7,2,5,8,3};
        Arrays.sort(arrayInt);
        System.out.println(Arrays.toString(arrayInt));
        /**
         * 排序指定位置之间的数组(升序)
         */
        System.out.println("----排序指定位置之间的数组(升序)-----");
        arrayInt = new int [] {4,7,2,5,8,3};
        Arrays.sort(arrayInt,1,4);
        System.out.println(Arrays.toString(arrayInt));
        //使用比较器全部降序排序
        /**
         * 使用比较器全部排序整个数组(降序) lambda表达式
         */
        System.out.println("-----使用比较器全部排序整个数组(降序)----lambda表达式-");
        Integer [] arrayInteger = new Integer[]{4,7,2,5,8,3};
        Arrays.sort(arrayInteger,(x,y)->y.compareTo(x));
        System.out.println(Arrays.toString(arrayInteger));

        /**
         *  使用比较器全部排序整个数组(降序) Comparator
         */
        System.out.println("-----使用比较器全部排序整个数组(降序)----Comparator-");
        Integer [] arrayInteger2 = new Integer[]{4,7,2,5,8,3};
        Arrays.sort(arrayInteger2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        System.out.println(Arrays.toString(arrayInteger2));
    }
}
