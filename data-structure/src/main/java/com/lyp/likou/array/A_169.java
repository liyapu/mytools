package com.lyp.likou.array;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 11:09
 *
 * 169. 多数元素
 * 给定一个大小为 n 的数组，找到其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 *
 *
 * 示例 1：
 * 输入：[3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：[2,2,1,1,1,2,2]
 * 输出：2
 *
 *
 * 进阶：
 *
 * 尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class A_169 {
    public  static int majorityElement(int[] nums) {
        Map<Integer,Integer> value$Times = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            value$Times.put(nums[i],value$Times.getOrDefault(nums[i],0)+1);
        }

        int count = nums.length/2;
        for(Map.Entry<Integer,Integer> entry : value$Times.entrySet()){
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if(value > count){
                return key;
            }
        }
        return -1;
    }
}
