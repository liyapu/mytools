package com.lyp.likou.swordOffer;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-15 10:44
 *
 * 剑指 Offer 42. 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 *
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 *
 * 提示：
 *
 * 1 <= arr.length <= 10^5
 * -100 <= arr[i] <= 100
 *
 * 注意：本题与主站 53 题相同：https://leetcode-cn.com/problems/maximum-subarray/
 */
public class O_42 {

    public static int maxSubArray(int[] nums) {
        if (null == nums) {
            return Integer.MIN_VALUE;
        }
        int len = nums.length;
        if (len == 0) {
            return nums[0];
        }

        int pre = 0;
        int maxResult = nums[0];
        for (int i = 0; i < len; i++) {
            pre = Math.max(pre + nums[i], nums[i]);
            maxResult = Math.max(pre, maxResult);
        }
        return maxResult;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
    }
}
