package com.lyp.likou.array;

/**
 * 力扣  https://leetcode-cn.com/problems/median-of-two-sorted-arrays/
 *
 * 4. 寻找两个正序数组的中位数
 * 给定两个大小为 m 和 n 的正序（从小到大）数组nums1 和nums2。
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为O(log(m + n))。
 * 你可以假设nums1和nums2不会同时为空。
 *
 * 示例 1:
 * nums1 = [1, 3]
 * nums2 = [2]
 * 则中位数是 2.0
 *
 * 示例 2:
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 * 则中位数是 (2 + 3)/2 = 2.5
 */
public class median {

    public static void main(String[] args) {
        int[] nums1 = {1,2,100,200,300};
        int[] nums2 = {10,20,30};

        double result = findMedianSortedArrays2(nums1,nums2);
        System.out.println("result = " + result);
    }

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        double result = 0D;
        if (nums1 == null) {
            result = findMedianOneArray(nums2);
        }else if(nums2 == null){
            result = findMedianOneArray(nums1);
        }
        int n1Len = nums1.length;
        int n2Len = nums2.length;

        return 0D;
    }

    /**
     * 查找单个数组的中位数
     *
     * @param nums
     * @return
     */
    public static double findMedianOneArray(int[] nums) {
        int len = nums.length;
        int middle = len / 2;

        if (len % 2 == 0) {
            return (nums[middle - 1] + nums[middle]) / 2D;
        } else {
            return nums[middle];
        }
    }

    public static double findMedianSortedArrays2(int[] nums1, int[] nums2) {
        int length = nums1.length + nums2.length;
        int[] newnums = new int[length];

        int index1 = 0;
        int index2 = 0;
        int currrent = 0;

        while (index1 < nums1.length || index2 < nums2.length) {
            if (index2 >= nums2.length) {
                newnums[currrent++] = nums1[index1++];
                continue;
            }

            if (index1 >= nums1.length ) {
                newnums[currrent++] = nums2[index2++];
                continue;
            }

            if (nums1[index1] <= nums2[index2]  ) {
                newnums[currrent++] = nums1[index1++];
            } else if (nums1[index1] > nums2[index2]) {
                newnums[currrent++] = nums2[index2++];
            }
        }

        if (newnums.length % 2 == 0) {
            return ((double)(newnums[length /2] + newnums[length / 2 - 1])) / 2;
        } else {
            return ((double)newnums[length / 2]);
        }

    }
}
