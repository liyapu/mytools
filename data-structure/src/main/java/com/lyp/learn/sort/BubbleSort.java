package com.lyp.learn.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 *     第一趟排序，就是将第一大的数排在倒数第一位
 *     第二趟排序，就是将第二大的数排在倒数第二位
 *     第三趟排序，就是将第三大的数排在倒数第三位
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] array = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(array));
        array = bubbleSort(array);
        System.out.println("排序后: " + Arrays.toString(array));
        System.out.println();


        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr));
        bubbleSortOptimize(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
    }


    //------------------------- 方式一 ----------------------------------------------
    /**
     * 冒泡排序
     *
     * @param arr
     * @return
     */
    public static int[] bubbleSort(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return arr;
        }
        //第一层控制遍历的变量个数：长度-1
        for (int i = 0; i < len - 1; i++) {
            //第二层从0开始，到长度-1 - 第一层下沉的大数个数
            for (int j = 0; j < len - 1 - i; j++) {
                //下面都是使用 j 变量计算的
                //当前值与其后一位，第二层已经-1，下面 +1,故不会访问越界
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        return arr;
    }

    //------------------------- 方式二 ----------------------------------------------

    /**
     * 优化冒泡排序
     *    某轮排序中，若是没有交互数据，表示已经是有序了的，可以提前结束
     *
     * @param arr
     */
    public static void bubbleSortOptimize(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return;
        }
        for (int i = 0; i < len - 1; i++) {
            // 标识变量，表示是否进行过交换，进行优化，可能可以提前结束
            // 在这里定义 ordered 变量,每轮都会自动初始化
            boolean ordered = false;
            for (int j = 0; j < len - 1 - i; j++) {
                // 如果前面的数比后面的数大，则交换
                if (arr[j] > arr[j + 1]) {
                    ordered = true;
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
            // 在一趟排序中，一次交换都没有发生过
            if (!ordered) {
                break;
            }
        }
    }

}
