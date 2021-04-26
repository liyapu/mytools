package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 10:23
 *
 * 90. 子集 II
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 *
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,2,2]
 * 输出：[[],[1],[1,2],[1,2,2],[2],[2,2]]
 * 示例 2：
 *
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 */
public class A_90 {
    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        int len = 0;
        if (nums == null || ((len = nums.length) == 0)) {
            return result;
        }

        Arrays.sort(nums);

        for (int i = 0; i <= len; i++) {
            if (i == 0) {
                result.add(new ArrayList<>());
                continue;
            }
            backTrack(result, path, nums, 0, i);
        }
        return result;
    }

    private static void backTrack(List<List<Integer>> result, List<Integer> path, int[] nums, int begin, int count) {
        if (path.size() == count) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            // i 大于 begin
            if (i > begin && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);
            backTrack(result, path, nums, i + 1, count);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        subsetsWithDup(new int[]{1, 2, 2}).stream().forEach(s -> System.out.println(s));
        System.out.println("--------------");
        subsetsWithDup(new int[]{0}).stream().forEach(s -> System.out.println(s));
    }
}
