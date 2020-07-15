package com.lyp.learn.sort;

import java.util.Arrays;

/**
 * 快速排序
 *     对挖坑填数进行总结
 *        1．i = low; j = high; 将基准数挖出形成第一个坑a[i]。
 *        2．j--由后向前找比它小的数，找到后挖出此数填前一个坑a[i]中。
 *        3．i++由前向后找比它大的数，找到后也挖出此数填到前一个坑a[j]中。
 *        4．再重复执行2，3二步，直到i==j，将基准数填入a[i]中
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr));
        quickSort(arr,0,arr.length - 1);
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println();

    }
    /**
     * 快速排序
     * @param arr
     * @return
     */
    public static void quickSort(int [] arr,int low,int high){
        if(arr == null || low >= high){
            return;
        }
        //从左边开始的游标
        int i = low;
        //从右边开始的游标
        int j = high;
        //存储比较的基准数，数组的第一个数字
        int key = arr[low];

        while(i < j){
            // j 表示的数组上的数字，都 >= 基准数，从后向前
            while(i < j && arr[j] >= key){
                j--;
            }
            //用上面找到的j 位置上的值
            //把上面找到的比基准数小的数字，放到 i 位置上，然后 i 向后移动 1位
            if(i < j){
                arr[i++] = arr[j];
            }


            //i 表示的数组上的数组，都 <= 基准数，从前向后
            while (i < j && arr[i] <= key){
                i++;
            }
            //用上面找到的 i位置上的值
            //把上面找到的比基准数大的数字，放到 j 位置上，然后 j 向前移动 1位
            if(i < j ){
                arr[j--] = arr[i];
            }
        }
        //上面的判断都有 i < j 的判断，所以最终i 会等于j;设置上基准数
        arr[i] = key;
        //基准数字移动到的位置 - 1
        quickSort(arr,low,i-1);
        //基准数字移动到的位置 + 1
        quickSort(arr,i+1,high);
    }

}
