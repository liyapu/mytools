package com.lyp.likou.lk;

import java.util.Arrays;
import java.util.Objects;

/**
 * 283. 移动零
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 *
 * 示例 1:
 * 输入: nums = [0,1,0,3,12]
 * 输出: [1,3,12,0,0]
 *
 * 示例 2:
 * 输入: nums = [0]
 * 输出: [0]
 *
 * 提示:
 * 1 <= nums.length <= 104
 * -231 <= nums[i] <= 231 - 1
 *
 *
 * 进阶：你能尽量减少完成的操作次数吗？
 */
public class A00283_移动零 {
    public static void moveZeroes(int[] nums) {
        if (Objects.isNull(nums)) {
            return;
        }
        int numLength = nums.length;
        if (numLength == 0 || numLength == 1) {
            return;
        }
        int zeroPoint = 0;
        int movePoint = 0;
        while (movePoint < numLength && zeroPoint < numLength) {
            if (nums[zeroPoint] != 0) {
                zeroPoint++;
            } else {
                if (nums[movePoint] != 0) {
                    //zeroPoint < movePoint 时才替换，控制 1,0 这样，0本来就在后边时不动
                    if (zeroPoint < movePoint) {
                        int temp = 0;
                        temp = nums[zeroPoint];
                        nums[zeroPoint] = nums[movePoint];
                        nums[movePoint] = temp;
                    } else {
                        //防止死循环，比如 1,0, zeroPoint = 1 指向了0，movePoint = 0 指向了1,如果2个指针都不动，则死循环了
                        movePoint++;
                    }

                }
            }


            if (nums[movePoint] == 0) {
                movePoint++;
            }
        }
    }

    public static void moveZeroes1(int[] nums) {
        /**
         * 核心思想: 把非零元素移动到左边。
         *
         * 移动过程如下:
         * 原始数组   [0,1,0,3,12]
         * 第一次移动 [1,0,0,3,12], 这样只需要交换0和1的位置, 而不是变成[1,0,3,12,0], 这样的话每个元素都需要移动位置, 时间复杂度较高
         * 第二次移动 [1,3,0,0,12]
         * 第三次移动 [1,3,12,0,0]
         *
         * 需要记录应该将非零元素交换到哪个位置, 例如在第二次移动时应该将3和下标为1的0进行交换位置, 所以需要一个变量(指针)进行记录
         */

        // 使用index记录应该将非零元素交换到哪个位置
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                // 交换位置
                int temp = nums[i];
                nums[i] = nums[index];
                nums[index] = temp;

                // 在此轮交换位置后, 下一次就应该将非零元素交换到下一个位置了
                index++;
            }
        }
    }

    public static void moveZeroes2(int[] nums) {
        // 使用index记录应该将非零元素交换到哪个位置
        int index = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                if (i != index) {
                    // 交换位置
                    int temp = nums[i];
                    nums[i] = nums[index];
                    nums[index] = temp;
                }
                // 在此轮交换位置后, 下一次就应该将非零元素交换到下一个位置了
                index++;
            }
        }
    }

    /**
     * 方法一：双指针
     * 思路及解法
     * 使用双指针，左指针指向当前已经处理好的序列的尾部，右指针指向待处理序列的头部。
     * 右指针不断向右移动，每次右指针指向非零数，则将左右指针对应的数交换，同时左指针右移。
     *
     * 注意到以下性质：
     * 左指针左边均为非零数；
     * 右指针左边直到左指针处均为零。
     *
     * 因此每次交换，都是将左指针的零与右指针的非零数交换，且非零数的相对顺序并未改变。
     */
    public static void moveZeroes3(int[] nums) {
        int length = nums.length, left = 0, right = 0;
        while (right < length) {
            if (nums[right] != 0) {
                int temp = nums[right];
                nums[right] = nums[left];
                nums[left] = temp;
                //下次left替换位置加1
                left++;
            }
            right++;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums));
        moveZeroes(nums);
        System.out.println(Arrays.toString(nums));
        System.out.println();

        int[] nums1 = new int[]{0};
        System.out.println(Arrays.toString(nums1));
        moveZeroes(nums1);
        System.out.println(Arrays.toString(nums1));
        System.out.println();

        int[] nums2 = new int[]{1, 0};
        System.out.println(Arrays.toString(nums2));
        moveZeroes(nums2);
        System.out.println(Arrays.toString(nums2));
        System.out.println();

        System.out.println("----------------");
        int[] nums3 = new int[]{8,0, 1, 0, 3, 12};
        System.out.println(Arrays.toString(nums3));
        moveZeroes1(nums3);
        System.out.println(Arrays.toString(nums3));
        System.out.println();

    }
}
