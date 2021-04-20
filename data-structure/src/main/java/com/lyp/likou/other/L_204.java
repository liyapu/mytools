package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 16:00
 * 204. 计数质数
 * 统计所有小于非负整数 n 的质数的数量。
 *
 *
 *
 * 示例 1：
 * 输入：n = 10
 * 输出：4
 * 解释：小于 10 的质数一共有 4 个, 它们是 2, 3, 5, 7
 *
 * 示例 2：
 * 输入：n = 0
 * 输出：0
 *
 * 示例 3：
 * 输入：n = 1
 * 输出：0
 *
 *
 * 提示：
 *
 * 0 <= n <= 5 * 106
 * 通过次数141,718提交次数367,655
 */
public class L_204 {
    public static int countPrimes(int n) {
        if (n == 0 || n == 1) return 0;
        int total = 0;

        for (int i = 2; i < n; i++) {
            if (isPrime(i)) {
                total++;
            }
        }
        return total;
    }

    private static boolean isPrime(int n) {
        boolean flag = true;
        int limit = (int) Math.sqrt(n);
        for (int i = 2; i <= limit; i++) {
            if (n % i == 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(countPrimes(10));
    }
}
