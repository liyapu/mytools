package com.lyp.likou.array;

import java.util.Arrays;

/**
 * @author liyapu
 * @date 2024-03-29 18:14
 * @description 189. 轮转数组
 * 中等
 * 相关标签
 * 相关企业
 * 提示
 * 给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。
 *
 *
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4,5,6,7], k = 3
 * 输出: [5,6,7,1,2,3,4]
 * 解释:
 * 向右轮转 1 步: [7,1,2,3,4,5,6]
 * 向右轮转 2 步: [6,7,1,2,3,4,5]
 * 向右轮转 3 步: [5,6,7,1,2,3,4]
 *
 * 示例 2:
 * 输入：nums = [-1,-100,3,99], k = 2
 * 输出：[3,99,-1,-100]
 * 解释:
 * 向右轮转 1 步: [99,-1,-100,3]
 * 向右轮转 2 步: [3,99,-1,-100]
 *
 *
 * 提示：
 * 1 <= nums.length <= 105
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 *
 *
 * 进阶：
 * 尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
 * 你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */
public class A_189 {

    /**
     * 可以执行正确求解，但是超时
     *
     * @param nums
     * @param k
     */
    public void rotate6(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (k == 0) {
            return;
        }
        for (int i = 0; i < k; i++) {
            int tail = nums[length - 1];
            for (int j = length - 1; j >= 1; j--) {
                nums[j] = nums[j - 1];
            }
            nums[0] = tail;
        }
    }

    public void rotate(int[] nums, int k) {
        int length = nums.length;
        k = k % length;
        if (k == 0) {
            return;
        }
        int[] resultArr = new int[length];
        for (int i = 0; i < length; i++) {
            int tempIndex = (i + k) % length;
            resultArr[tempIndex] = nums[i];
        }
        System.arraycopy(resultArr, 0, nums, 0, length);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7};
        System.out.println(Arrays.toString(nums));
        new A_189().rotate(nums, 3);
        System.out.println(Arrays.toString(nums));
        System.out.println("--------------");

        int[] nums2 = new int[]{-1, -100, 3, 99};
        System.out.println(Arrays.toString(nums2));
        new A_189().rotate(nums2, 2);
        System.out.println(Arrays.toString(nums2));
    }
}
