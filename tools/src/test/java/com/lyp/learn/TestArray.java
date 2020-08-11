package com.lyp.learn;

/**
 *@author: liyapu
 *@description:
 *@date 2020-08-11 16:15
 */
public class TestArray {

    public static void main(String[] args) {
        int [] array1 = {1,3,5,7,9};
        int [] array2 = {2,3,5,6,8,9};

        int len1 = array1.length;
        int len2 = array2.length;

        int [] array = new int[len1+len2];

        int i = 0;
        int j = 0;
        int k = 0;
        while(i < len1 && j < len2){
            if(array1[i] > array2[j]){
                array[k++] = array2[j++];
            }else{
                array[k++] = array1[i++];
            }
        }

        if(i == len1){
            while(j < len2){
                array[k++] = array2[j++];
            }
        }
        if(j == len2){
            while(i < len1){
                array[k++] = array2[i++];
            }
        }

        for (int n = 0; n < array.length; n++) {
            System.out.print(array[n] + " ");
        }
    }
}
