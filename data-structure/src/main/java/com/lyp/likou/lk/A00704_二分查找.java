package com.lyp.likou.lk;

/**
 * 704. 二分查找
 * 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target  ，
 * 写一个函数搜索 nums 中的 target，如果 target 存在返回下标，否则返回 -1。
 *
 * 你必须编写一个具有 O(log n) 时间复杂度的算法。
 *
 *
 * 示例 1:
 * 输入: nums = [-1,0,3,5,9,12], target = 9
 * 输出: 4
 * 解释: 9 出现在 nums 中并且下标为 4
 *
 * 示例 2:
 * 输入: nums = [-1,0,3,5,9,12], target = 2
 * 输出: -1
 * 解释: 2 不存在 nums 中因此返回 -1
 *
 *
 * 提示：
 * 你可以假设 nums 中的所有元素是不重复的。
 * n 将在 [1, 10000]之间。
 * nums 的每个元素都将在 [-9999, 9999]之间。
 */


public class A00704_二分查找 {


    /**
     * 二分查找
     * @param nums
     * @param target
     * @return
     */
    public int search(int[] nums, int target) {
        int len;
        if (nums == null || (len = nums.length) == 0) {
            return -1;
        }

        int left = 0, right = len - 1;
        //这里条件存在 left = right ，所以是 left <= right ,target可能是left和right相等的那个位置
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (target > nums[mid]) {
                left = mid + 1;
            } else if (target < nums[mid]) {
                right = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }
}
/**
 * 相似题型参考
 *
 * A00033_搜索旋转排序数组
 * A00153_寻找旋转排序数组中的最小值
 * A00162_寻找峰值
 *
 *
 */
