package com.lyp.likou.lk;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 15.三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 *
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * 解释：
 * nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
 * nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
 * nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
 * 不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
 * 注意，输出的顺序和三元组的顺序并不重要。
 *
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * 解释：唯一可能的三元组和不为 0 。
 *
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * 解释：唯一可能的三元组和为 0 。
 *
 *
 * 提示：
 * 3 <= nums.length <= 3000
 * -105 <= nums[i] <= 105
 */
public class A00015_三数之和 {
    public static List<List<Integer>> threeSum(int[] nums) {

        int numLength = nums.length;

        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < numLength; i++) {
            int firstNum = nums[i];
            Map<Integer, Integer> value2indexMap = new HashMap<>();
            //firstNum 已确定，secondNum如果已经出现了，则 threeNum也是固定的了，这种情况直接跳过，避免重复
            Set<Integer> repeat = new HashSet<>();

            for (int j = i + 1; j < numLength; j++) {
                if (repeat.contains(nums[j])) {
                    continue;
                }
                // A + B + C = 0
                //     B + C = -A
                //         C = -A-B
                if (value2indexMap.containsKey((-firstNum) - nums[j])) {
                    List<Integer> numList = new ArrayList<>();
                    numList.add(firstNum);
                    numList.add(nums[j]);
                    numList.add((-firstNum) - nums[j]);
                    result.add(numList);

                    repeat.add(nums[j]);
                }
                value2indexMap.put(nums[j], j);
            }
        }
        return result.stream()
                .map(a -> a.stream().sorted().collect(Collectors.toList()))
                .distinct()
                .collect(Collectors.toList());
    }

    /**
     * 排序 + 双指针
     * 本题的难点在于如何去除重复解。
     *
     * 算法流程：
     * 特判，对于数组长度 n，如果数组为 null 或者数组长度小于 3，返回 []。
     * 对数组进行排序。
     * 遍历排序后数组：
     * 若 nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于 0，直接返回结果。
     * 对于重复元素：跳过，避免出现重复解
     * 令左指针 L=i+1，右指针 R=n−1，当 L<R 时，执行循环：
     * 当 nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将 L,R 移到下一位置，寻找新的解
     * 若和大于 0，说明 nums[R] 太大，R 左移
     * 若和小于 0，说明 nums[L] 太小，L 右移
     *
     *
     * 作者：吴彦祖
     * 链接：https://leetcode.cn/problems/3sum/solutions/39722/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> resultList = new ArrayList<>();
        if (nums == null) {
            return resultList;
        }
        int len = nums.length;
        if (len < 3) {
            return resultList;
        }
        Arrays.sort(nums);

        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                //nums经过上面的排序之后，最小值都大于0，不可能存在和为0的数了
                //如果遍历的起始元素大于0，就直接退出
                //原因，此时数组为有序的数组，最小的数都大于0了，三数之和肯定大于0
                return resultList;
            }
            //去重，当起始的值等于前一个元素，那么得到的结果将会和前一次相同
            if (i > 0 && nums[i] == nums[i - 1]) {
                //第一个数，前后两个数相同，则跳过
                continue;
            }
            int left = i + 1, right = len - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    resultList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    //第二个数，前后两个数相同，则跳过
                    //去重，因为 i 不变，当此时 left取的数的值与前一个数相同，所以不用在计算，直接跳
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    //第三个数，前后两个数相同，则跳过
                    while (left < right && nums[right] == nums[right + 1]) {
                        right--;
                    }
                } else if (sum > 0) {
                    //太大了，右边最大值要减小
                    right--;
                } else {
                    //太小了，左边最小值要增大
                    left++;
                }
            }
        }
        return resultList;
    }


    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums));
    }
}
