package com.lyp.likou.lk;

import java.util.Arrays;

/**
 * 34.在排序数组中查找元素的第一个和最后一个位置
 *
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class A00034_在排序数组中查找元素的第一个和最后一个位置 {


    public static int[] searchRange(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        while (left <= right) {

            int mid = left + (right - left) / 2;
            int num = nums[mid];

            if (target == num) {
                int first = mid, second = mid;
                while (first >= 0 && nums[first] == num) {
                    first--;
                }
                while (second < len && nums[second] == num) {
                    second++;
                }
                return new int[]{first + 1, second - 1};

            } else if (target > num) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return new int[]{-1, -1};
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 7, 7, 8, 8, 10};
        int target = 8;
        System.out.println(Arrays.toString(searchRange(nums, target)));
    }


}
