package com.lyp.likou.array;

import java.util.Arrays;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-19 16:28
 *
 * 167. 两数之和 II - 输入有序数组
 * 给定一个已按照 升序排列  的整数数组 numbers ，请你从数组中找出两个数满足相加之和等于目标数 target 。
 *
 * 函数应该以长度为 2 的整数数组的形式返回这两个数的下标值。numbers 的下标 从 1 开始计数 ，所以答案数组应当满足 1 <= answer[0] < answer[1] <= numbers.length 。
 *
 * 你可以假设每个输入只对应唯一的答案，而且你不可以重复使用相同的元素。
 *
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。
 *
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 *
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 *
 *
 * 提示：
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 递增顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class A_167 {

    /**
     * 暴力破解
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum(int[] numbers, int target) {
        int[] resultArr = new int[2];
        int len;
        if (null == numbers || (len = numbers.length) == 0) {
            return resultArr;
        }

        // 减 1， 让下面的 加1 可以到达末尾
        for (int i = 0; i < len - 1; i++) {
            int other = target - numbers[i];

            // 从 i + 1 开始，因为不可重复使用数字
            for (int j = i + 1; j < len; j++) {
                if (numbers[j] == other) {
                    resultArr[0] = i + 1;
                    resultArr[1] = j + 1;
                    return resultArr;
                } else if (numbers[j] > other) {
                    break;
                }
            }
        }
        return resultArr;
    }

    /**
     * 数组有序
     *    二分查找  另一个数字
     * @param numbers
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] numbers, int target) {
        int[] resultArr = new int[2];
        int len;
        if (null == numbers || (len = numbers.length) == 0) {
            return resultArr;
        }

        for (int i = 0; i < len; i++) {
            // 这里 low 等于 i + 1 ，每次 low 都有变 ，加 1 是为了不使用重复的数字，比如 1 2 2 3
            int low = i + 1;
            int high = len - 1;
            int key = target - numbers[i];

            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (key > numbers[mid]) {
                    low = mid + 1;
                } else if (key < numbers[mid]) {
                    high = mid - 1;
                } else {
                    resultArr[0] = i + 1;
                    resultArr[1] = mid + 1;
                    return resultArr;
                }
            }
        }
        return resultArr;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[]{2, 7, 11, 15}, 9)));
        System.out.println(Arrays.toString(twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println(Arrays.toString(twoSum(new int[]{-1, 0}, -1)));
    }


}
