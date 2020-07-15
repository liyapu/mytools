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
//        int[] arr = new int[]{5, 4, 3, 2, 1};
//        int[] arr = new int[]{1,2,3};
        System.out.println(Arrays.toString(arr));
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
       int len;
       if(arr == null || (len = arr.length) == 0){
           return;
       }
       int start = len/2-1;
        for (int i = start; i >= 0; i--) {
            adjust(arr,i, len);
        }

        for (int i = len-1; i >=0; i--) {
            swap(arr,0,i);
            adjust(arr,0,i);
        }
    }

    private static void adjust(int[] arr, int i,int len) {
       int temp = arr[i];
        for (int k = 2*i+1; k < len; k = 2*i+1) {
            if(k+1 < len && arr[k] < arr[k+1] ){
                k++;
            }
            if(arr[k] > temp){
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        arr[i] = temp;
    }

    private static void swap(int[] arr, int i, int len) {
        int temp = arr[i];
        arr[i] = arr[len];
        arr[len] = temp;
    }


}