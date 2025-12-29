package com.lyp.likou.my;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * 128. 最长连续序列
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *
 * 示例 3：
 * 输入：nums = [1,0,1,2]
 * 输出：3
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 */
public class A00128_最长连续序列 {

    public static int longestConsecutive(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int maxLength = 0;
        int arrLength = nums.length;

        for (int i = 0; i < arrLength; i++) {
            int j = i + 1;
            /*
               minus 解决下面的情况,记录重复值
               输入 nums = [1,0,1,2]
               输出
               2
               预期结果
                3
             */
            //记录重复值出现的次数，到时候需要减去
            int minus = 0;
            for (; j < arrLength; j++) {
                if (nums[j] == nums[j - 1]) {
                    //相邻值相同 则记录，并且继续
                    minus++;
                    continue;
                }
                if (nums[j] - nums[j - 1] != 1) {
                    //差值为1则继续
                    break;
                }
            }
            int maxTemp = j - i - minus;
            if (maxTemp > maxLength) {
                maxLength = maxTemp;
            }
            //跳跃给i赋值，解决算法代码超时问题
            i = j - 1;
        }
        return maxLength;
    }

    public static int longestConsecutive2(int[] nums) {
        if (Objects.isNull(nums) || nums.length == 0) {
            return 0;
        }
        //使用set集合，去重
        Set<Integer> numSet = new HashSet<>();
        for (int num : nums) {
            numSet.add(num);
        }
        int maxLength = 0;
        //遍历set集合
        for (Integer num : numSet) {
            //不包含，则停止，这样找到当前序数列的最开始的数
            if(!numSet.contains(num-1)){
                int cur = num;
                int tempLength = 1;
                while (numSet.contains(cur+1)){
                    //确定包含后，再加1
                    cur +=1;
                    tempLength +=1;
                }
                maxLength = Math.max(maxLength,tempLength);
            }
        }
        return maxLength;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{100, 4, 200, 1, 3, 2};
        int[] nums1 = new int[]{1, 0, 1, 2};

        System.out.println(longestConsecutive(nums));
        System.out.println(longestConsecutive(nums1));

        System.out.println(longestConsecutive2(nums));
        System.out.println(longestConsecutive2(nums1));


    }
}
