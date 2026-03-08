package com.lyp.likou.interview_other;

/**
 * 面试字节前必看的高频题——木头切割问题
 * https://mp.weixin.qq.com/s/FQma0bdAWbzLMmCKhZRk7w
 *
 * 算法---木头切割问题(二分法)
 * https://blog.csdn.net/qq_35077107/article/details/118197848
 *
 * 题目描述
 * 给定长度为n的数组，每个元素代表一个木头的长度，木头可以任意截断，从这堆木头中截出至少k个相同长度为m的木块。已知k，求max(m)。
 *
 * 输入两行，第一行n, k，第二行为数组序列。输出最大值。
 *
 * 输入
 * 5 5
 * 4 7 2 10 5
 * 输出
 * 4
 * 解释：如图，最多可以把它分成5段长度为4的木头 ,出现了5次4
 * 4  4-3  2  4-4-2  4-1
 * ps:数据保证有解，即结果至少是1。
 */
public class Other00001_木头切割问题 {

    /**
     * 方法一：暴力。
     * 大概思路就是从1遍历到木棍最长的长度，每次遍历的长度作为m，
     * 如果可以将所有木头截出来k个长度为m的木块，则更新最大值，最后输出最大值即可。
     *
     * 以下的代码也比较容易理解，这里就不多展开说了。时间复杂度也很容易看出来是O(n * len), len为木头中最大的长度。
     * 容易想到遍历长度时可以从大到小遍历，if (cnt >= k)成立，则该值即为最终结果，可直接break，但最坏时间复杂度没变。
     */

    public static int findMaxLen(Integer len, Integer k, Integer[] arrs) {
        //先找到数组中的最大值
        int maxNum = 0;
        for (int i = 0; i < len; i++) {
            maxNum = Math.max(maxNum, arrs[i]);
        }
        int ans = 0;
        int curLen = 1;
        while (curLen <= maxNum) {
            int count = 0;
            //以 curLen 截取木头，查找能截取的最多段
            for (int i = 0; i < len; i++) {
                count += arrs[i] / curLen;
            }
            //如果能截取 k 段以上，则更新结果
            if (count >= k) {
                ans = curLen;
            }
            curLen++;
        }
        return ans;
    }

    /**
     * 方法二：二分法。
     * 方法一在[1,max]寻找最大长度时是顺序遍历，由于其有序，我们可借助二分来快速检出结果。
     * 如果能截出来k个长度为x的木块，说明答案肯定 >= x，则接下来只需在[x,max]中找m最大满足条件的长度。
     * 反之则说明答案 < x，则在[1,x-1]中寻找结果。
     * 这样我们每次可以舍弃1/2的情况，因此使用二分的时间复杂度是O(n * log Len)。
     *
     * @param len
     * @param k
     * @param arrs
     * @return
     */
    public static int findMaxLen2(Integer len, Integer k, Integer[] arrs) {
        //先找到数组中的最大值
        int maxNum = 0;
        for (int i = 0; i < len; i++) {
            maxNum = Math.max(maxNum, arrs[i]);
        }
        int ans = 0;
        int left = 1, right = maxNum;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            int count = 0;
            //以 mid 长度截取木头，查找能截取的最多段
            for (int i = 0; i < len; i++) {
                count += arrs[i] / mid;
            }
            if (count >= k) {
                ans = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        Integer[] arrs = {4, 7, 2, 10, 5};
        int len = arrs.length, k = 5;
        System.out.println(findMaxLen(len, k, arrs));
        System.out.println(findMaxLen2(len, k, arrs));

    }
}
