package com.lyp.learn.base.pk02;

import java.util.Arrays;

public class ArrayCopySystem {
    public static void main(String[] args) {
        int [] array1 = {1,2,3,4,5};
        int [] array2 = new int [array1.length];

        System.arraycopy(array1,0,array2,0,array1.length);
        array2[0] = 100;

        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
}
