package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-30 16:34
 *
 * 287. 寻找重复数
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。
 *
 * 假设 nums 只有 一个重复的整数 ，找出 这个重复的数 。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *
 * 示例 3：
 * 输入：nums = [1,1]
 * 输出：1
 *
 * 示例 4：
 * 输入：nums = [1,1,2]
 * 输出：1
 *
 *
 * 提示：
 *
 * 2 <= n <= 3 * 104
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *
 */
public class A_287 {

    /**
     * 快慢指针思想
     * https://leetcode-cn.com/problems/find-the-duplicate-number/solution/287xun-zhao-zhong-fu-shu-by-kirsche/
     * @param nums
     * @return
     */
    public static int findDuplicate(int[] nums) {
        int slow = 0;
        int fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        int third = 0;
        while (third != slow) {
            third = nums[third];
            slow = nums[slow];
        }
        return third;
    }


    /**
     * 原地哈希
     * 这一题有个很有意思的限制：n + 1 个整数的数组 nums ，其数字都在 1 到 n 之间，因此可以把数组原地当做哈希表来做
     *
     * 思路很简单：依次遍历数组，把数组中的每个数当做下标，把对应那个数置成负数，如果这个数已经是负数了，那么这个数就是解
     * @param nums
     * @return
     */
    public static int findDuplicate2(int[] nums) {
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            //nums[i] 做下标时，可能已经被置成负数了，这里要取 正数
            int index = Math.abs(nums[i]);
            //nums[index] 第二次被置成负数的时候，就是找到答案的时候
            if(nums[index] < 0){
                return index;
            }else {
                nums[index] = -nums[index];
            }
        }
        return -1;
    }

    public static void main(String[] args) {
//        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate2(new int[]{1, 3, 4, 2, 2}));
    }
}
