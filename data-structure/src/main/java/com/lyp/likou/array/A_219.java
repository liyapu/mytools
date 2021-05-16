package com.lyp.likou.array;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-11 17:49
 *
 * 219. 存在重复元素 II
 * 给定一个整数数组和一个整数 k，判断数组中是否存在两个不同的索引 i 和 j，
 * 使得 nums [i] = nums [j]，并且 i 和 j 的差的 绝对值 至多为 k。
 *
 *
 * 示例 1:
 * 输入: nums = [1,2,3,1], k = 3
 * 输出: true
 *
 * 示例 2:
 * 输入: nums = [1,0,1,1], k = 1
 * 输出: true
 *
 * 示例 3:
 * 输入: nums = [1,2,3,1,2,3], k = 2
 * 输出: false
 */
public class A_219 {

    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        Map<Integer,Integer> value$Index = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int key = nums[i];
            if(value$Index.containsKey(key)){
                Integer index = value$Index.get(key);
                if(Math.abs(i - index) <= k){
                    return true;
                }else{
                    value$Index.put(key,i);
                }
            }else{
                value$Index.put(key,i);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        System.out.println(containsNearbyDuplicate(new int[]{99, 99}, 2));
        System.out.println(containsNearbyDuplicate(new int[]{99, 99}, 10));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1}, 3));
        System.out.println(containsNearbyDuplicate(new int[]{1, 0, 1, 1}, 1));
        System.out.println(containsNearbyDuplicate(new int[]{1, 2, 3, 1, 2, 3}, 2));
    }
}
