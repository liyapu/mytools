package com.lyp.likou.my;

import java.util.*;

/**
 * 560. 和为 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的子数组的个数 。
 * 子数组是数组中元素的连续非空序列。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 2 * 104
 * -1000 <= nums[i] <= 1000
 * -107 <= k <= 107
 */
public class A00560_和为K的子数组 {
    public static int subarraySum(int[] nums, int k) {
        int numsLength = nums.length;
        int result = 0;
        for (int i = 0; i < numsLength; i++) {
            int sum = 0;
            for (int j = i; j < numsLength ; j++) {
                sum += nums[j];
                if(sum ==k){
                    result++;
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 1, 1};
        int k = 2;
        System.out.println(subarraySum(nums, k));
    }
}
