package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-19 16:50
 *
 * 172. 阶乘后的零
 * 给定一个整数 n，返回 n! 结果尾数中零的数量。
 *
 * 示例 1:
 * 输入: 3
 * 输出: 0
 * 解释: 3! = 6, 尾数中没有零。
 *
 * 示例 2:
 * 输入: 5
 * 输出: 1
 * 解释: 5! = 120, 尾数中有 1 个零.
 * 说明: 你算法的时间复杂度应为 O(log n) 。
 */
public class L_172 {

    public int trailingZeroes(int n) {
        int zeroCount = 0;
        for (int i = 5; i <= n; i++) {
            int curr = i;
            while (curr % 5 == 0) {
                curr /= 5;
                zeroCount++;

            }
        }
        return zeroCount;
    }

    public static void main(String[] args) {

    }
}
