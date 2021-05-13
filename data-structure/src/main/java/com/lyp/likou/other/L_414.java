package com.lyp.likou.other;

import java.util.HashSet;
import java.util.Set;

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
        //解决 nums 中，出现 Integer.MIN_VALUE
        Set<Integer> seen = new HashSet<>();
        //初始化值
        int big = Integer.MIN_VALUE, middle = Integer.MIN_VALUE, small = Integer.MIN_VALUE;
        for (int num : nums) {

            if(seen.size() < 3){
                seen.add(num);
            }
            //去除过滤 和初始化值相等的值
            if (num == big || num == middle || num == small) {
                continue;
            }
            if (num > big) {
                small = middle;
                middle = big;
                big = num;
            } else if (num > middle) {
                small = middle;
                middle = num;
            } else if (num > small) {
                small = num;
            }
        }
        return (seen.size() < 3) ? big : small;
    }

    public static void main(String[] args) {
        System.out.println(thirdMax(new int[]{3, 2, 1}));
        System.out.println(thirdMax(new int[]{1, 2}));
        System.out.println(thirdMax(new int[]{2, 2, 3, 1}));
        System.out.println(thirdMax(new int[]{3, 2, 1}));
        System.out.println(thirdMax(new int[]{3, 2, 1}));
    }
}
