package com.lyp.learn.sort;

import java.util.Arrays;

/**
 * 桶排序
 *
 *      桶排序是指：将待排序数组中的元素划分为多个桶（范围段），对每个桶分别进行排序，将多个桶组合即为排序结果。
 *      桶排序的时间复杂度为O(n + c),  其中n为待排序数据量，c = n * (logn - logm), m为桶的个数。极端情况下，当桶的个数与数据量相等时，桶排序时间复杂度为O(n)。
 *      看一些博客里写道桶排序是稳定排序，另一些博客则说是非稳定排序。
 *      实际上，桶排序的稳定性取决于桶内排序所使用的算法，若使用插入排序，则是稳定排序，
 *      若使用快排，则是非稳定排序
 *
 *
 */
public class BucketSort {
    public static void main(String[] args) {
        int[] array = new int[]{3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        bucketSort(array);
        System.out.println(Arrays.toString(array));
    }

    public static void bucketSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return;
        }
        int len = arr.length;
        // 根据原始序列的长度，设置桶的数量。这里假设每个桶放最多放4个元素
        int bucketCount = len / 4;
        // 遍历原始序列，找出最大值和最小值
        int min = 0, max = 0;
        for (int i = 0; i < len; i++) {
            if (arr[i] > max) {
                max = arr[i];
            } else if (arr[i] < min) {
                min = arr[i];
            }
        }
        // 每个桶的数值范围
        int range = (max - min + 1) / bucketCount;
        int[][] buckets = new int[bucketCount][];
        // 遍历原始序列
        for (int i = 0; i < len; i++) {
            int val = arr[i];
            // 计算当前值属于哪个桶
            int bucketIndex = (int) Math.floor((val - min) / range);
            // 向桶中添加元素
            buckets[bucketIndex] = appendItem(buckets[bucketIndex], val);
        }
        // 最后合并所有的桶
        int k = 0;
        for (int[] b : buckets) {
            if (b != null) {
                for (int i = 0; i < b.length; i++) {
                    arr[k++] = b[i];
                }
            }
        }
    }

    private static int[] appendItem(int[] bucketArr, int val) {
        if (bucketArr == null || bucketArr.length == 0) {
            return new int[]{val};
        }
        // 拷贝一下原来桶的序列，并增加一位
        int[] arr = Arrays.copyOf(bucketArr, bucketArr.length + 1);
        // 这里使用插入排序，将新的值val插入到序列中
        int i;
        for (i = arr.length - 2; i >= 0 && arr[i] > val; i--) {
            // 从新序列arr的倒数第二位开始向前遍历（倒数第一位是新增加的空位，还没有值）
            // 如果当前序列值大于val，那么向后移位
            arr[i + 1] = arr[i];
        }
        arr[i + 1] = val;
        return arr;
    }
}