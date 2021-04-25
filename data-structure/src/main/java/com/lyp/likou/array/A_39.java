package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-25 11:06
 *
 * 39. 组合总和
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 *
 * candidates 中的数字可以无限制重复被选取。
 *
 * 说明：
 *
 * 所有数字（包括 target）都是正整数。
 * 解集不能包含重复的组合。
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7,
 * 所求解集为：
 * [
 *   [7],
 *   [2,2,3]
 * ]
 *
 * 示例 2：
 * 输入：candidates = [2,3,5], target = 8,
 * 所求解集为：
 * [
 *   [2,2,2,2],
 *   [2,3,3],
 *   [3,5]
 * ]
 *
 *
 * 提示：
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都是独一无二的。
 * 1 <= target <= 500
 */
public class A_39 {

    /**
     * 遇到这一类相同元素不计算顺序的问题，我们在搜索的时候就需要 按某种顺序搜索。
     * 具体的做法是：每一次搜索的时候设置 下一轮搜索的起点 begin
     *
     * 什么时候使用 used 数组，什么时候使用 begin 变量 ???
     *    有些朋友可能会疑惑什么时候使用 used 数组，什么时候使用 begin 变量。这里为大家简单总结一下：
     *    排列问题，讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为不同列表时），需要记录哪些数字已经使用过，此时用 used 数组；
     *    组合问题，不讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为相同列表时），需要按照某种顺序搜索，此时使用 begin 变量。
     *    注意：具体问题应该具体分析， 理解算法的设计思想 是至关重要的，请不要死记硬背。
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param candidates
     * @param target
     * @return
     */
    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        if (candidates == null || candidates.length == 0) return result;

        //这里进行排序,然后和 begin 进行组合，可以达到去重的目的
        Arrays.sort(candidates);
        backTrack(result, path, candidates, target, 0);
        return result;
    }

    private static void backTrack(List<List<Integer>> result, List<Integer> path, int[] candidates, int target, int begin) {
        if (target == 0) {
            result.add(new ArrayList<>(path));
            return;
        }

        for (int i = begin; i < candidates.length; i++) {
            if (candidates[i] > target) {
                break;
            }
            path.add(candidates[i]);
            System.out.println("递归之前 => " + path + "，剩余 = " + (target - candidates[i]));

            //这里递归 传入的是 i 并且不加1 表示此数还可以使用 ,传入的不是 begin,表示begin之前的数字都不考虑了，进而去重
            backTrack(result, path, candidates, target - candidates[i], i);
            path.remove(path.size() - 1);
            System.out.println("递归之后 => " + path);
        }

    }

    public static void main(String[] args) {
        combinationSum(new int[]{2, 3, 6, 7}, 7).stream().forEach(s -> System.out.println(s));
        System.out.println("------------");
        combinationSum(new int[]{2,3,5}, 8).stream().forEach(s -> System.out.println(s));
    }

}
