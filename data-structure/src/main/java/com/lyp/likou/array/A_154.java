package com.lyp.likou.array;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-30 14:37
 *
 * 154. 寻找旋转排序数组中的最小值 II
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,4,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,4]
 * 若旋转 7 次，则可以得到 [0,1,4,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个可能存在 重复 元素值的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [1,3,5]
 * 输出：1
 *
 * 示例 2：
 * 输入：nums = [2,2,2,0,1]
 * 输出：0
 *
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class A_154 {

    /**
     * 看题解分析
     *
     * 第三种情况是 nums[mid]==nums[right]。如下图所示，由于重复元素的存在，我们并不能确定nums[mid] 究竟在最小值的左侧还是右侧，
     * 因此我们不能莽撞地忽略某一部分的元素。我们唯一可以知道的是，由于它们的值相同，所以无论 nums[high] 是不是最小值，都有一个它的「替代品」nums[pivot]，
     * 因此我们可以忽略二分查找区间的右端点
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/find-minimum-in-rotated-sorted-array-ii/solution/xun-zhao-xuan-zhuan-pai-xu-shu-zu-zhong-de-zui--16/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums
     * @return
     */
    public static int findMin(int[] nums) {
        int len;
        if (null == nums || (len = nums.length) == 0) {
            return -1;
        }
        int left = 0;
        int right = len - 1;
        int mid;
        while (left < right) {
            mid = left + (right - left) / 2;
            if(nums[mid] < nums[right]){
                right = mid;
            }else if(nums[mid] > nums[right]){
                left = mid + 1;
            }else{
                //mid 等于 right 时
                right--;
            }
        }
        return nums[left];
    }

    public static void main(String[] args) {
        System.out.println(findMin(new int[]{2}));
        System.out.println(findMin(new int[]{2, 1}));
        System.out.println(findMin(new int[]{3, 4, 5, 1, 2}));
        System.out.println(findMin(new int[]{4, 5, 6, 7, 0, 1, 2}));
        System.out.println(findMin(new int[]{11, 13, 15, 17}));
    }


}
