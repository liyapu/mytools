package com.lyp.likou.my;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 167. 两数之和 II - 输入有序数组
 * 给你一个下标从 1 开始的整数数组 numbers ，该数组已按 非递减顺序排列  ，
 * 请你从数组中找出满足相加之和等于目标数 target 的两个数。
 * 如果设这两个数分别是 numbers[index1] 和 numbers[index2] ，则 1 <= index1 < index2 <= numbers.length 。
 *
 * 以长度为 2 的整数数组 [index1, index2] 的形式返回这两个整数的下标 index1 和 index2。
 * 你可以假设每个输入 只对应唯一的答案 ，而且你 不可以 重复使用相同的元素。
 *
 * 你所设计的解决方案必须只使用常量级的额外空间。
 *
 *
 * 示例 1：
 * 输入：numbers = [2,7,11,15], target = 9
 * 输出：[1,2]
 * 解释：2 与 7 之和等于目标数 9 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 示例 2：
 * 输入：numbers = [2,3,4], target = 6
 * 输出：[1,3]
 * 解释：2 与 4 之和等于目标数 6 。因此 index1 = 1, index2 = 3 。返回 [1, 3] 。
 *
 * 示例 3：
 * 输入：numbers = [-1,0], target = -1
 * 输出：[1,2]
 * 解释：-1 与 0 之和等于目标数 -1 。因此 index1 = 1, index2 = 2 。返回 [1, 2] 。
 *
 * 提示：
 * 2 <= numbers.length <= 3 * 104
 * -1000 <= numbers[i] <= 1000
 * numbers 按 非递减顺序 排列
 * -1000 <= target <= 1000
 * 仅存在一个有效答案
 */
public class A00167_两数之和2_输入有序数组 {

    /**
     * 利用 Map数据结构
     * 这种写法没有用上数组的有序性， 针对无序和有序均可
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum(int[] numbers, int target) {
        if (Objects.isNull(numbers)) {
            return new int[]{};
        }
        int len = numbers.length;
        Map<Integer, Integer> num2IndexMap = new HashMap<>();
        for (int i = 0; i < len; i++) {
            int a = numbers[i];
            if (num2IndexMap.containsKey(target - a)) {
                return new int[]{num2IndexMap.get(target - a) + 1, i + 1};
            }
            num2IndexMap.put(a, i);
        }
        return new int[]{};
    }

    /**
     * 这道题可以使用「1. 两数之和」的解法，使用 O(n2) 的时间复杂度和 O(1) 的空间复杂度暴力求解，
     * 或者借助哈希表使用 O(n) 的时间复杂度和 O(n) 的空间复杂度求解。
     * 但是这两种解法都是针对无序数组的，没有利用到输入数组有序的性质。利用输入数组有序的性质，可以得到时间复杂度和空间复杂度更优的解法。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/solutions/337156/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param numbers
     * @param target
     * @return
     */

    /**
     * 方法一：二分查找
     * 在数组中找到两个数，使得它们的和等于目标值，可以首先固定第一个数，然后寻找第二个数，第二个数等于目标值减去第一个数的差。
     * 利用数组的有序性质，可以通过二分查找的方法寻找第二个数。为了避免重复寻找，在寻找第二个数时，只在第一个数的右侧寻找。
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/solutions/337156/liang-shu-zhi-he-ii-shu-ru-you-xu-shu-zu-by-leet-2/
     * 来源：力扣（LeetCode）
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum2(int[] numbers, int target) {
        if (numbers == null) {
            return new int[]{};
        }
        int len = numbers.length;
        //第一层for循环，相当于固定第一个数
        for (int i = 0; i < len; i++) {
            int left = i + 1, right = len - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                if (numbers[mid] == target - numbers[i]) {
                    return new int[]{i + 1, mid + 1};
                } else if (numbers[mid] > target - numbers[i]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return new int[]{};

    }

    /**
     * 双指针
     *
     * 记中间的任意数字为 mid，满足 nums[l] ≤ mid ≤ nums[r]。
     *
     * 如果 nums[l] + nums[r] = target，数对 (nums[l], nums[r]) 即为答案。
     * 如果 nums[l] + nums[r] < target，则 nums[l]+ mid < target，可舍弃 nums[l]，令 l 右移。 //让小的增大
     * 如果 nums[l] + nums[r] > target，则 mid + nums[r] > target，可舍弃 nums[r]，令 r 左移。//让大的减小
     *
     * @param numbers
     * @param target
     * @return
     */
    public int[] twoSum3(int[] numbers, int target) {
        int len = numbers.length;
        int left = 0, right = len - 1;
        while (left < right) {
            int sum = numbers[left] + numbers[right];
            if (sum == target) {
                return new int[]{left + 1, right + 1};
            }
            if (sum < target) {
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }

}
