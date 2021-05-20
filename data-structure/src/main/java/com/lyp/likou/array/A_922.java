package com.lyp.likou.array;

import java.util.Arrays;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-20 10:32
 *
 * 922. 按奇偶排序数组 II
 * 给定一个非负整数数组 A， A 中一半整数是奇数，一半整数是偶数。
 *
 * 对数组进行排序，以便当 A[i] 为奇数时，i 也是奇数；当 A[i] 为偶数时， i 也是偶数。
 *
 * 你可以返回任何满足上述条件的数组作为答案。
 *
 *
 *
 * 示例：
 * 输入：[4,2,5,7]
 * 输出：[4,5,2,7]
 * 解释：[4,7,2,5]，[2,5,4,7]，[2,7,4,5] 也会被接受。
 *
 *
 * 提示：
 *
 * 2 <= A.length <= 20000
 * A.length % 2 == 0
 * 0 <= A[i] <= 1000
 */
public class A_922 {
    public int[] sortArrayByParityII(int[] nums) {
        int i = 0;
        int j = 1;
        int len = nums.length;
        int temp;

        // 这里要用  && ，同时满足或者同时不满足，否则会有数组下标溢出
        while (i < len && j < len) {
            if (isEven(nums[i])) {
                i += 2;
                continue;
            } else if (isOdd(nums[j])) {
                j += 2;
                continue;
            } else {
                temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;

                i += 2;
                j += 2;
            }
        }
        return nums;
    }

    /**
     * 是否是 偶数
     * @param num
     * @return
     */
    public boolean isEven(int num) {
        return num % 2 == 0;
    }

    /**
     * 是否是 奇数
     * @param num
     * @return
     */
    public boolean isOdd(int num) {
        return num % 2 == 1;
    }

    public static void main(String[] args) {
        A_922 a = new A_922();
        System.out.println(Arrays.toString(a.sortArrayByParityII(new int[]{4, 2, 5, 7})));
    }
}
