package com.lyp.likou.other;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 10:40
 *
 * 216. 组合总和 III
 * 找出所有相加之和为 n 的 k 个数的组合。组合中只允许含有 1 - 9 的正整数，并且每种组合中不存在重复的数字。
 *
 * 说明：
 *
 * 所有数字都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: k = 3, n = 7
 * 输出: [[1,2,4]]
 *
 * 示例 2:
 * 输入: k = 3, n = 9
 * 输出: [[1,2,6], [1,3,5], [2,3,4]]
 */
public class L_216 {

    public static List<List<Integer>> combinationSum3(int k, int n) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();

        if (k <= 0 || n <= 0) return result;

        backTrackSum(result, path, nums, 0, n, k);
        return result;
    }

    /**
     *
     * @param result
     * @param path
     * @param nums
     * @param begin 数组下标开始搜索位置
     * @param target 目标和
     * @param count  数字个数
     */
    private static void backTrackSum(List<List<Integer>> result, List<Integer> path, int[] nums, int begin, int target, int count) {
        if (target == 0 && path.size() == count) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < nums.length; i++) {
            if (nums[i] > target) {
                break;
            }
            path.add(nums[i]);
            backTrackSum(result, path, nums, i + 1, target - nums[i], count);
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        combinationSum3(3, 7).stream().forEach(s -> System.out.println(s));
        System.out.println("-------------");
        combinationSum3(3, 9).stream().forEach(s -> System.out.println(s));

    }
}
