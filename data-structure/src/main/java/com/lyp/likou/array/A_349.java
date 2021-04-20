package com.lyp.likou.array;

import java.util.HashSet;
import java.util.Set;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 16:16
 *
 * 349. 两个数组的交集
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2]
 *
 * 示例 2：
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[9,4]
 *
 *
 * 说明：
 * 输出结果中的每个元素一定是唯一的。
 * 我们可以不考虑输出结果的顺序。
 */
public class A_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        if(nums1 == null || nums2 == null) return new int[0];

        Set<Integer> seeSet =new HashSet<>();
        //用 ansSet, 保证在 结果中不存在重复的数字
        Set<Integer> ansSet = new HashSet<>();

        for (int i = 0; i < nums1.length; i++) {
            seeSet.add(nums1[i]);
        }

        for (int i = 0; i < nums2.length; i++) {
            if(seeSet.contains(nums2[i])){
                ansSet.add(nums2[i]);
            }
        }

        int [] ansArr = new int [ansSet.size()];
        int index = 0;
        for (Integer num : ansSet) {
            ansArr[index++] = num;
        }
        return ansArr;
    }
}
