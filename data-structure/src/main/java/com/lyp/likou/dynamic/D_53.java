package com.lyp.likou.dynamic;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-11 14:36
 *
 *  53. 最大子序和
 *  https://leetcode-cn.com/problems/maximum-subarray/
 *  给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 *
 * 示例 1：
 *
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：1
 * 示例 3：
 *
 * 输入：nums = [0]
 * 输出：0
 * 示例 4：
 *
 * 输入：nums = [-1]
 * 输出：-1
 * 示例 5：
 *
 * 输入：nums = [-100000]
 * 输出：-100000
 *  
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -105 <= nums[i] <= 105
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/maximum-subarray
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 * 方法一：动态规划
 * 思路和算法
 * 假设 nums 数组的长度是 nn，下标从 0 到 n−1。
 *
 * 我们用 f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
 *
 * 因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。那么我们如何求 f(i) 呢？
 * 我们可以考虑 nums[i] 单独成为一段还是加入 f(i−1) 对应的那一段，这取决于 nums[i] 和 f(i−1)+nums[i] 的大小，
 * 我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
 * f(i)=max{ f(i−1)+nums[i],  nums[i] }
 *
 * 不难给出一个时间复杂度 O(n)、空间复杂度 O(n) 的实现，即用一个 f 数组来保存 f(i) 的值，
 * 用一个循环求出所有 f(i)。考虑到 f(i) 只和 f(i−1) 相关，于是我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i−1) 的值是多少，从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想。
 *
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 *
 *
 */
public class D_53 {

    public static int maxSubArray(int[] nums) {
        int pre = 0, maxSum = nums[0];
        for (int num : nums) {
            //如果和前边累加后还不如自己本身大，那就把前边的都扔掉，从此自己本身重新开始累加。
            pre = Math.max(num + pre, num);
            maxSum = Math.max(pre, maxSum);
        }
        return maxSum;
    }

    public static int maxSubArray_A(int[] nums) {
        int len = nums.length;
        if (len == 1) {
            return nums[0];
        }

        //定义dp数组，dp数组中的每个值dp[i]代表着以nums[i]为结尾的最大子序和
        int[] dp = new int[len];
        //以nums[0]结尾的最大子序和就是nums[0]
        dp[0] = nums[0];

        //遍历，通过状态转移方程求得dp[i]的最大子序和
        for (int i = 1; i < len; i++) {

            //dp[i]的最大子序和要么是自成一派最大，要么就是当前值加上前面  i-1 个数的最大子序和
            dp[i] = Math.max(dp[i-1] + nums[i], nums[i]);
        }

        int maxSum = dp[0];
        //遍历dp数组，求得dp数组中的最大值，就是该题的答案
        for (int i = 1; i < len; i++) {
            maxSum = Math.max(maxSum,dp[i]);
        }
        return maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));
        System.out.println(maxSubArray_A(nums));
    }
}
