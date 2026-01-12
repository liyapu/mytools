package com.lyp.likou.my;

import java.util.Arrays;

/**
 * 713. 乘积小于 K 的子数组
 * 给你一个整数数组 nums 和一个整数 k ，请你返回子数组内所有元素的乘积严格小于 k 的连续子数组的数目。
 *
 *
 * 示例 1：
 * 输入：nums = [10,5,2,6], k = 100
 * 输出：8
 * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
 * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 0
 * 输出：0
 *
 *
 * 提示:
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 */
public class A00713_乘积小于K的子数组 {

    /**
     * 小技巧：特判 k=0 和 k=1，这样 while 循环中就无需判断 i <= j 了。
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        // 注意这里特判了 k=0 和 k=1 的情况
        //同样排除k为1的情况比如  [1,1,1] k=1
        if (k <= 1) {
            return 0;
        }
        int len = nums.length;
        int start = 0, end = 0;
        int ans = 0;
        int multi = 1;
        while (end < len) {
            multi *= nums[end];
            while (multi >= k) { // 不满足要求, 除掉最左侧值，直到满足为止
                multi /= nums[start];
                start++;
            }
            ans += end - start + 1;
            end++;
        }
        return ans;
    }

    /**
     * 本题滑动窗口题解：
     * 分析
     * 首先定义两个指针 left 和 right，后续遍历数组与记录用，
     *
     * 左右指针起始均在位置 0 ；用右指针遍历数组，每次循环中右指针右移一次；
     *
     * 移动过程中纪录从左指针到右指针路上的连续数的乘积为 mul；
     *
     * 如果乘积大于 k 了，则左指针右移，注意此处用的是 while 来使左指针右移，因为实际情况可能是：右指针最后右移一次指向了一个比较大的数使得 mul 不小于目标值，此时左指针需要右移多次才能使得 mul 刚小于 k；
     *
     * 最后用 ans 记录 mul 小于 k 时的数组组合；
     *
     * 思路看不明白直接上代码，结合起来理解就好了：
     *
     * 作者：腌菜读作梦想
     * 链接：https://leetcode.cn/problems/subarray-product-less-than-k/solutions/1320871/jian-dan-yi-dong-xiang-xi-zhu-jie-shuang-jvy3/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @param k
     * @return
     *
     * 示例 1：
     * 输入：nums = [10,5,2,6], k = 100
     * 输出：8
     * 解释：8 个乘积小于 100 的子数组分别为：[10]、[5]、[2]、[6]、[10,5]、[5,2]、[2,6]、[5,2,6]。
     * 需要注意的是 [10,5,2] 并不是乘积小于 100 的子数组。
     *
     * i = 0, j = 0, prod = 10  结果 [10]                个数 1
     * i = 0, j = 1, prod = 50  结果 [5] [10,5]          个数 2
     * i = 0, j = 2, prod = 100 结果 无
     * i = 1, j = 2, prod = 10  结果 [2] [5,2]           个数 2
     * i = 1, j = 3, prod = 60  结果 [6] [2,6] [5,2,6]   个数 3   以右结点为尾节点，不停的向前找
     *                                                 总个数 8
     */

    /**
     * 视频讲解】越短越合法型滑动窗口（Python/Java/C++/C/Go/JS/Rust）
     * 灵茶山艾府
     * 本题视频讲解：请看 滑动窗口【基础算法精讲 03】第二题。
     *
     * 注意数据范围 nums[i]≥1，所以乘积不可能小于 1。因此，当 k≤1 时，没有这样的子数组，直接返回 0。
     * 由于子数组越长，乘积越大，越不能满足题目要求；反之，子数组越短，乘积越小，越能满足题目要求。有这种性质的题目，可以用滑动窗口解决。
     *
     * 枚举子数组右端点 right，如果发现子数组不满足要求，就缩小窗口，也就是增大左端点 left。
     *
     * 内层循环结束后，[left,right] 这个子数组是满足题目要求的。由于子数组越短，越能满足题目要求，
     * 所以除了 [left,right]，还有 [left+1,right],[left+2,right],…,[right,right] 都是满足要求的。
     * 也就是说，当右端点固定在 right 时，左端点在 left,left+1,left+2,…,right 的所有子数组都是满足要求的，这一共有 right−left+1 个，加到答案中。
     *
     * 答疑
     * 问：为什么这个算法可以不重不漏地统计所有子数组？
     * 答：如果用暴力做的话，我们可以枚举右端点，枚举左端点，判断子数组是否合法，合法就把答案加一。暴力做法必然不会漏算多算。
     * 滑动窗口是对暴力的优化，保留了「枚举右端点」的思路，把枚举左端点的过程优化成了均摊 O(1)。本质和暴力是一样的，自然不会漏算多算。
     *
     * 问：如果不特判 k≤1，代码要怎么改？
     * 答：如果 k≤1，那么代码中的 prod >= k 恒为真。可以额外判断 left≤right 避免下标越界，即内层循环条件改成 left <= right && prod >= k。
     * @param nums
     * @param k
     * @return
     */
    public int numSubarrayProductLessThanK2(int[] nums, int k) {
        int n = nums.length, ret = 0;
        int prod = 1, i = 0;
        for (int j = 0; j < n; j++) {
            prod *= nums[j];
            while (i <= j && prod >= k) {
                prod /= nums[i];
                i++;
            }
            ret += j - i + 1;
        }
        return ret;
    }



}
