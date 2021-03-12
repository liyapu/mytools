package com.lyp.likou.dynamic;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-12 15:07
 *
 * https://leetcode-cn.com/problems/climbing-stairs/
 * 70. 爬楼梯
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 *
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 注意：给定 n 是一个正整数。
 *
 * 示例 1：
 *
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 *
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 *
 */
public class D_70 {

    /**
     * 我们用 f(x) 表示爬到第 x 级台阶的方案数，考虑最后一步可能跨了一级台阶，也可能跨了两级台阶，所以我们可以列出如下式子：
     *      f(x)=f(x−1)+f(x−2)
     *
     * 它意味着爬到第 x 级台阶的方案数是爬到第 x−1 级台阶的方案数和爬到第 x−2 级台阶的方案数的和。
     *  很好理解，因为每次只能爬 1 级或 2 级，所以 f(x) 只能从 f(x−1) 和 f(x−2) 转移过来，而这里要统计方案总数，我们就需要对这两项的贡献求和。
     *
     *  以上是动态规划的转移方程，下面我们来讨论边界条件。
     *     我们是从第 0 级开始爬的，所以从第 0 级爬到第 0 级我们可以看作只有一种方案，即 f(0)=1；
     *          从第 0 级到第 1 级也只有一种方案，即爬一级，f(1)=1。
     *          这两个作为边界条件就可以继续向后推导出第 n 级的正确结果。
     *          我们不妨写几项来验证一下，根据转移方程得到
     *          f(2)=2，
     *          f(3)=3，
     *          f(4)=5，
     *          ……，
     *          我们把这些情况都枚举出来，发现计算的结果是正确的。
     *
     *  我们不难通过转移方程和边界条件给出一个时间复杂度和空间复杂度都是 O(n) 的实现，
     *  但是由于这里的 f(x) 只和 f(x−1) 与 f(x−2) 有关，所以我们可以用「滚动数组思想」把空间复杂度优化成 O(1)
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     *
     *  递归实现，存在大量的重复计算，此种提交时，超时了
     */
    public static int climbStairs(int n) {
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }
        return climbStairs(n - 1) + climbStairs(n - 2);
    }

    /**
     * 添加缓存，重复计算的部分，使用缓存结果
     * @param n
     * @return
     */
    public static int climbStairs_B(int n) {
        Map<Integer, Integer> cache = new HashMap<>();
        cache.put(1, 1);
        cache.put(2, 2);
        return climbStairsCache(n, cache);
    }

    public static int climbStairsCache(int n, Map<Integer, Integer> cache) {
        if (cache.containsKey(n)) {
            return cache.get(n);
        }
        int times = climbStairsCache(n - 1, cache) + climbStairsCache(n - 2, cache);
        cache.put(n, times);
        return times;
    }

    /**
     * 滚动数组思想
     *
     *  f(0) = 1
     *  f(1) = 1
     *  f(2) = 2
     *  f(3) = 3
     *  f(4) = 5
     *  f(5) = 8
     *  ......
     */
    public static int climbStairs_C(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param args
     */

    public static void main(String[] args) {
        int n1 = 1;
        int n2 = 2;
        int n3 = 3;
        int n4 = 4;
        int n5 = 5;

        System.out.println(climbStairs(n1));
        System.out.println(climbStairs_B(n1));
        System.out.println(climbStairs_C(n1));

        System.out.println(climbStairs(n2));
        System.out.println(climbStairs_B(n2));
        System.out.println(climbStairs_C(n2));

        System.out.println(climbStairs(n3));
        System.out.println(climbStairs_B(n3));
        System.out.println(climbStairs_C(n3));

        System.out.println(climbStairs(n4));
        System.out.println(climbStairs_B(n4));
        System.out.println(climbStairs_C(n4));

        System.out.println(climbStairs(n5));
        System.out.println(climbStairs_B(n5));
        System.out.println(climbStairs_C(n5));

    }
}
