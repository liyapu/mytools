package com.lyp.learn.base.demo.pk02;

import java.util.Arrays;

public class ArrayCopyArrays {
    public static void main(String[] args) {
        int [] array1 = {1,2,3,4,5};
        int [] array2 =  Arrays.copyOf(array1,array1.length);
        array2[0] = 100;

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
}
