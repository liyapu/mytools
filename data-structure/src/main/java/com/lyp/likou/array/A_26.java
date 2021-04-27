package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 13:59
 *
 * 26. 删除有序数组中的重复项
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 只出现一次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2]
 * 解释：函数应该返回新的长度 2 ，并且原数组 nums 的前两个元素被修改为 1, 2 。不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4]
 * 解释：函数应该返回新的长度 5 ， 并且原数组 nums 的前五个元素被修改为 0, 1, 2, 3, 4 。不需要考虑数组中超出新长度后面的元素
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class A_26 {

    public static int removeDuplicates(int[] nums) {
        int len;
        if(null == nums || (len = nums.length) == 0) return 0;
        int newLen = 0;
        for (int i = 1; i < len; i++) {
            if(nums[i] != nums[newLen]){
                nums[++newLen] = nums[i];
            }
        }
        newLen++;
        return newLen;
    }

    public static void main(String[] args) {
        int [] nums = {0,0,1,1,1,2,2,3,3,4};
        int newLen = removeDuplicates(nums);
        for (int i = 0; i < newLen; i++) {
            System.out.println(nums[i]);
        }
    }
}
