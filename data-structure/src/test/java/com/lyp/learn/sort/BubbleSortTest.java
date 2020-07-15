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
        System.out.println(Arrays.toString(arr));
        shellSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shellSort(int[] arr) {
        int len = arr.length;
        if (arr == null || (len = arr.length) == 0) {
            return;
        }
        for (int gap = len / 2; gap >= 1; gap /= 2) {
            for (int i = gap; i < len; i++) {
                int temp = arr[i];
                int j = i - gap;
                while (j >= 0 && arr[j] > temp) {
                    arr[j + gap] = arr[j];
                    j -= gap;
                }
                arr[j + gap] = temp;
            }
        }
    }


}