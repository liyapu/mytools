package com.lyp.likou.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 14:57
 *
 * 18. 四数之和
 * 给定一个包含 n 个整数的数组 nums 和一个目标值 target，判断 nums 中是否存在四个元素 a，b，c 和 d ，使得 a + b + c + d 的值与 target 相等？找出所有满足条件且不重复的四元组。
 *
 * 注意：答案中不可以包含重复的四元组。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 *
 * 示例 2：
 * 输入：nums = [], target = 0
 * 输出：[]
 *
 *
 * 提示：
 * 0 <= nums.length <= 200
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 */
public class A_18 {
    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ansList = new ArrayList<>();
        int len = 0;
        if (nums == null || (len = nums.length) < 4) {
            return ansList;
        }
        //排序很重要
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < len; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = len - 1;
                while (left < right) {
                    int sum = nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ansList.add(new ArrayList<>(Arrays.asList(nums[i], nums[j], nums[left], nums[right])));
                        left++;
                        right--;
                        while (left < right && nums[left] == nums[left - 1]) {
                            left++;
                        }
                        while (left < right && nums[right] == nums[right + 1]) {
                            right--;
                        }
                    } else if (sum > target) {
                        right--;
                    } else {
                        left++;
                    }
                }
            }
        }
        return ansList;
    }

    public static void main(String[] args) {
//        int[] nums = {1, 0, -1, 0, -2, 2};
//        int target = 0;
//        List<List<Integer>> lists = fourSum(nums, target);
//        lists.forEach(list -> System.out.println(list));

        System.out.println("-----------");

        int[] nums2 = {2, 2, 2, 2, 2};
        int target2 = 8;
        List<List<Integer>> lists2 = fourSum(nums2, target2);
        lists2.forEach(list -> System.out.println(list));
    }
}
