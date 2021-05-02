package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-02 23:35
 * 35. 搜索插入位置
 * 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 *
 * 你可以假设数组中无重复元素。
 *
 * 示例 1:
 * 输入: [1,3,5,6], 5
 * 输出: 2
 *
 * 示例 2:
 * 输入: [1,3,5,6], 2
 * 输出: 1
 *
 * 示例 3:
 * 输入: [1,3,5,6], 7
 * 输出: 4
 *
 * 示例 4:
 * 输入: [1,3,5,6], 0
 * 输出: 0
 */
public class A_35 {

    /**
     * 暴力解法，遍历
     * @param nums
     * @param target
     * @return
     */
//    public static int searchInsert(int[] nums, int target) {
//
//        int eqIndex = -1;
//        int ltIndex = 0;
//
//        for (int i = 0; i < nums.length; i++) {
//            if(nums[i] == target){
//                eqIndex = i;
//                break;
//            }else if(target > nums[i]){
//                ltIndex = i+1;
//            }
//        }
//        return eqIndex > 0 ? eqIndex : ltIndex;
//    }
    public static int searchInsert(int[] nums, int target) {
        int len = nums.length;
        int left = 0, right = len - 1;
        int middle = 0;
        int index = 0;

        while (left <= right) {
            middle = (left + right) / 2;
            if (nums[middle] < target) {
                //小于的时候，返回 index 要加 1
                index = middle + 1;
                left = middle + 1;
            } else if (nums[middle] == target) {
                index = middle;
                break;
            } else {
                right = middle - 1;
            }
        }
        return index;
    }

    public static void main(String[] args) {
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 5));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 2));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 7));
        System.out.println(searchInsert(new int[]{1, 3, 5, 6}, 0));
    }
}
