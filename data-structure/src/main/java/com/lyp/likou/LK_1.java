package com.lyp.likou;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author liyapu
 * @date 2023-03-14 23:24
 * @description
 */
public class LK_1 {

    public static int[] twoSum(int[] nums, int target) {
        int len = nums.length;
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] == target) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    public static int[] twoSum2(int[] nums, int target) {
        //Map<值，值索引>
        Map<Integer, Integer> value$indexMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int otherNum = target - nums[i];
            if (value$indexMap.containsKey(otherNum)) {
                //另一个值的索引再前面
                return new int[]{value$indexMap.get(otherNum), i};
            }
            value$indexMap.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{2, 7, 11, 15};
        int target = 9;
        System.out.println("twoSum = " + Arrays.toString(twoSum(nums, target)));
        System.out.println("twoSum2 = " + Arrays.toString(twoSum2(nums, target)));

    }
}
