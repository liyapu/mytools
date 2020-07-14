package com.lyp.learn.sort;

import java.util.Arrays;

/**
 * 希尔排序（Shell Sort）
 * <p>
 * https://baijiahao.baidu.com/s?id=1644158198885715432&wfr=spider&for=pc
 * https://blog.csdn.net/qq_39207948/article/details/80006224
 */
public class ShellSort {


    public static void main(String[] args) {
        int[] array = new int[]{8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        System.out.println("排序前: " + Arrays.toString(array));
        array = shellSort(array);
        System.out.println("排序后: " + Arrays.toString(array));
        System.out.println();


        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println();
    }

    //------------------------- 方式一 ----------------------------------------------

    /**
     * 希尔排序
     *
     * @param arr
     * @return
     */
    public static int[] shellSort(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return arr;
        }
        //gap控制两数之间的间隔
        int temp, gap = len / 2;
        while (gap > 0) {
            for (int i = gap; i < len; i++) {
                //每次遍历开始下标位置的值
                temp = arr[i];
                //开始第一次为0
                int preIndex = i - gap;
                //preIndex >= 0;防止数组越界
                //preIndex >= 0 时, array[preIndex] 不大于 temp 时，停止遍历
                // array[preIndex] 之前，间隔gap的数，都是有序的
                while (preIndex >= 0 && arr[preIndex] > temp) {
                    //preIndex + gap 与 preIndex 间隔 gap个数，此两数相邻
                    arr[preIndex + gap] = arr[preIndex];
                    //preIndex - gap ,向前推进
                    preIndex -= gap;
                }
                //到此表示给 temp 找到了插入了位置
                //temp原位置不动的设置一次 或者是 把temp设置到前面的位置
                // while 循环中  preIndex 减去了 gap ,此处要加回来
                arr[preIndex + gap] = temp;
            }
            gap /= 2;
        }
        return arr;
    }

    //------------------------- 方式二 ----------------------------------------------


    public static void shellSort2(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return;
        }
        // 控制 gap  gap的终止条件是 >= 1
        for (int gap = len / 2; gap >= 1; gap /= 2) {
            //内部的直接排序，从后向前
            for (int i = gap; i < len ; i++) {
                //保存待插入的值
                int temp = arr[i];
                // j 定义在外部，for 循环之外需要访问
                // j 减去 gap ,向前比较
                int j = i -gap;
                // j >0 ，从而不会数组越界
                // arr[j] 和 temp 比较，这个循环是给 temp 找到合适的插入位置
                for (; j >= 0 && arr[j] > temp ; j-=gap) {
                    arr[j+gap] = arr[j];
                }
                //找temp 位置的 循环的终止条件时 j < 0 ,故下面需要 j 需要加上 gap ,才会找到终止前的位置
                arr[j+gap] = temp;
            }
        }
    }

}
