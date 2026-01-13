package com.lyp.likou.my;

/**
 * 33. 搜索旋转排序数组
 * 整数数组 nums 按升序排列，数组中的值 互不相同 。
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 向左旋转，
 * 使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。
 * 例如， [0,1,2,4,5,6,7] 下标 3 上向左旋转后可能变为 [4,5,6,7,0,1,2] 。
 *
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
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
 *
 * 1 <= nums.length <= 5000
 * -104 <= nums[i] <= 104
 * nums 中的每个值都 独一无二
 * 题目数据保证 nums 在预先未知的某个下标上进行了旋转
 */
public class A00033_搜索旋转排序数组 {

    public static int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right) {
            mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[left] <= nums[mid]) { //处理 left = mid 进而指向同一个数的情况，是一种特殊情况，属于左侧有序，需要进来，对应 int[]{3,1} target = 1 的解决case， 不加 = 就算法就不对了。
                //下面均可对mid 进行 +1，-1 操作，因为上面已经判断 nums[mid] 不会等于 target了
                //左侧部分有序
                if (nums[left] <= target && target <= nums[mid]) {
                    //包含目标值
                    right = mid - 1;
                } else {
                    //不包含目标值
                    left = mid + 1;
                }
            } else {
                //右侧部分有序
                if (nums[mid] <= target && target <= nums[right]) {
                    //包含目标值
                    left = mid + 1;
                } else {
                    //不包含目标值
                    right = mid - 1;
                }
            }
        }
        return -1;
    }


    public static void main(String[] args) {
//        int[] nums = new int[]{4, 5, 6, 7, 0, 1, 2};
//        int target = 0;

        int[] nums = new int[]{3, 1};
        int target = 1;
        System.out.println(search(nums, target));
    }

}