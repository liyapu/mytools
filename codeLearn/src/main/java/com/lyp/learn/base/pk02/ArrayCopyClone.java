package com.lyp.learn.base.pk02;

import java.util.Arrays;

public class ArrayCopyClone {
    public static void main(String[] args) {
        int [] array1 = {1,2,3,4,5};
        int [] array2 =  array1.clone();
        array2[0] = 100;

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
}
