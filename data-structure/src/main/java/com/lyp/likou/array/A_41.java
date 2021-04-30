//package com.lyp.likou.array;
//
//import java.util.HashSet;
//import java.util.Set;
//
///**
// *@author: liyapu
// *@description:
// *@date 2021-04-30 17:23
// *
// * 41. 缺失的第一个正数
// * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
// *
// *
// * 进阶：你可以实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案吗？
// *
// *
// *
// * 示例 1：
// * 输入：nums = [1,2,0]
// * 输出：3
// *
// * 示例 2：
// * 输入：nums = [3,4,-1,1]
// * 输出：2
// *
// * 示例 3：
// * 输入：nums = [7,8,9,11,12]
// * 输出：1
// *
// *
// * 提示：
// * 0 <= nums.length <= 300
// * -231 <= nums[i] <= 231 - 1
// */
//public class A_41 {
//    /**
//     * 存入set中，然后再次遍历
//     * @param nums
//     * @return
//     */
//    public static int firstMissingPositive(int[] nums) {
//        int len = nums.length;
//
//        Set<Integer> set = new HashSet<>();
//
//        for (int i = 0; i < len; i++) {
//            set.add(nums[i]);
//        }
//
//        for (int i = 1; i <= len; i++) {
//            if (!set.contains(i)) {
//                return i;
//            }
//        }
//        return len + 1;
//    }
//
//
//    public static int firstMissingPositive2(int[] nums) {
//        int len = nums.length;
//        for (int i = 0; i < len; i++) {
//            if (nums[i] <= 0) {
//                nums[i] = len+1;
//            }
//        }
//
//        for (int i = 0; i < len; i++) {
//            int index = Math.abs(nums[i]);
//            if(index < len){
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//        System.out.println(firstMissingPositive(new int[]{1, 2, 0}));
//        System.out.println(firstMissingPositive(new int[]{1, 2, 3}));
//        System.out.println(firstMissingPositive(new int[]{3, 4, -1, 1}));
//        System.out.println(firstMissingPositive(new int[]{7, 8, 9, 11, 12}));
//    }
//}
