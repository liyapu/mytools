package com.lyp.learn.base.demo.pk02.arrays;


import java.util.Arrays;

public class ParallelSetAllDemo {
    public static void main(String[] args) {
        int [] arrayInt = {1,2,3,4,5,6};
        System.out.println(Arrays.toString(arrayInt));
        Arrays.parallelSetAll(arrayInt,x -> x* 5);
        System.out.println(Arrays.toString(arrayInt));

    }
}
