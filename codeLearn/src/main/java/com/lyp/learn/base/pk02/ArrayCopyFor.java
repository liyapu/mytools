package com.lyp.learn.base.pk02;

import java.util.Arrays;

public class ArrayCopyFor {
    public static void main(String[] args) {
        int [] array1 = {1,2,3,4,5};
        int [] array2 = new int [array1.length];

        for(int i = 0; i < array1.length; i++){
            array2[i] = array1[i];
        }
        array2[0] = 100;
        System.out.println(Arrays.toString(array1));
        System.out.println(Arrays.toString(array2));
    }
}
