package com.lyp.mianshiti;

import java.util.Arrays;
import java.util.Objects;

/**
 * @author liyapu
 * @date 2023-06-05 20:56
 * @description java语言实现
 * 现在一个内部稀有若干 "1"，"-1"，"0"的int数组，要求再数组上排序，使所有的"1"均在数组的左边，所有的"0"都在数组的右边
 * <p>
 * 可以使用双指针法，定义两个指针left和right，left指向数组的第一个位置，right指向数组的最后一个位置。
 * 然后循环遍历数组，如果当前元素为1，则将其与left指向的元素交换，并将left指针向右移动一位；
 * 如果当前元素为0，则不做任何操作，直接继续遍历；
 * 如果当前元素为-1，则将其与right指向的元素交换，并将right指针向左移动一位。直到left指针大于等于right指针为止，排序完成。
 */
public class ArrayDemo {
    public static void main(String[] args) {
        //无序 -1，0，1 变成 1...1,-1....-1,0....0
        int[] arrs = new int[]{1, 1, 0, 0, -1, -1, 1, 0, -1, -1, 0, 1};
        System.out.println(Arrays.toString(arrs));
//        sort(arrs);
        sortArray(arrs);
        System.out.println(Arrays.toString(arrs));

    }

    public static void sort(int[] arrs) {
        if (Objects.isNull(arrs) || arrs.length == 0) {
            return;
        }
        int len = arrs.length;
        for (int i = 0, j = len - 1; i <= j; ) {
            int start = arrs[i];
            int end = arrs[j];
            if (start == 1) {
                i++;
                continue;
            }
            if (end == 0) {
                j--;
                continue;
            }

            int temp;
            if (start == 0) {
                if (end == 1) {
                    temp = arrs[i];
                    arrs[i] = arrs[j];
                    arrs[j] = temp;
                    i++;
                    j--;
                } else {
                    temp = arrs[i];
                    arrs[i] = arrs[j];
                    arrs[j] = temp;
                    j--;
                }
                continue;
            }
            if (start == -1) {
                if (end == 1) {
                    temp = arrs[i];
                    arrs[i] = arrs[j];
                    arrs[j] = start;
                    i++;
                    continue;
                } else {
                    boolean isMoved = false;
                    for (int k = i + 1; k <= j; k++) {
                        int ak = arrs[k];
                        if (ak == 1) {
                            temp = arrs[i];
                            arrs[i] = arrs[k];
                            arrs[k] = temp;
                            i++;
                            isMoved = true;
                            break;
                        }
                        if (ak == 0) {
                            temp = arrs[j];
                            arrs[j] = arrs[k];
                            arrs[k] = temp;
                            j--;
                            isMoved = true;
                            break;
                        }
                    }
                    if (!isMoved) {
                        break;
                    }
                }
            }

        }

    }

    public static void sortArray(int[] nums) {
        int left = 0, right = nums.length - 1;
        for (int i = 0; i <= right; ) {
            if (nums[i] == 1) {
                swap(nums, i, left);
                left++;
                i++;
            } else if (nums[i] == -1) {
                i++;
            } else {
                swap(nums, i, right);
                right--;
            }
        }
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
