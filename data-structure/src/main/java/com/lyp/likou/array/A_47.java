package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-23 16:13
 *
 * 47. 全排列 II
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 *
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 */
public class A_47 {

    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Boolean[] visited = new Boolean[nums.length];
        Arrays.fill(visited, false);

        //数组要进行排序，让相等的元素挨着
        Arrays.sort(nums);
        backTrack(result, new ArrayList<Integer>(), nums, visited, 0);
        return result;
    }

    private static void backTrack(List<List<Integer>> result, ArrayList<Integer> al, int[] nums, Boolean[] visited, int depth) {
        if (depth == nums.length) {
            result.add(new ArrayList<>(al));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            // 要解决重复问题，我们只要设定一个规则，保证在填第 idx 个数的时候重复数字只会被填入一次即可。
            // 而在本题解中，我们选择对原数组排序，保证相同的数字都相邻，然后每次填入的数一定是这个数所在重复数集合中「从左往右第一个未被填过的数字」，
            // 即如下的判断条件：
            // 已经访问过的  或者 和前一个数相等
            // 剪枝,!visited[i-1]保证确实是在同一层发生了重复的选择
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i-1])) {
                continue;
            }
            al.add(nums[i]);
            visited[i] = true;
            backTrack(result, al, nums, visited, depth + 1);
            visited[i] = false;
            al.remove(al.size() - 1);
        }
    }

    public static void main(String[] args) {
//        permuteUnique(new int[]{1, 2, 3}).stream().forEach(s -> System.out.println(s));
        System.out.println("-------------");
        permuteUnique(new int[]{1, 1, 2}).stream().forEach(s -> System.out.println(s));

    }
}
