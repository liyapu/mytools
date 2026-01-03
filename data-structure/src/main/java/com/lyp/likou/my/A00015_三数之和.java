package com.lyp.likou.my;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 15.三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class A00015_三数之和 {
    public static List<List<Integer>> threeSum(int[] nums) {

        int numLength = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numLength; i++) {
            int firstNum = nums[i];
            Map<Integer,Integer> value2indexMap = new HashMap<>();
            //firstNum 已确定，secondNum如果已经出现了，则 threeNum也是固定的了，这种情况直接跳过，避免重复
            Set<Integer> repeat = new HashSet<>();

            for (int j = i+1; j < numLength; j++) {
                if(repeat.contains(nums[j])){
                    continue;
                }
                // A + B + C = 0
                //     B + C = -A
                //         C = -A-B
                if(value2indexMap.containsKey((-firstNum)-nums[j])){
                    List<Integer> numList= new ArrayList<>();
                    numList.add(firstNum);
                    numList.add(nums[j]);
                    numList.add((-firstNum)-nums[j]);
                    result.add(numList);

                    repeat.add(nums[j]);
                }
                value2indexMap.put(nums[j],j);
            }
        }
       return result.stream()
                .map(a -> a.stream().sorted().collect(Collectors.toList()))
                .distinct()
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums = new int[] {-1,0,1,2,-1,-4};
        System.out.println(threeSum(nums));
    }
}
