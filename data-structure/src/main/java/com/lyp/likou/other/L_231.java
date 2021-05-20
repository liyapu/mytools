package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-20 09:53
 *
 * 231. 2的幂
 * 给定一个整数，编写一个函数来判断它是否是 2 的幂次方。
 *
 * 示例 1:
 * 输入: 1
 * 输出: true
 * 解释: 2^0 = 1
 *
 * 示例 2:
 * 输入: 16
 * 输出: true
 * 解释: 2^4 = 16
 *
 * 示例 3:
 * 输入: 218
 * 输出: false
 */
public class L_231 {

    public static boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        while (n % 2 == 0) {
            n /= 2;
        }

        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(isPowerOfTwo(1));
        System.out.println(isPowerOfTwo(2));
        System.out.println(isPowerOfTwo(3));
        System.out.println(isPowerOfTwo(4));
        System.out.println(isPowerOfTwo(5));
        System.out.println(isPowerOfTwo(6));
        System.out.println(isPowerOfTwo(218));
    }
}
