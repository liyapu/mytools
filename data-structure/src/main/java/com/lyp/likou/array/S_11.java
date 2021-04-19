package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-19 09:24
 *
 * 剑指 Offer 11. 旋转数组的最小数字
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。输入一个递增排序的数组的一个旋转，输出旋转数组的最小元素。例如，数组 [3,4,5,1,2] 为 [1,2,3,4,5] 的一个旋转，该数组的最小值为1。
 *
 * 示例 1：
 *
 * 输入：[3,4,5,1,2]
 * 输出：1
 * 示例 2：
 *
 * 输入：[2,2,2,0,1]
 * 输出：0
 * 注意：本题与主站 154 题相同：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/
 */
public class S_11 {
    public static int minArray(int[] numbers) {
        if(null == numbers) return -1;
        int min = Integer.MAX_VALUE;
        for (int i = numbers.length - 1; i >= 0; i--) {
            if(numbers[i] <= min){
                min = numbers[i];
            }else{
                break;
            }
        }
        return min;
    }

    public static void main(String[] args) {
        int [] arr = {3,4,5,1,2};
        System.out.println(minArray(arr));
    }
}
