package com.lyp.likou.array;

import java.util.Arrays;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-24 10:25
 * 66. 加一
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 *
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 *
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 *
 *
 *
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 *
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 *
 * 示例 3：
 * 输入：digits = [0]
 * 输出：[1]
 */
public class A_66 {
    public static int[] plusOne(int[] digits) {
        int carry = 0;
        int len = 0;

        if (digits == null || (len = digits.length) == 0) {
            return digits;
        }

        int tempRes = digits[len - 1] + 1;
        if (tempRes >= 10) {
            carry = tempRes / 10;
            tempRes = tempRes % 10;
        }
        digits[len - 1] = tempRes;

        if (carry >= 1) {
            for (int i = len - 2; i >= 0; i--) {
                tempRes = carry + digits[i];
                if (tempRes >= 10) {
                    carry = tempRes / 10;
                    tempRes = tempRes % 10;
                    digits[i] = tempRes;
                } else {
                    digits[i] = tempRes;
                    carry = 0;
                    break;
                }
            }
            if (carry >= 1) {
                int[] arr = new int[len + 1];
                arr[0] = carry;
                for (int i = 0; i < len; i++) {
                    arr[i + 1] = digits[i];
                }

                digits = arr;
            }
        }
        return digits;
    }

    /**
     * 官方题解
     * @param digits
     * @return
     */
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length - 1; i >= 0; i--) {
            //没有返回，表示有进位，再次加1
            digits[i]++;
            digits[i] = digits[i] % 10;
            // 不等于 0 ，表示 加1之后，没有进位，直接返回即可
            if (digits[i] != 0) return digits;
        }

        // 9,9 + 1 = 1,0,0 只有第一位为 1，后面的位上都是 0
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    public static void main(String[] args) {
        System.out.println(Arrays.toString(plusOne(new int[]{1, 2, 3})));
        System.out.println(Arrays.toString(plusOne(new int[]{0})));
        System.out.println(Arrays.toString(plusOne(new int[]{9})));
        System.out.println(Arrays.toString(plusOne(new int[]{1, 9})));
        System.out.println(Arrays.toString(plusOne(new int[]{9, 9, 9})));
    }
}
