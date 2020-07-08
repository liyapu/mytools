package com.lyp.learn.sort;

import java.util.Arrays;

/**
 * 堆排序
 *    一般升序采用大顶堆，降序采用小顶堆
 *
 *    算法思想,参考 笔记html
 */
public class HeapSort {

    /**
     * 大顶堆排序
     * @param arr
     * @return
     */
    public static void maxHeapSort(int [] arr){
        //1.构建大顶堆
        //开始位置是 length/2 - 1,
        //从最后一个非叶子结点开始（叶结点自然不用调整，第一个非叶子结点 arr.length/2-1=5/2-1=1），从左至右，从下至上进行调整
        int length = arr.length;
        for(int i = length/2 - 1; i >= 0; i--){
            //从树看:从第一个非叶子结点从下至上，从右至左调整结构
            //从数组看，从后向前
            adjustHeap(arr,i,length);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for(int j = length - 1; j > 0; j--){
            //将堆顶元素与末尾元素进行交换
            swapNum(arr,0,j);
            //重新对堆进行调整
            adjustHeap(arr,0,j);
        }
    }


    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     * @param arr
     * @param i
     * @param length
     */
    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];//先取出当前元素i
        for(int k = 2*i + 1; k < length; k = 2*i + 1){ //从i结点的左子结点开始，也就是2i+1处开始
            if(k + 1 < length && arr[k] < arr[k+1]){//如果左子结点小于右子结点，k指向右子结点
                k++;
            }
            if(arr[k] > temp){//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[k];
                i = k;
            }else{
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }

    /**
     * 交换元素
     * @param arr
     * @param i
     * @param length
     */
    private static void swapNum(int[] arr, int i, int length) {
        int temp = arr[i];
        arr[i] = arr[length];
        arr[length] = temp;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};

        System.out.println(Arrays.toString(arr));
        maxHeapSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
