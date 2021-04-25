package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-25 11:36
 *
 * 40. 组合总和 II
 * 给定一个数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的每个数字在每个组合中只能使用一次。
 *
 * 说明：
 *
 * 所有数字（包括目标数）都是正整数。
 * 解集不能包含重复的组合。
 *
 * 示例 1:
 * 输入: candidates = [10,1,2,7,6,1,5], target = 8,
 * 所求解集为:
 * [
 *   [1, 7],
 *   [1, 2, 5],
 *   [2, 6],
 *   [1, 1, 6]
 * ]
 *
 * 示例 2:
 * 输入: candidates = [2,5,2,1,2], target = 5,
 * 所求解集为:
 * [
 *   [1,2,2],
 *   [5]
 * ]
 */
public class A_40 {
    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;
        Boolean[] used = new Boolean[candidates.length];
        Arrays.fill(used, false);
        Arrays.sort(candidates);

        backTrack(result, path, candidates, used, target, 0);
        return result;
    }

    private static void backTrack(List<List<Integer>> result, List<Integer> path, int[] candidates, Boolean[] used, int target, int index) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (used[i]) continue;
            //此处i > index
            if (i > index && candidates[i] == candidates[i - 1]) {
                continue;
            }
            if (candidates[i] > target) break;
            used[i] = true;
            path.add(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));
            //此处 i 加 1 进行递归
            backTrack(result, path, candidates, used, target - candidates[i], i + 1);
            path.remove(path.size() - 1);
            used[i] = false;
            System.out.println("递归之后 => " + path);

        }
    }

    public static void main(String[] args) {
        combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8).stream().forEach(s -> System.out.println(s));
        System.out.println("------------");
        combinationSum2(new int[]{2, 5, 2, 1, 2}, 5).stream().forEach(s -> System.out.println(s));
    }
}
