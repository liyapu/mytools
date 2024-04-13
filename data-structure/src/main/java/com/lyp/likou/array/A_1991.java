package com.lyp.likou.array;

/**
 * @author liyapu
 * @date 2024-03-18 22:14
 * @description 1991. 找到数组的中间位置
 * 简单
 * 相关标签
 * 相关企业
 * 提示
 * 给你一个下标从 0 开始的整数数组 nums ，请你找到 最左边 的中间位置 middleIndex （也就是所有可能中间位置下标最小的一个）。
 *
 * 中间位置 middleIndex 是满足 nums[0] + nums[1] + ... + nums[middleIndex-1] == nums[middleIndex+1] + nums[middleIndex+2] + ... + nums[nums.length-1] 的数组下标。
 *
 * 如果 middleIndex == 0 ，左边部分的和定义为 0 。类似的，如果 middleIndex == nums.length - 1 ，右边部分的和定义为 0 。
 *
 * 请你返回满足上述条件 最左边 的 middleIndex ，如果不存在这样的中间位置，请你返回 -1 。
 *
 * 示例 1：
 * 输入：nums = [2,3,-1,8,4]
 * 输出：3
 * 解释：
 * 下标 3 之前的数字和为：2 + 3 + -1 = 4
 * 下标 3 之后的数字和为：4 = 4
 *
 * 示例 2：
 * 输入：nums = [1,-1,4]
 * 输出：2
 * 解释：
 * 下标 2 之前的数字和为：1 + -1 = 0
 * 下标 2 之后的数字和为：0
 *
 * 示例 3：
 * 输入：nums = [2,5]
 * 输出：-1
 * 解释：
 * 不存在符合要求的 middleIndex 。
 *
 * 示例 4：
 * 输入：nums = [1]
 * 输出：0
 * 解释：
 * 下标 0 之前的数字和为：0
 * 下标 0 之后的数字和为：0
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 100
 * -1000 <= nums[i] <= 1000
 *
 *
 * 注意：本题与主站 724 题相同：https://leetcode-cn.com/problems/find-pivot-index/
 *
 * 示例 1：
 * 输入：nums = [1, 7, 3, 6, 5, 6]
 * 输出：3
 * 解释：
 * 中心下标是 3 。
 * 左侧数之和 sum = nums[0] + nums[1] + nums[2] = 1 + 7 + 3 = 11 ，
 * 右侧数之和 sum = nums[4] + nums[5] = 5 + 6 = 11 ，二者相等。
 *
 * 示例 2：
 * 输入：nums = [1, 2, 3]
 * 输出：-1
 * 解释：
 * 数组中不存在满足此条件的中心下标。
 *
 * 示例 3：
 * 输入：nums = [2, 1, -1]
 * 输出：0
 * 解释：
 * 中心下标是 0 。
 * 左侧数之和 sum = 0 ，（下标 0 左侧不存在元素），
 * 右侧数之和 sum = nums[1] + nums[2] = 1 + -1 = 0 。
 */
public class A_1991 {
    public static void main(String[] args) {
        A_1991 a = new A_1991();
        System.out.println(a.findMiddleIndex(new int[]{2, 3, -1, 8, 4}));
        System.out.println(a.findMiddleIndex(new int[]{1, -1, 4}));
        System.out.println(a.findMiddleIndex(new int[]{2, 5}));
        System.out.println(a.findMiddleIndex(new int[]{1}));
        System.out.println(a.findMiddleIndex(new int[]{1, 7, 3, 6, 5, 6}));
        System.out.println(a.findMiddleIndex(new int[]{1, 2, 3}));
        System.out.println(a.findMiddleIndex(new int[]{2, 1, -1}));
    }

//    public int findMiddleIndex(int[] nums) {
//        int length = nums.length;
//        //for i 每次循环，都假设中间位置是 i
//        // i <= j 表示中间位置 可以是最后一位的索引
//        for (int i = 0, j = length - 1; i <= j; i++) {
//            int left = 0;
//            //右边的第一位是i后边的第一个索引
//            int right = i + 1;
//
//            int leftSum = 0;
//            int rightSum = 0;
//
//            while (left < i) {
//                //i左边之和
//                leftSum += nums[left];
//                left++;
//            }
//            while (right <= j) {
//                //i右边之和
//                rightSum += nums[right];
//                right++;
//            }
//            //判断是否相等
//            if (leftSum == rightSum) {
//                return i;
//            }
//        }
//        //找不到，默认值返回-1
//        return -1;
//    }

    /**
     * 假设数组之和为total,
     * 左边之和 = sum
     * 右边之和 = total - sum - 数组中当前下标对应的值
     * 如果左边之和等于右边之和，则当前下标为中间位置
     * 则 ：sum  = total - sum - nums[i]
     * 则 2*sum + nums[i] = total
     *
     * @param nums
     * @return
     */
//    public int findMiddleIndex(int[] nums) {
//        int length = nums.length;
//        int total = Arrays.stream(nums).sum();
//
//        int sum = 0;
//        for (int i = 0; i < length; i++) {
//            if (2 * sum + nums[i] == total) {
//                return i;
//            }
//            sum += nums[i];
//        }
//        //找不到，默认值返回-1
//        return -1;
//    }

    /**
     * 定义两个指针，分别表示左右之和
     *
     * @param nums
     * @return
     */
    public int findMiddleIndex(int[] nums) {
        int length = nums.length;

        int leftSum = 0;
        int rightSum = 0;

        //假设中间位置是下标为0
        for (int i = 1; i < length; i++) {
            rightSum += nums[i];
        }
        if (leftSum == rightSum) {
            return 0;
        }
        for (int i = 1; i < length; i++) {
            leftSum += nums[i - 1];
            rightSum -= nums[i];
            if (leftSum == rightSum) {
                return i;
            }
        }
        //找不到，默认值返回-1
        return -1;
    }
}
