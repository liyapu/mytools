package com.lyp.leetcode.editor.cn;

//给你一个长度为 n 的整数数组 nums 和 一个目标值 target。请你从 nums 中选出三个整数，使它们的和与 target 最接近。
//
// 返回这三个数的和。 
//
// 假定每组输入只存在恰好一个解。 
//
// 
//
// 示例 1： 
//
// 
//输入：nums = [-1,2,1,-4], target = 1
//输出：2
//解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2) 。
// 
//
// 示例 2： 
//
// 
//输入：nums = [0,0,0], target = 1
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 3 <= nums.length <= 1000 
// -1000 <= nums[i] <= 1000 
// -10⁴ <= target <= 10⁴ 
// 
//
// Related Topics 数组 双指针 排序 👍 1404 👎 0

import java.util.Arrays;

/**
 * 最接近的三数之和
 *
 * @date 2023-06-11 17:59:11
 */
public class P16_ThreeSumClosest {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P16_ThreeSumClosest().new Solution();
        System.out.println(solution.threeSumClosest(new int[]{-1, 2, 1, -4}, 1));
        System.out.println(solution.threeSumClosest(new int[]{0, 0, 0}, 1));
        System.out.println(solution.threeSumClosest(new int[]{0, 1, 2}, 3));
    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int threeSumClosest(int[] nums, int target) {
            Arrays.sort(nums);
            int answer = nums[0] + nums[1] + nums[2];
            for (int i = 0; i < nums.length - 2; i++) {
                int left = i + 1;
                int right = nums.length - 1;
                while (left < right) {
                    int tempAnswer = nums[i] + nums[left] + nums[right];
                    if (Math.abs(tempAnswer - target) < Math.abs(answer - target)) {
                        answer = tempAnswer;
                    }
                    if (tempAnswer > target) {
                        right--;
                    } else if (tempAnswer < target) {
                        left++;
                    } else {
                        return tempAnswer;
                    }
                }
            }
            return answer;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
