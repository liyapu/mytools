package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-23 11:08
 * 46. 全排列
 * 给定一个 没有重复 数字的序列，返回其所有可能的全排列。
 *
 * 示例:
 *
 * 输入: [1,2,3]
 * 输出:
 * [
 *   [1,2,3],
 *   [1,3,2],
 *   [2,1,3],
 *   [2,3,1],
 *   [3,1,2],
 *   [3,2,1]
 * ]
 */
public class A_46 {

    /**
     * 回溯法
     * 每次 改变数组，消耗大
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backTrack(result, nums, new ArrayList<Integer>());
        return result;
    }

    private static void backTrack(List<List<Integer>> result, int[] nums, ArrayList<Integer> al) {
        //终止条件
        if (nums == null || nums.length == 0) {
            //这里要 复制一遍，不然都是 引用都是空
            result.add(new ArrayList<>(al));
            return;
        }

        //处理一次的逻辑
        for (int i = 0; i < nums.length; i++) {
            al.add(nums[i]);
            //这里新建数组，除 当前 i 之外的数组
            int[] newArr = new int[nums.length - 1];
            int index = 0;
            for (int j = 0; j < nums.length; j++) {
                if (nums[j] != nums[i]) {
                    newArr[index++] = nums[j];
                }
            }
            backTrack(result, newArr, al);
            //删除本次添加的,要根据长度减一删除，不能删除 al.remove(i)
            al.remove(al.size() - 1);
        }

    }

    /**
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> permute2(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Boolean[] used = new Boolean[nums.length];
        Arrays.fill(used, false);

        backTrack(result, nums, used, new ArrayList<Integer>(),0);
        return result;
    }

    private static void backTrack(List<List<Integer>> result, int[] nums, Boolean[] used, ArrayList<Integer> al,int depth) {
        // depth 这么深，表示已经到了 最后
        if (depth == nums.length) {
            result.add(new ArrayList<>(al));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (!used[i]) {
                al.add(nums[i]);
                // 标记使用，下面并且要 复位
                used[i] = true;
                backTrack(result, nums, used, al,depth+1);
                used[i] = false;
                al.remove(al.size() - 1);
            }
        }
    }

    public static void main(String[] args) {
        permute(new int[]{1, 2, 3}).forEach(s -> System.out.println(s));
        System.out.println("------------");
        permute2(new int[]{1, 2, 3}).forEach(s -> System.out.println(s));

    }
}
