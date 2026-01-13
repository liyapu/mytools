package com.lyp.likou.lk;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 09:56
 *
 * 1. 两数之和
 * 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出 和为目标值 的那 两个 整数，并返回它们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素在答案里不能重复出现。
 *
 * 你可以按任意顺序返回答案。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[0,1]
 * 解释：因为 nums[0] + nums[1] == 9 ，返回 [0, 1] 。
 *
 * 示例 2：
 * 输入：nums = [3,2,4], target = 6
 * 输出：[1,2]
 *
 * 示例 3：
 * 输入：nums = [3,3], target = 6
 * 输出：[0,1]
 *
 *
 * 提示：
 * 2 <= nums.length <= 103
 * -109 <= nums[i] <= 109
 * -109 <= target <= 109
 * 只会存在一个有效答案
 */
public class A00001_两数之和 {

    public static int[] twoSum(int[] nums, int target) {
        // 值对应的索引下标
        Map<Integer, Integer> value$Index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (value$Index.containsKey(target - nums[i])) {
                return new int[]{value$Index.get(target - nums[i]), i};
            }
            value$Index.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
        System.out.println(Arrays.toString(twoSum2(nums, target)));
    }


    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> value2IndexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i];
            if(value2IndexMap.containsKey(target-cur)){
                return new int[] {value2IndexMap.get(target-cur),i};
            }
            value2IndexMap.put(nums[i],i);
        }
        return null;
    }
}
