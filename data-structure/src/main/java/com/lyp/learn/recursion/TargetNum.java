package com.lyp.learn.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 给定一个无重复元素的数组 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的数字可以无限制重复被选取。
 * <p>
 * 说明：所有数字（包括 target）都是正整数。解集不能包含重复的组合。
 * 示例 1:输入: candidates = [2,3,6,7], target = 7,
 * 所求解集为:
 * [
 * [7],
 * [2,2,3]
 * ]
 * <p>
 * 示例 2:输入: candidates = [2,3,5], target = 8,
 * 所求解集为:
 * [
 * [2,2,2,2],
 * [2,3,3],
 * [3,5]
 * ]
 */
public class TargetNum {

    public static void main(String[] args) {
        int [] candidates = new int [] {5,2,3};
        int target = 8;
        TargetNum tn = new TargetNum();

        List<List<Integer>> lists = tn.combinationSum(candidates, target);
        lists.forEach(System.out::println);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        //重要的要大小排列
//        Arrays.sort(candidates);

        List<Integer> temp = new ArrayList<Integer>();

        if (candidates[0] > target) {
            return res;
        }

        int len = candidates.length;

        // 取小于target的数 足证一个临时数组
        for (int i = 0; i < len; i++) {
            int num = candidates[i];
            if (num > target) {
                break;
            }

            temp.add(num);
        } // end for

        //
        find(res, new ArrayList<>(), temp, target, 0);

        return res;
    }

    public void find(List<List<Integer>> res, List<Integer> tmp, List<Integer> candidates, int target, int start) {
        //target==0.找到一个新的解
        if (target == 0) {
            res.add(new ArrayList<>(tmp));
        } else if (target > 0) {
            for (int i = start; i < candidates.size(); i++) {
                int num = candidates.get(i);
                if (num <= target) {
                    tmp.add(num);
                    //查找新的target
                    int newTarget = target - num;
                    find(res, tmp, candidates, newTarget, i);
                    tmp.remove(tmp.size() - 1);
                }

            }//end for
        }


    }


}
