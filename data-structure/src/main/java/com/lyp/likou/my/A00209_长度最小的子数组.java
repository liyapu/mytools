package com.lyp.likou.my;

/**
 * 209. 长度最小的子数组
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其总和大于等于 target 的长度最小的 子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。如果不存在符合条件的子数组，返回 0 。
 *
 *
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 *
 * 提示：
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 */
public class A00209_长度最小的子数组 {

    /**
     * 暴力破解
     * 执行会超时
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int len = nums.length;
        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < len; i++) {
            int temp = 0;
            for (int j = i; j < len; j++) {
                temp += nums[j];
                if (temp >= target) {
                    ans = Math.min(ans, j - i + 1);
                    break;
                }
            }
        }
        //最终如果都没有符合要求的，则 ans = Integer.MAX_VALUE 从来没有变过，处理一下
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

    /**
     * 方法三：滑动窗口
     * 定义两个指针 start 和 end 分别表示子数组（滑动窗口窗口）的开始位置和结束位置，维护变量 sum 存储子数组中的元素和（即从 nums[start] 到 nums[end] 的元素和）。
     *
     * 初始状态下，start 和 end 都指向下标 0，sum 的值为 0。
     *
     * 每一轮迭代，将 nums[end] 加到 sum，如果 sum≥s，则更新子数组的最小长度（此时子数组的长度是 end−start+1），
     * 然后将 nums[start] 从 sum 中减去并将 start 右移，直到 sum<s，在此过程中同样更新子数组的最小长度。在每一轮迭代的最后，将 end 右移。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/minimum-size-subarray-sum/solutions/305704/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public int minSubArrayLen2(int target, int[] nums) {
        int len = nums.length;
        int ans = Integer.MAX_VALUE;
        int start = 0, end = 0;
        int sum = 0;

        while (end < len) {
            sum += nums[end];
            while (sum >= target) {
                ans = Math.min(ans, end - start + 1);
                sum -= nums[start];
                start++;
            }
            end++;
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }

}
