package com.lyp.learn.sort;

import java.util.Arrays;

/**
 * 归并排序
 */
public class MergeSort {

    public static void main(String[] args) {
        int[] array = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(array));
        array = mergeSort(array);
        System.out.println("排序后: " + Arrays.toString(array));
        System.out.println();

        int[] arr = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr));
        arr = mergeSort2(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println();

        int[] arr3 = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr3));
        int len3 = arr3.length;
        //归并排序需要一个额外空间
        int temp[] = new int[len3];
        //这里需要传递 len - 1
        mergeSort3(arr3, 0, len3-1, temp);
        System.out.println("排序后: " + Arrays.toString(temp));

    }

    //------------------------- 方式一 ----------------------------------------------

    /**
     * 归并排序
     *
     * @param arr
     * @return
     */
    public static int[] mergeSort(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return arr;
        }
        if (len < 2) {
            return arr;
        }

//        if(arr == null  || (len = arr.length) <= 1){
//            return arr;
//        }

        int mid = len / 2;
        int[] left = Arrays.copyOfRange(arr, 0, mid);
        int[] right = Arrays.copyOfRange(arr, mid, len);
        return merge(mergeSort(left), mergeSort(right));
    }

    /**
     * 归并排序---将两段排序好的数组结合成一个排序好的数组
     *
     * @param left
     * @param right
     * @return
     */
    public static int[] merge(int[] left, int[] right) {
        int leftLen = left.length;
        int rightLen = right.length;
        int len = leftLen + rightLen;
        int[] array = new int[len];

        //index 表示 left,right 数组总长度遍历次数
        //i 表示 left数组的下标
        //j 表示 right数组的下标
        //index 已经在 for 循环中自增了，在下面的数组赋值时，不能自增了
        for (int index = 0, i = 0, j = 0; index < len; index++) {
            // 前两个判断 i >= leftLen，j >= rightLen 一定要放在前面，不然会出错，比如放在if判断的时候，会报数组越界的异常
            //i >= left数组长度，表示left中已经遍历完了，剩下的把right中的数组元素直接放到array中
            if (i >= leftLen) {
                array[index] = right[j++];
                //j >= right数组长度，表示right中已经遍历完了，剩下的把left中的数组元素直接放到array中
            } else if (j >= rightLen) {
                array[index] = left[i++];
                //比较left数组与right数组元素大小
            } else if (left[i] > right[j]) {
                array[index] = right[j++];
            } else {
                array[index] = left[i++];
            }
        }
        return array;
    }


    //------------------------- 方式二 ----------------------------------------------

    public static int[] mergeSort2(int[] arr) {
        int len;
        if (arr == null || (len = arr.length) == 0) {
            return arr;
        }
        if (len < 2) {
            return arr;
        }
        int middle = len / 2;
        int[] leftArr = Arrays.copyOfRange(arr, 0, middle);
        int[] rightArr = Arrays.copyOfRange(arr, middle, len);
        return merge2(mergeSort2(leftArr), mergeSort2(rightArr));
    }

    private static int[] merge2(int[] leftArr, int[] rightArr) {
        int leftLen = leftArr.length;
        int rightLen = rightArr.length;
        int len = leftLen + rightLen;
        int[] arr = new int[len];
        int i = 0, j = 0;
        int index = 0;

        while (i < leftLen && j < rightLen) {
            if (leftArr[i] > rightArr[j]) {
                arr[index++] = rightArr[j++];
            } else {
                arr[index++] = leftArr[i++];
            }
        }

        // 表示 左边的数组，还未遍历到末尾，直接加入 左边的数组
        // 这里需要使用 while 循环，因为可能有很多个
        // 一共 3个 while循环
        while (i < leftLen) {
            arr[index++] = leftArr[i++];
        }
        while (j < rightLen) {
            arr[index++] = rightArr[j++];
        }
        return arr;
    }

    //------------------------- 方式三 ----------------------------------------------


    //分+合方法
    public static void mergeSort3(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2; //中间索引
            //向左递归进行分解
            mergeSort3(arr, left, mid, temp);
            //向右递归进行分解
            mergeSort3(arr, mid + 1, right, temp);
            //合并
            merge3(arr, left, mid, right, temp);

        }
    }

    //合并的方法

    /**
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     */
    public static void merge3(int[] arr, int left, int mid, int right, int[] temp) {

        int i = left; // 初始化i, 左边有序序列的初始索引
        int j = mid + 1; //初始化j, 右边有序序列的初始索引
        int index = 0; // 指向temp数组的当前索引

        //(一)
        //先把左右两边(有序)的数据按照规则填充到temp数组
        //直到左右两边的有序序列，有一边处理完毕为止
        while (i <= mid && j <= right) {//继续
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到 temp数组
            //然后 index++, i++
            if (arr[i] <= arr[j]) {
                temp[index++] = arr[i++];
            } else { //反之,将右边有序序列的当前元素，填充到temp数组
                temp[index++] = arr[j++];
            }
        }

        //(二)
        //把有剩余数据的一边的数据依次全部填充到temp
        while (i <= mid) { //左边的有序序列还有剩余的元素，就全部填充到temp
            temp[index++] = arr[i++];
        }

        while (j <= right) { //右边的有序序列还有剩余的元素，就全部填充到temp
            temp[index++] = arr[j++];
        }


        //(三)
        //将temp数组的元素拷贝到arr
        //注意，并不是每次都拷贝所有
        index = 0;
        int tempLeft = left; //
        //第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[index++];
        }

    }



}
