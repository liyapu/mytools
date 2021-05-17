package com.lyp.likou.array;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-17 10:30
 *
 * 674. 最长连续递增序列
 * 给定一个未经排序的整数数组，找到最长且连续递增的子序列，并返回该序列的长度。
 *
 * 连续递增的子序列 可以由两个下标 l 和 r（l < r）确定，如果对于每个 l <= i < r，都有 nums[i] < nums[i + 1] ，
 * 那么子序列 [nums[l], nums[l + 1], ..., nums[r - 1], nums[r]] 就是连续递增子序列。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,3,5,4,7]
 * 输出：3
 * 解释：最长连续递增序列是 [1,3,5], 长度为3。
 * 尽管 [1,3,5,7] 也是升序的子序列, 但它不是连续的，因为 5 和 7 在原数组里被 4 隔开。
 *
 * 示例 2：
 * 输入：nums = [2,2,2,2,2]
 * 输出：1
 * 解释：最长连续递增序列是 [2], 长度为1。
 *
 *
 * 提示：
 * 0 <= nums.length <= 104
 * -109 <= nums[i] <= 109
 */
public class A_674 {

    public static int findLengthOfLCIS(int[] nums) {
        int len;
        if (null == nums || (len = nums.length) == 0) {
            return 0;
        }

        int maxLen = 0;
        int leftIndex = 0;
        int max = Integer.MIN_VALUE;

        Map<Integer, Integer> value$Index = new HashMap<>();

        for (int i = 0; i < len; i++) {
            int value = nums[i];
            if (value > max) {
                // 1 2 3 4
                max = value;
                maxLen = Math.max(maxLen, i - leftIndex + 1);
            } else {
                // 重置 左下标，同时重置 max 值
                // 1 2 6 3 4 5 7 8
                leftIndex = i;
                max = value;
                maxLen = Math.max(maxLen, i - leftIndex + 1);
            }
        }
        return maxLen;
    }

    public static int findLengthOfLCIS2(int[] nums) {
        int len;
        if (null == nums || (len = nums.length) == 0) {
            return 0;
        }

        int maxLen = 0;
        int leftIndex = 0;
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < len; i++) {
            int value = nums[i];
            // 这里必须是 小于等于
            if (value <= max) {
                leftIndex = i;
            }
            max = value;
            maxLen = Math.max(maxLen, i - leftIndex + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        int[] arr1 = {1, 3, 5, 4, 7};
        System.out.println(findLengthOfLCIS(arr1));

        int[] arr2 = {2, 2, 2, 2, 2};
        System.out.println(findLengthOfLCIS(arr2));

        int[] arr3 = {1, 3, 5, 4, 2, 3, 4, 5};
        System.out.println(findLengthOfLCIS(arr3));

    }
}
