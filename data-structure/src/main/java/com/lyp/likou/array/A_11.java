package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-23 09:12
 *
 * 11. 盛最多水的容器
 * 给你 n 个非负整数 a1，a2，...，an，每个数代表坐标中的一个点 (i, ai) 。在坐标内画 n 条垂直线，垂直线 i 的两个端点分别为 (i, ai) 和 (i, 0) 。找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 *
 * 说明：你不能倾斜容器。
 *
 *
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
 * 示例 3：
 * 输入：height = [4,3,2,1,4]
 * 输出：16
 *
 * 示例 4：
 * 输入：height = [1,2,1]
 * 输出：2
 */
public class A_11 {

    /**
     * 双层for循环，超时
     * @param height
     * @return
     */
    public static int maxArea(int[] height) {
        int maxArea = 0;
        int tempArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                if (height[i] <= height[j]) {
                    tempArea = (j - i) * height[i];
                    maxArea = Math.max(maxArea, tempArea);
                } else {
                    tempArea = (j - i) * height[j];
                    maxArea = Math.max(maxArea, tempArea);
                }
            }
        }
        return maxArea;
    }

    public static int maxArea2(int[] height) {
        int maxArea = 0;
        int tempArea = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                tempArea = (j - i) * Math.min(height[i],height[j]);
                maxArea = Math.max(maxArea, tempArea);
            }
        }
        return maxArea;
    }

    /**
     * 双指针法
     * @param height
     * @return
     */
    public static int maxArea3(int[] height) {
        int maxArea = 0;
        int tempArea = 0;
        int left = 0;
        int right = height.length - 1;
        while (left < right){
            tempArea = (right - left) * Math.min(height[left],height[right]);
            maxArea = Math.max(maxArea, tempArea);
            //移动 短的那个
            if(height[left] < height[right]){
                left++;
            }else{
                right--;
            }
        }
        return maxArea;
    }

    public static void main(String[] args) {
        System.out.println(maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea(new int[]{1, 1}));
        System.out.println(maxArea(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxArea(new int[]{1, 2, 1}));
        System.out.println("----------------------");
        System.out.println(maxArea2(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea2(new int[]{1, 1}));
        System.out.println(maxArea2(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxArea2(new int[]{1, 2, 1}));
        System.out.println("----------------------");
        System.out.println(maxArea3(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(maxArea3(new int[]{1, 1}));
        System.out.println(maxArea3(new int[]{4, 3, 2, 1, 4}));
        System.out.println(maxArea3(new int[]{1, 2, 1}));
    }
}
