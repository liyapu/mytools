package com.lyp.likou.array;

import java.util.Arrays;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-28 09:19
 *
 * 34. 在排序数组中查找元素的第一个和最后一个位置
 * 给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 *
 * 进阶：
 *
 * 你可以设计并实现时间复杂度为 O(log n) 的算法解决此问题吗？
 *
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 *
 * 提示：
 *
 * 0 <= nums.length <= 105
 * -109 <= nums[i] <= 109
 * nums 是一个非递减数组
 * -109 <= target <= 109
 */
public class A_34 {

    public static int[] searchRange(int[] nums, int target) {
        int len;
        int [] notFindArr  = {-1,-1};
        if(nums == null || (len = nums.length) == 0 ) return notFindArr;

        int left = 0;
        int right = len - 1;
        int mid;

        while (left <= right){
            mid = left + (right - left)/2;
            if(target < nums[mid]){
                right = mid - 1;
            }else if(target > nums[mid]){
                left = mid + 1;
            }else{
                //当找到之后，向左移动
                int i = mid;
                while (i >= 0 && nums[i] == target){
                    i--;
                }

                //根据while 的终止条件判断，i 值修正
                if(i >= 0){
                    if(nums[i] != target){
                        i++;
                    }
                }else{
                    i++;
                }

                //找到之后，向 右 移动
                int j = mid;
                while (j < len && nums[j] == target){
                    j++;
                }
                if(j < len){
                    j--;
                }else{
                    j--;
                }
                return new int [] {i,j};
            }
        }

        return notFindArr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(searchRange(new int [] {5,7,7,8,8,10},8)));
        System.out.println(Arrays.toString(searchRange(new int [] {5,7,7,8,8,10},6)));
        System.out.println(Arrays.toString(searchRange(new int [] {5,5,5,7,7,8,8,10},5)));
        System.out.println(Arrays.toString(searchRange(new int [] {5,5,5,7,7,8,8,10,10,10},10)));
        System.out.println(Arrays.toString(searchRange(new int [] {5,5,5,5},5)));
        System.out.println(Arrays.toString(searchRange(new int [] {},8)));
    }

}
