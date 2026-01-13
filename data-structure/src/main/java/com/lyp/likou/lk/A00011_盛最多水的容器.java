package com.lyp.likou.lk;

/**
 * 11. 盛最多水的容器
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 *
 * 说明：你不能倾斜容器。
 *
 * 示例 1：
 * 输入：[1,8,6,2,5,4,8,3,7]
 * 输出：49
 * 解释：图中垂直线代表输入数组 [1,8,6,2,5,4,8,3,7]。在此情况下，容器能够容纳水（表示为蓝色部分）的最大值为 49。
 *
 * 示例 2：
 * 输入：height = [1,1]
 * 输出：1
 *
 * 提示：
 * n == height.length
 * 2 <= n <= 105
 * 0 <= height[i] <= 104
 */
public class A00011_盛最多水的容器 {

    public static int maxArea(int[] height) {
        int heightLength = height.length, left = 0, right = heightLength - 1;
        int maxArea = 0;
        while (left < right) {
            //使用 长*宽 计算长方形面积，长为x轴下表差值，宽为height数组下表对应值并取最小
            int tempArea = (right - left) * Math.min(height[left], height[right]);
            maxArea = Math.max(maxArea, tempArea);
            //小值移动，大值不动
            //一句话概括：我们left++和right--都是为了尝试取到更多的水，如果短的板不动的话，取到的水永远不会比上次多。
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,8,6,2,5,4,8,3,7};
        int[] nums1 = new int[]{1,1};
        System.out.println(maxArea(nums));
        System.out.println(maxArea(nums1));

    }
}
