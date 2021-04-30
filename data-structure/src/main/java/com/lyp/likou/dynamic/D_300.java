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

    /**
     * 官方题解
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-by-leetcode-soluti/
     *
     * @param nums
     * @return
     */
    public static int lengthOfLIS_A(int[] nums) {
        int resultLen = 1, numsLen = nums.length;
        if (numsLen == 0) {
            return 0;
        }
        int[] d = new int[numsLen + 1];
        d[resultLen] = nums[0];
        for (int i = 1; i < numsLen; ++i) {
            if (nums[i] > d[resultLen]) {
                d[++resultLen] = nums[i];
            } else {
                // pos 表示在长度为 resultLen的数组d中，比 nums[i]最末次小的下标值
                int l = 1, r = resultLen, pos = 0; // 如果找不到说明所有的数都比 nums[i] 大，此时要更新 d[1]，所以这里将 pos 设为 0
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }

        System.out.println(Arrays.toString(d));
        return resultLen;
    }

    /**
     * 动态规划+二分查找  O(nlg(n))
     *
     * 考虑一个简单的贪心，如果我们要使上升子序列尽可能的长，则我们需要让序列上升得尽可能慢，因此我们希望每次在上升子序列最后加上的那个数尽可能的小。
     *
     * 很具小巧思。新建数组 cell，用于保存最长上升子序列。
     *
     * 对原序列进行遍历，将每位元素二分插入 cell 中。
     *
     * 如果 cell 中元素都比它小，将它插到最后
     * 否则，用它覆盖掉比它大的元素中最小的那个。
     * 总之，思想就是让 cell 中存储比较小的元素。
     * cell 中是真实的最长上升子序列之一（最长的子序列可能不唯一），长度也是最长的。
     *
     * https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/zui-chang-shang-sheng-zi-xu-lie-dong-tai-gui-hua-e/
     */
    public static int lengthOfLIS_B(int[] nums) {
        if (Objects.isNull(nums)) {
            return 0;
        }
        int resultLen = 1, numsLen = nums.length;
        if (numsLen <= 1) {
            return numsLen;
        }

        int[] d = new int[numsLen + 1];
        d[1] = nums[0];
        for (int i = 1; i < numsLen; i++) {
            if (nums[i] > d[resultLen]) {
                d[++resultLen] = nums[i];
            } else {
                int left = 1, right = resultLen, pos = 0;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (d[mid] < nums[i]) {
                        pos = mid;
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[pos + 1] = nums[i];
            }
        }

        System.out.println(Arrays.toString(d));
        return resultLen;
    }

    public static int lengthOfLIS_C(int[] nums) {
        if (Objects.isNull(nums)) {
            return 0;
        }
        int numsLen = nums.length;
        if (numsLen <= 1) {
            return numsLen;
        }

        int[] d = new int[numsLen];
        d[0] = nums[0];
        int resultLen = 1;

        for (int i = 1; i < numsLen; i++) {
            if (nums[i] > d[resultLen - 1]) {
                d[resultLen++] = nums[i];
            } else {
                int left = 0, right = resultLen - 1;
                while (left <= right) {
                    int mid = (left + right) / 2;
                    if (d[mid] < nums[i]) {
                        left = mid + 1;
                    } else {
                        right = mid - 1;
                    }
                }
                d[left] = nums[i];
            }
        }
        System.out.println(Arrays.toString(d));
        return resultLen;
    }


        public static void main(String[] args) {
        // 2,3,7,101
        // 4
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));
        System.out.println();

        // 1，3，6，7，9，10
        // 6
        int[] arr1 = {1, 3, 6, 7, 9, 4, 10, 5, 6};
        System.out.println(lengthOfLIS(arr1));
        System.out.println();

        int[] arr2 = {3, 8, 4, 12, 5};

        System.out.println("--------lengthOfLIS_A----------");
        System.out.println(lengthOfLIS_A(nums));
        System.out.println(lengthOfLIS_A(arr1));
        System.out.println(lengthOfLIS_A(arr2));
        System.out.println();

        System.out.println("--------lengthOfLIS_B----------");
        System.out.println(lengthOfLIS_B(nums));
        System.out.println(lengthOfLIS_B(arr1));
        System.out.println(lengthOfLIS_B(arr2));
        System.out.println();

        System.out.println("--------lengthOfLIS_C----------");
        System.out.println(lengthOfLIS_C(nums));
        System.out.println(lengthOfLIS_C(arr1));
        System.out.println(lengthOfLIS_C(arr2));
    }
}
