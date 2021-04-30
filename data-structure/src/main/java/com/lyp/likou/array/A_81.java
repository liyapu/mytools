package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-30 13:39
 *
 * 81. 搜索旋转排序数组 II
 * 已知存在一个按非降序排列的整数数组 nums ，数组中的值不必互不相同。
 *
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转 ，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,4,4,5,6,6,7] 在下标 5 处经旋转后可能变为 [4,5,6,6,7,0,1,2,4,4] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，请你编写一个函数来判断给定的目标值是否存在于数组中。如果 nums 中存在这个目标值 target ，则返回 true ，否则返回 false 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 0
 * 输出：true
 * 示例 2：
 *
 * 输入：nums = [2,5,6,0,0,1,2], target = 3
 * 输出：false
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 * -104 <= target <= 104
 */
public class A_81 {

    /**
     * 对于数组中有重复元素的情况，二分查找时可能会有 a[l]=a[mid]=a[r]，此时无法判断区间 [l,mid] 和区间 [mid+1,r] 哪个是有序的。
     * 例如 nums=[3,1,2,3,3,3,3]，target=2，首次二分时无法判断区间 [0,3] 和区间 [4,6] 哪个是有序的。
     * 对于这种情况，我们只能将当前二分区间的左边界加一，右边界减一，然后在新区间上继续二分查找。
     *
     * @param nums
     * @param target
     * @return
     */
    public static boolean search(int[] nums, int target) {
        int len;
        if (null == nums || (len = nums.length) == 0) {
            return false;
        }
        int left = 0;
        int right = len - 1;
        int mid;

        while (left <= right) {
            mid = left + (right - left) / 2;
            //找到则返回
            if (target == nums[mid]) {
                return true;
            } else if (nums[left] == nums[mid] && nums[mid] == nums[right]) {
                // 左右边界值和mid相等，则左边界加一，右边界减一
                left++;
                right--;
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
        return false;
    }

    public static void main(String[] args) {
        System.out.println(search(new int[]{4, 6, 2, 3, 1}, 4));
        System.out.println(search(new int[]{4, 6, 2, 3, 1}, 6));
        System.out.println(search(new int[]{4, 6, 2, 3, 1}, 2));
        System.out.println(search(new int[]{4, 6, 2, 3, 1}, 3));
        System.out.println(search(new int[]{4, 6, 2, 3, 1}, 1));
    }
}
