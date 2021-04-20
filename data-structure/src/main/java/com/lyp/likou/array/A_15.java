package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 14:26
 *
 * 15. 三数之和
 * 给你一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 *
 * 注意：答案中不可以包含重复的三元组。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 *
 * 示例 2：
 * 输入：nums = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：nums = [0]
 * 输出：[]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class A_15 {
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ansList = new ArrayList<>();
        int len = 0;
        if (nums == null || (len = nums.length) <= 2) return ansList;
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            // 需要和上一次枚举的数不相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int target = -nums[i];
            // k 对应的指针初始指向数组的最右端
            int k = len - 1;
            for (int j = i + 1; j < len; j++) {
                // 需要和上一次枚举的数不相同
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 需要保证 j 的指针在 k 的指针的左侧
                while (j < k && nums[j] + nums[k] > target) {
                    k--;
                }
                // 如果指针重合，随着 j 后续的增加
                // 就不会有满足 i+j+k=0 并且 j<k 的 k 了，可以退出循环
                if (j == k) {
                    break;
                }
                if (nums[j] + nums[k] == target) {
                    List<Integer> ans = new ArrayList<>();
                    ans.add(nums[i]);
                    ans.add(nums[j]);
                    ans.add(nums[k]);
                    ansList.add(ans);
                }
            }
        }
        return ansList;
    }

    /**
     * Leetcode: 15. 三数之和
     * https://blog.csdn.net/starflyyy/article/details/106955473
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {// 总时间复杂度：O(n^2)
        List<List<Integer>> ansList = new ArrayList<>();
        int len = 0;
        if (nums == null || (len = nums.length) <= 2) return ansList;

        Arrays.sort(nums); // O(nlogn)

        for (int i = 0; i < len - 2; i++) { // O(n^2)
            if (nums[i] > 0) break; // 第一个数大于 0，后面的数都比它大，肯定不成立了
            if (i > 0 && nums[i] == nums[i - 1]) continue; // 去掉重复情况
            int target = -nums[i];
            int left = i + 1, right = len - 1;

            while (left < right) {
                if (nums[left] + nums[right] == target) {
                    ansList.add(new ArrayList<>(Arrays.asList(nums[i], nums[left], nums[right])));

                    // 现在要增加 left，减小 right，但是不能重复，比如: [-2, -1, -1, -1, 3, 3, 3], i = 0, left = 1, right = 6, [-2, -1, 3] 的答案加入后，需要排除重复的 -1 和 3
                    left++;
                    right--; // 首先无论如何先要进行加减操作
                    while (left < right && nums[left] == nums[left - 1]) left++;
                    while (left < right && nums[right] == nums[right + 1]) right--;
                } else if (nums[left] + nums[right] < target) {
                    left++;
                } else {  // nums[left] + nums[right] > target
                    right--;
                }
            }
        }
        return ansList;
    }

    public static void main(String[] args) {
        int[] arr = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists = threeSum(arr);
        lists.forEach(list -> System.out.println(list));

        System.out.println("-----------");
        int[] arr2 = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> lists2 = threeSum2(arr2);
        lists2.forEach(list -> System.out.println(list));
    }
}
