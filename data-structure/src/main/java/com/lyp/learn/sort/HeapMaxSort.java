package com.lyp.learn.sort;

import java.util.Arrays;

/**
 *   堆排序
 *     一般升序采用大顶堆，降序采用小顶堆
 *
 *   算法思想,参考 笔记html
 *
 *   堆是一种数据结构，一种叫做完全二叉树的数据结构。
 *
 *   堆的性质
 *      这里我们用到两种堆，其实也算是一种。
 *      大顶堆：每个节点的值都大于或者等于它的左右子节点的值。
 *      小顶堆：每个节点的值都小于或者等于它的左右子节点的值。
 *
 *
 *   如果我们把这种逻辑结构映射到数组中，就是下边这样
 *   这个数组arr逻辑上就是一个堆。
 *   从这里我们可以得出以下性质（重点）
 *   对于大顶堆：arr[i] >= arr[2i + 1] && arr[i] >= arr[2i + 2]
 *   对于小顶堆：arr[i] <= arr[2i + 1] && arr[i] <= arr[2i + 2]
 *
 *
 *  堆排序的基本思想是：
 *    1、将带排序的序列构造成一个大顶堆，根据大顶堆的性质，当前堆的根节点（堆顶）就是序列中最大的元素；
 *    2、将堆顶元素和最后一个元素交换，然后将剩下的节点重新构造成一个大顶堆；
 *    3、重复步骤2，如此反复，从第一次构建大顶堆开始，每一次构建，
 *      我们都能获得一个序列的最大值，然后把它放到大顶堆的尾部。
 *    4.最后，就得到一个有序的序列了。
 *
 *    第一个叶子节点，一定是序列长度/2，
 *    第一个非叶子节点的索引就是arr.length / 2 -1。
 */
public class HeapMaxSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        System.out.println("排序前: " + Arrays.toString(arr));
        maxHeapSort(arr);
        System.out.println("排序后: " + Arrays.toString(arr));
        System.out.println();

    }

    /**
     * 大顶堆排序
     *
     * @param arr
     * @return
     */
    public static void maxHeapSort(int[] arr) {
        //1.构建大顶堆
        //开始位置是 len/2 - 1,第一个非叶子节点
        //从最后一个非叶子结点开始（叶结点自然不用调整，第一个非叶子结点 arr.length/2-1=5/2-1=1），从右至左，从下至上进行调整
        int len = arr.length;
        for (int i = len / 2 - 1; i >= 0; i--) {
            //从树看:从第一个非叶子结点从下至上，从右至左调整结构
            //从数组看，从后向前
            adjustHeap(arr, i, len);
        }

        //2.调整堆结构+交换堆顶元素与末尾元素
        for (int j = len - 1; j > 0; j--) {
            //将堆顶元素与末尾元素进行交换
            swapNum(arr, 0, j);
            //重新对堆进行调整
            adjustHeap(arr, 0, j);
        }
    }


    /**
     * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
     *
     * @param arr
     * @param i
     * @param len
     */
    private static void adjustHeap(int[] arr, int i, int len) {
        int temp = arr[i];//先取出当前元素i
        for (int j = 2 * i + 1; j < len; j = 2 * i + 1) { //从i结点的左子结点开始，也就是2i+1处开始
            if (j + 1 < len && arr[j] < arr[j + 1]) {//如果左子结点小于右子结点，j指向右子结点
                j++;
            }
            if (arr[j] > temp) {//如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
                arr[i] = arr[j];
                i = j;
            } else {
                break;
            }
        }
        //将temp值放到最终的位置
        arr[i] = temp;
    }

    /**
     * 交换元素
     *
     * @param arr
     * @param i
     * @param length
     */
    private static void swapNum(int[] arr, int i, int length) {
        int temp = arr[i];
        arr[i] = arr[length];
        arr[length] = temp;
    }

}
