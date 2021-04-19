package com.lyp.likou.bit;

/**
 *@author: liyapu
 *@description:136. 只出现一次的数字
 * 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 说明：
 * 你的算法应该具有线性时间复杂度。 你可以不使用额外空间来实现吗？
 *
 * 示例 1:
 * 输入: [2,2,1]
 * 输出: 1
 *
 * 示例 2:
 * 输入: [4,1,2,1,2]
 * 输出: 4
 *@date 2021-04-19 16:18
 *
 *
 */
public class B_136 {

    /**
     * 答案是使用位运算。对于这道题，可使用异或运算 \oplus⊕。异或运算有以下三个性质。
     *
     * 任何数和 0 做异或运算，结果仍然是原来的数，a^0 = a
     * 任何数和其自身做异或运算，结果是 0, a^a=0
     * 异或运算满足交换律和结合律，即 a^b^a=b^a^a=b^(a^a)=b^0=b
     *
     *https://leetcode-cn.com/problems/single-number/solution/zhi-chu-xian-yi-ci-de-shu-zi-by-leetcode-solution/
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int single = 0;
        for (int num : nums) {
            single ^= num;
        }
        return single;
    }
}
