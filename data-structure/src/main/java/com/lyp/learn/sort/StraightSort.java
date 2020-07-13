package com.lyp.learn.sort;

import java.util.Arrays;

public class StraightSort {

    public static void main(String[] args) {
        int[] array = new int[]{52, 39, 67, 95, 70, 8, 25, 52};
        System.out.println("排序前: " + Arrays.toString(array));
        array = straightSort(array);
        System.out.println("排序后: " + Arrays.toString(array));
        System.out.println();

        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr));
        straightSort2(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println();


    }

    //------------------------- 方式一 ----------------------------------------------

    /**
     * 直接插入排序
     *
     * @param array
     * @return
     */
    public static int[] straightSort(int[] array) {
        int length = array.length;
        if (length == 0) {
            return array;
        }
        //第一层控制：顺序往后：从第一个元素到末尾
        for (int i = 1; i < length; i++) {
            //第二层控制：逆序往前逐个比较:
            // j > 0 ,j的最小值是 1,所以下面的 j-1 不会数组越界
            for (int j = i; j > 0; j--) {
                if (array[j] < array[j - 1]) {
                    int temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                }
            }
        }
        return array;
    }


    public static void printArray(int[] array) {
        int length = array.length;
        for (int i = 0; i < length; i++) {
            if (i != 0) {
                System.out.print(" , ");
            }
            System.out.print(array[i]);
        }
        System.out.println();
    }

    //------------------------- 方式二 ----------------------------------------------
    public static void straightSort2(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return;
        }
        for (int i = 1; i < len; i++) {
            int j = i;
            //此处 j >= 1, 所以 j-1 不会数组越界
            while (j >= 1 && arr[j - 1] > arr[j]) {
                int temp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = temp;
                j--;
            }
        }
    }


}
