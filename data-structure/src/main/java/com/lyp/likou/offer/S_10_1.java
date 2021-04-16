package com.lyp.likou.offer;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-16 17:09
 *
 * 剑指 Offer 10- I. 斐波那契数列
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,
 * F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *
 *
 * 提示：
 *
 * 0 <= n <= 100
 */
public class S_10_1 {

    /**
     * 原始递归计算
     *
     * 存在大量的重复计算
     * @param n
     * @return
     */
    public static int fib(int n) {
        if (0 == n) return 0;
        if (1 == n) return 1;
        if (2 == n) return 1;
        //需要对每次的计算结果，都进行 取模操作
        return (fib(n - 1) + fib(n - 2)) % 1000000007;
    }

    /**
     * 缓存已经计算过的
     * 空间复杂度增高
     * @param n
     * @return
     */
    public static int fib2(int n) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        map.put(1, 1);
        map.put(2, 1);

        int sum = fibCache(n, map);
        return sum;
    }

    private static int fibCache(int n, Map<Integer, Integer> map) {
        if (map.containsKey(n)) return map.get(n);
        int sum = (fibCache(n - 1, map) + fibCache(n - 2, map)) % 1000000007;
        map.put(n, sum);
        return sum;
    }

    public static int fib3(int n) {
        if (0 == n) return 0;
        if (1 == n) return 1;
        if (2 == n) return 1;

        int x = 0, y = 1, z = 1, temp = 0;
        for (int i = 3; i <= n; i++) {
            temp = y + z;
            x = y;
            y = z;
            z = temp % 1000000007;
        }
        return z;
    }

    public static int fib4(int n) {
        int x = 0, y = 1, temp = 0;
        for (int i = 0; i < n; i++) {
            temp = (x + y) % 1000000007;
            x = y;
            y = temp;
        }
        return x;
    }

    public static void main(String[] args) {
//        System.out.println(fib(48));
//        System.out.println(fib2(48));
        System.out.println(fib3(48));
        System.out.println(fib4(48));
    }
}


