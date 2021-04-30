package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-30 09:20
 *
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2]
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [4,5,6,7,0,1,2], target = 0
 * 输出：4
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2], target = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：nums = [1], target = 0
 * 输出：-1
 *
 *
 * 提示：
 * 1 <= nums.length <= 5000
 * -10^4 <= nums[i] <= 10^4
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -10^4 <= target <= 10^4
 */
public class A_33 {

    /**
     * 将数组一分为二，其中一定有一个是有序的，另一个可能是有序，也能是部分有序。
     * 此时有序部分用二分法查找。无序部分再一分为二，其中一个一定有序，另一个可能有序，可能无序。就这样循环.
     *
     * 这启示我们可以在常规二分查找的时候查看当前 mid 为分割位置分割出来的两个部分 [left, mid] 和 [mid + 1, right] 哪个部分是有序的，
     * 并根据有序的那个部分确定我们该如何改变二分查找的上下界，因为我们能够根据有序的那部分判断出 target 在不在这个部分
     *
     * @param nums
     * @param target
     * @return
     */
    public static int search(int[] nums, int target) {
        int len;
        if (null == nums || (len = nums.length) == 0) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            //找到则返回
            if (target == nums[mid]) {
                return mid;
            } else if (nums[left] <= nums[mid]) {
                //左半部分 有序
                if (target >= nums[left] && target <= nums[mid]) {
                    //在左边 可能有目标值
                    right = mid;
                } else {
                    //在右边 可能有目标值
                    left = mid + 1;
                }
            } else {
                //右半部分 有序
                if (target >= nums[mid] && target <= nums[right]) {
                    left = mid;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 0));
        System.out.println(search(new int[]{4, 5, 6, 7, 0, 1, 2}, 3));
        System.out.println(search(new int[]{1}, 0));
    }
}
