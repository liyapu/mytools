package com.lyp.leetcode.editor.cn;

//给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。 
//
// 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。 
//
// 返回容器可以储存的最大水量。 
//
// 说明：你不能倾斜容器。 
//
// 
//
// 示例 1： 
//
// 
//
// 
//输入：[1,8,6,2,5,4,8,3,7]
//输出：49 
//解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。 
//
// 示例 2： 
//
// 
//输入：height = [1,1]
//输出：1
// 
//
// 
//
// 提示： 
//
// 
// n == height.length 
// 2 <= n <= 10⁵ 
// 0 <= height[i] <= 10⁴ 
// 
//
// Related Topics 贪心 数组 双指针 👍 4513 👎 0


/**
 * 盛最多水的容器
 *
 * @date 2023-08-26 17:49:27
 */
public class P11_ContainerWithMostWater {
    public static void main(String[] args) {
        //测试代码
        Solution solution = new P11_ContainerWithMostWater().new Solution();
        int result1 = solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7});
        System.out.println("result1 = " + result1);

        int result2 = solution.maxArea(new int[]{1, 1});
        System.out.println("result2 = " + result2);


    }

    //力扣代码
//leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxArea(int[] height) {
            int length = height.length;
            int i = 0,j = length -1;
            int result = Integer.MIN_VALUE;
            while (i < j){
                result = Math.max(result, Math.min(height[i],height[j])*(j-i));
                if(height[i] < height[j]){
                    i++;
                }else{
                    j--;
                }
            }
            return result;
        }

        public int maxArea1(int[] height) {
            int result = 0;
            for (int i = 0; i < height.length; i++) {
                for (int j = i + 1; j < height.length; j++) {
                    int min = Math.min(height[i], height[j]);
                    int temp = min * (j - i);
                    result = Math.max(temp, result);
                }
            }
            return result;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}
