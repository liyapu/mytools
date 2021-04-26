package com.lyp.likou.interview;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 10:57
 *
 * 面试题 17.14. 最小K个数
 * 设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。
 *
 * 示例：
 * 输入： arr = [1,3,5,7,2,4,6,8], k = 4
 * 输出： [1,2,3,4]
 * 提示：
 *
 * 0 <= len(arr) <= 100000
 * 0 <= k <= min(100000, len(arr))
 */
public class I_17_14 {


    public static int[] smallestK(int[] arr, int k) {
        int len = 0;
        int[] resultArr = new int[k];
        if (null == arr || (len = arr.length) == 0 || k <= 0) return resultArr;

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> (o2 - o1));
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }

        for (int i = k; i < len; i++) {
            if (arr[i] < pq.peek()) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }

        for (int i = 0; i < k; i++) {
            resultArr[i] = pq.poll();
        }
        return resultArr;
    }

    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 2, 4, 6, 8};
        int k = 4;
        System.out.println(Arrays.toString(smallestK(arr, k)));

    }
}
