package com.lyp.likou.lk;

/**
 * 162. 寻找峰值
 * 峰值元素是指其值严格大于左右相邻值的元素。
 *
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 *
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 * 或者返回索引 5， 其峰值元素为 6。
 *
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -231 <= nums[i] <= 231 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class A00162_寻找峰值 {

    /**
     * 爬坡法
     * 俗话说「人往高处走，水往低处流」。如果我们从一个位置开始，不断地向高处走，那么最终一定可以到达一个峰值位置。
     *
     * 相当于二分查找左边界: 第一个大于等于目标值的元素(这里没有等于的情况, 目标值相当于是nums[mid+1])
     *
     * 标签：二分查找
     * 过程：
     * 首先要注意题目条件，在题目描述中出现了 nums[-1] = nums[n] = -∞，这就代表着 只要数组中存在一个元素比相邻元素大，那么沿着它一定可以找到一个峰值
     * 根据上述结论，我们就可以使用二分查找找到峰值
     * 查找时，左指针 l，右指针 r，以其保持左右顺序为循环条件
     * 根据左右指针计算中间位置 m，并比较 m 与 m+1 的值，如果 m 较大，则左侧存在峰值，r = m，如果 m + 1 较大，则右侧存在峰值，l = m + 1
     * 时间复杂度：O(logN)
     * 作者：画手大鹏
     * 链接：https://leetcode.cn/problems/find-peak-element/solutions/6695/hua-jie-suan-fa-162-xun-zhao-feng-zhi-by-guanpengc/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        //因为 nums[i] != nums[i + 1]，所以不可能相等，left < right ,不可以 =
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1]) {
                //1.中间元素大于右边元素，说明峰值在左边（包括mid）
                //a.说明此时mid为下坡路，那么有可能自己本身就是山峰，或者在下山的过程中，所以right=mid而不能等于mid-1
                right = mid;
            } else {
                //2. 中间元素小于右边元素，说明峰值在右边
                //b.反之说明此时mid为上坡路，既然是上坡，那么mid肯定不是山峰，所以left=mid+1（题目要求nums[i]!=nums[i+1]，所以不可能存在“平峰”的情况）
                left = mid + 1;
            }
        }
        return left;
    }

    public static void main(String[] args) {
        int[] nums =new int[] {1,2,3,1};
        System.out.println(findPeakElement(nums));
    }


}
