package com.lyp.likou.array;

import java.util.PriorityQueue;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-19 14:58
 * 215. 数组中的第K个最大元素
 * 在未排序的数组中找到第 k 个最大的元素。请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4] 和 k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
 * 输出: 4
 *
 * 说明:
 * 你可以假设 k 总是有效的，且 1 ≤ k ≤ 数组的长度
 */
public class A_215 {
    /**
     * 构建小顶堆
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        if (null == nums || k <= 0) return -1;
        int length = nums.length;
        if (k > length) return -1;

        PriorityQueue<Integer> pq = new PriorityQueue<>(k);
        for (int i = 0; i < k; i++) {
            pq.offer(nums[i]);
        }
        for (int i = k; i < length; i++) {
            if (pq.peek() < nums[i]) {
                pq.poll();
                pq.offer(nums[i]);
            }
        }
        return pq.poll();
    }

    public static void main(String[] args) {
        int[] arr1 = {3, 2, 1, 5, 6, 4};
        System.out.println(findKthLargest(arr1, 2));

        int[] arr2 = {3, 2, 3, 1, 2, 4, 5, 5, 6};
        System.out.println(findKthLargest(arr2, 4));
    }

}
