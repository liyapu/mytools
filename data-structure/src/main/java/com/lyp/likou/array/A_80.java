package com.lyp.likou.array;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 15:11
 *
 * 80. 删除有序数组中的重复项 II
 * 给你一个有序数组 nums ，请你 原地 删除重复出现的元素，使每个元素 最多出现两次 ，返回删除后数组的新长度。
 *
 * 不要使用额外的数组空间，你必须在 原地 修改输入数组 并在使用 O(1) 额外空间的条件下完成。
 *
 *
 *
 * 说明：
 *
 * 为什么返回数值是整数，但输出的答案是数组呢？
 *
 * 请注意，输入数组是以「引用」方式传递的，这意味着在函数里修改输入数组对于调用者是可见的。
 *
 * 你可以想象内部操作如下:
 *
 * // nums 是以“引用”方式传递的。也就是说，不对实参做任何拷贝
 * int len = removeDuplicates(nums);
 *
 * // 在函数里修改输入数组对于调用者是可见的。
 * // 根据你的函数返回的长度, 它会打印出数组中 该长度范围内 的所有元素。
 * for (int i = 0; i < len; i++) {
 *     print(nums[i]);
 * }
 *
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,2,2,3]
 * 输出：5, nums = [1,1,2,2,3]
 * 解释：函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。 不需要考虑数组中超出新长度后面的元素。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,1,1,2,3,3]
 * 输出：7, nums = [0,0,1,1,2,3,3]
 * 解释：函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。 不需要考虑数组中超出新长度后面的元素。
 *
 *
 * 提示：
 *
 * 1 <= nums.length <= 3 * 104
 * -104 <= nums[i] <= 104
 * nums 已按升序排列
 */
public class A_80 {

    public int removeDuplicates3(int[] nums) {
        if (Objects.isNull(nums)) {
            return 0;
        }
        int length = nums.length;
        if (length == 0 || length == 1 || length == 2) {
            return length;
        }
        int newIndex = 0;
        boolean onece = false;
        for (int i = 1; i < length; i++) {
            if (nums[newIndex] == nums[i]) {
                if (!onece) {
                    onece = true;
                    nums[++newIndex] = nums[i];
                }
            } else {
                nums[++newIndex] = nums[i];
                onece = false;
            }

        }
        return newIndex + 1;
    }

    public static int removeDuplicates(int[] nums) {
        int newLen = 0;
        if (nums == null || nums.length == 0) return 0;

        Set<Integer> seen = new HashSet<>();

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[newLen]) {
                if (!seen.contains(nums[i])) {
                    seen.add(nums[i]);
                    nums[++newLen] = nums[i];
                }
            } else {
                nums[++newLen] = nums[i];
            }
        }
        ++newLen;
        return newLen;
    }

    /**
     * 双指针
     *
     * 因为本题要求相同元素最多出现两次而非一次，所以我们需要检查上上个应该被保留的元素 nums[slow−2] 是否和当前待检查元素 nums[fast] 相同。
     * 当且仅当 nums[slow−2]=nums[fast] 时，当前待检查元素 nums[fast] 不应该被保留（因为此时必然有 nums[slow−2]=nums[slow−1]=nums[fast]）。
     * 最后，slow 即为处理好的数组的长度
     *
     * @param nums
     * @return
     */
    public static int removeDuplicates2(int[] nums) {
        int len;
        if(nums == null || (len = nums.length ) == 0) return 0;
        int slow =2,fast = 2;

        //特别地，数组的前两个数必然可以被保留，因此对于长度不超过 2 的数组，我们无需进行任何处理，
        // 对于长度超过 2 的数组，我们直接将双指针的初始值设为 2 即可。
        if(len <= 2) return slow;

        while (fast < len){
            if(nums[slow-2] == nums[fast]){
                fast++;
            }else{
                nums[slow] = nums[fast];
                slow++;
                fast++;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        int [] arr = {0,0,1,1,1,1,2,3,3};
        int newLen = removeDuplicates2(arr);
        for (int i = 0; i < newLen; i++) {
            System.out.println(arr[i]);
        }
    }
}
