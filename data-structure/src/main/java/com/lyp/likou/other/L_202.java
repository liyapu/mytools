package com.lyp.likou.other;

import java.util.HashSet;
import java.util.Set;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 15:16
 *
 * 202. 快乐数
 * 编写一个算法来判断一个数 n 是不是快乐数。
 *
 * 「快乐数」定义为：
 *
 * 对于一个正整数，每一次将该数替换为它每个位置上的数字的平方和。
 * 然后重复这个过程直到这个数变为 1，也可能是 无限循环 但始终变不到 1。
 * 如果 可以变为  1，那么这个数就是快乐数。
 * 如果 n 是快乐数就返回 true ；不是，则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：19
 * 输出：true
 * 解释：
 * 1^2 + 9^2 = 82
 * 8^2 + 2^2 = 68
 * 6^2 + 8^2 = 100
 * 1^2 + 0^2 + 0^2 = 1
 *
 * 示例 2：
 * 输入：n = 2
 * 输出：false
 *
 *
 * 提示：
 * 1 <= n <= 231 - 1
 */
public class L_202 {
    public static boolean isHappy(int n) {
        Set<Integer> seenSet = new HashSet<>();
        n = getNext(n);
        while (n != 1 && !seenSet.contains(n)) {
            seenSet.add(n);
            n = getNext(n);
        }
        return n == 1;
    }

    private static int getNext(int n) {
        int sum = 0;
        //不断循环此过程，就可以得到每位上的数字
        while (n > 0) {
            //模除10 可以得到 个位上的数字
            int d = n % 10;
            n = n / 10;
            sum += d * d;
        }
        return sum;
    }


    public static void main(String[] args) {
        System.out.println(isHappy(19));
        System.out.println(isHappy(7));
        System.out.println(isHappy(1111111));
        System.out.println(isHappy(2));
    }
}
