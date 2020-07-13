package com.lyp.learn.sort;


import java.util.Arrays;

/**
 * @author: liyapu
 * @description:
 * @date 2020-07-13 15:48
 */
class BubbleSortTest {
    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println(Arrays.toString(arr));
         straightSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public  static void straightSort(int [] arr){
        int len;
        if(arr == null || (len = arr.length) == 0){
            return;
        }
        for (int i = 1; i < len ; i++) {
            int j = i;
            while(j >=1  && arr[j-1] > arr[j]){
                int temp = arr[j-1];
                arr[j-1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }



}