package com.lyp.likou.array;

import java.util.*;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 17:04
 *
 * 350. 两个数组的交集 II
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 *
 *
 * 示例 1：
 * 输入：nums1 = [1,2,2,1], nums2 = [2,2]
 * 输出：[2,2]
 *
 * 示例 2:
 * 输入：nums1 = [4,9,5], nums2 = [9,4,9,8,4]
 * 输出：[4,9]
 *
 *
 * 说明：
 *
 * 输出结果中每个元素出现的次数，应与元素在两个数组中出现次数的最小值一致。
 * 我们可以不考虑输出结果的顺序。
 * 进阶：
 *
 * 如果给定的数组已经排好序呢？你将如何优化你的算法？
 * 如果 nums1 的大小比 nums2 小很多，哪种方法更优？
 * 如果 nums2 的元素存储在磁盘上，内存是有限的，并且你不能一次加载所有的元素到内存中，你该怎么办？
 */
public class A_350 {
    public static int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> nums1Value$index = new HashMap<>();
        for (int i = 0; i < nums1.length; i++) {
            nums1Value$index.put(nums1[i], nums1Value$index.getOrDefault(nums1[i], 0) + 1);
        }

        List<Integer> ansList = new ArrayList<>();
        for (int i = 0; i < nums2.length; i++) {
            if (nums1Value$index.containsKey(nums2[i])) {
                ansList.add(nums2[i]);
                Integer times = nums1Value$index.get(nums2[i]);
                if (times == 1) {
                    nums1Value$index.remove(nums2[i]);
                } else {
                    nums1Value$index.put(nums2[i], times - 1);
                }
            }
        }

        int[] arr = new int[ansList.size()];
        for (int i = 0; i < ansList.size(); i++) {
            arr[i] = ansList.get(i);
        }

        return arr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(intersect(new int[]{1, 2, 2, 1}, new int[]{2, 2})));
        System.out.println(Arrays.toString(intersect(new int[]{4, 9, 5}, new int[]{9, 4, 9, 8, 4})));
    }
}
