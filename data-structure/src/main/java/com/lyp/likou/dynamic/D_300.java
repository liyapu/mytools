package com.lyp.likou.dynamic;

import java.util.Arrays;
import java.util.Objects;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-09 15:52
 *
 *  https://leetcode-cn.com/problems/longest-increasing-subsequence/
 *  300. 最长递增子序列
 *
 *
 * https://leetcode-cn.com/leetbook/read/dynamic-programming-1-plus/xcos8s/
 * 考虑能否将问题规模减小
 * 将问题规模减小的方式有很多种，一些典型的减小方式是动态规划分类的依据，例如线性，区间，树形等。这里考虑数组上常用的两种思路：
 *
 * 每次减少一半：如果每次将问题规模减少一半，原问题有[10,9,2,5]，和[3,7,101,18]，
 * 两个子问题的最优解分别为 [2,5] 和 [3,7,101]，但是找不到好的组合方式将两个子问题最优解组合为原问题最优解 [2,5,7,101]。
 *
 * 每次减少一个：记 f(n) 为以第 n 个数结尾的最长子序列，每次减少一个，将原问题分为 f(n-1), f(n-2), ..., f(1)，共 n - 1 个子问题。n - 1=7 个子问题以及答案如下：
 *
 * [10, 9, 2, 5, 3, 7, 101] -> [2, 5, 7, 101]
 * [10, 9, 2, 5, 3, 7] -> [2, 5, 7]
 * [10, 9, 2, 5, 3] -> [2, 3]
 * [10, 9, 2, 5] -> [2, 5]
 * [10, 9, 2] -> [2]
 * [10, 9] -> [9]
 * [10] -> [10]
 *
 * 已经有 7 个子问题的最优解之后，可以发现一种组合方式得到原问题的最优解：f(6)的结果 [2,5,7], 7 < 18，
 * 同时长度也是 f(1)~f(7) 中，结尾小于 18 的结果中最长的。f(7) 虽然长度为 4 比 f(6) 长，但结尾是不小于 18 的，无法组合成原问题的解。
 *
 * 以上组合方式可以写成一个式子，即状态转移方程
 *
 * f(n) = max f(i) + 1  其中 i < n 且 a[i] < a[n]
 * f(n)=maxf(i)+1 其中 i<n 且 a[i]<a[n]
 *
 * 这种思考如何通过 f(1)...f(n-1) 求出 f(n) 的过程实际就是在思考状态转移方程怎么写。
 *
 * 总结： 解决动态规划问题最难的地方有两点：
 *
 * 如何定义 f(n)
 * 如何通过 f(1), f(2), …f(n−1) 推导出 f(n)，即状态转移方程
 *
 *
 * 递归的解法需要非常多的重复计算，如果有一种办法能避免这些重复计算，可以节省大量计算时间。
 * 记忆化就是基于这个思路的算法。在递归地求解子问题 f(1), f(2)... 过程中，将结果保存到一个表里，
 * 在后续求解子问题中如果遇到求过结果的子问题，直接查表去得到答案而不计算
 *
 */
public class D_300 {

    /**
     * 时间复杂度：O(N^2)，这里 N 是输入数组的长度； 空间复杂度：O(N)
     *
     * 补充说明：这道题还有一个经典的，时间复杂度为 O(N \log N) 的解法，它也是动态规划的解法，可以在题解区找到这个方法的解释和代码。
     * @param nums
     * @return
     */
    public static int lengthOfLIS(int[] nums) {
        if (Objects.isNull(nums)) {
            return 0;
        }
        int len = nums.length;
        if (len <= 1) {
            return len;
        }

        //dp[i] 表示以 nums[i] 结尾的最长上升子序列的长度
        int[] dp = new int[len];
        //初始化。单独一个数是子序列，初始化的值为 1
        Arrays.fill(dp, 1);
        //System.out.println(Arrays.toString(dp));

        //初始化默认结果
        int result = dp[0];
        //寻找第i位置
        for (int i = 1; i < len; i++) {
            //让 i位置和它之前所有的数比较，小于时才有可能影响最优解，动态修改 dp
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    //新结果和原来的结果还需做比较
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            // 在遍历的时候同时找 dp 数组的最大值
            // 让记录的最优解和刚刚产生的最优解，做比较
            result = Math.max(result, dp[i]);
        }
        return result;
    }


    public static void main(String[] args) {
        // 2,3,7,101
        // 4
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));

        // 1，3，6，7，9，10
        // 6
        int[] arr1 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(arr1));
    }
}
