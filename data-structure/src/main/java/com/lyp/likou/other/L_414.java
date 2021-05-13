package com.lyp.likou.other;

import java.util.PriorityQueue;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-13 10:30
 *
 * 414. 第三大的数
 * 给你一个非空数组，返回此数组中 第三大的数 。如果不存在，则返回数组中最大的数。
 *
 *
 * 示例 1：
 * 输入：[3, 2, 1]
 * 输出：1
 * 解释：第三大的数是 1 。
 *
 * 示例 2：
 * 输入：[1, 2]
 * 输出：2
 * 解释：第三大的数不存在, 所以返回最大的数 2 。
 *
 * 示例 3：
 * 输入：[2, 2, 3, 1]
 * 输出：1
 * 解释：注意，要求返回第三大的数，是指在所有不同数字中排第三大的数。
 * 此例中存在两个值为 2 的数，它们都排第二。在所有不同数字中排第三大的数为 1 。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 */
public class L_414 {

    public static int thirdMax(int[] nums) {
        int len = 0;

//        Integer big = null,mid = null,samll = null;
//        for (int num : nums) {
//
//        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(3);
        pq.offer(Integer.MIN_VALUE);
        pq.offer(Integer.MIN_VALUE);
        pq.offer(Integer.MIN_VALUE);
        for (int num : nums) {
            pq.offer(num);
        }

        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println(pq.poll());
        System.out.println("------------");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(thirdMax(new int [] {3, 2, 1}));
        System.out.println(thirdMax(new int [] {1,2}));
        System.out.println(thirdMax(new int [] {2, 2, 3, 1}));
        System.out.println(thirdMax(new int [] {3, 2, 1}));
        System.out.println(thirdMax(new int [] {3, 2, 1}));
    }
}
