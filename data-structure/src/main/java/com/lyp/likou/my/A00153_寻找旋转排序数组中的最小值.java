package com.lyp.likou.my;

/**
 * 153. 寻找旋转排序数组中的最小值
 * 已知一个长度为 n 的数组，预先按照升序排列，经由 1 到 n 次 旋转 后，得到输入数组。例如，原数组 nums = [0,1,2,4,5,6,7] 在变化后可能得到：
 * 若旋转 4 次，则可以得到 [4,5,6,7,0,1,2]
 * 若旋转 7 次，则可以得到 [0,1,2,4,5,6,7]
 * 注意，数组 [a[0], a[1], a[2], ..., a[n-1]] 旋转一次 的结果为数组 [a[n-1], a[0], a[1], a[2], ..., a[n-2]] 。
 *
 * 给你一个元素值 互不相同 的数组 nums ，它原来是一个升序排列的数组，并按上述情形进行了多次旋转。请你找出并返回数组中的 最小元素 。
 *
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 *
 *
 *
 * 示例 1：
 * 输入：nums = [3,4,5,1,2]
 * 输出：1
 * 解释：原数组为 [1,2,3,4,5] ，旋转 3 次得到输入数组。
 *
 * 示例 2：
 * 输入：nums = [4,5,6,7,0,1,2]
 * 输出：0
 * 解释：原数组为 [0,1,2,4,5,6,7] ，旋转 4 次得到输入数组。
 *
 * 示例 3：
 * 输入：nums = [11,13,15,17]
 * 输出：11
 * 解释：原数组为 [11,13,15,17] ，旋转 4 次得到输入数组。
 *
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 5000
 * -5000 <= nums[i] <= 5000
 * nums 中的所有整数 互不相同
 * nums 原来是一个升序排序的数组，并进行了 1 至 n 次旋转
 */
public class A00153_寻找旋转排序数组中的最小值 {

    /**
     * 我们考虑数组中的最后一个元素 x：在最小值右侧的元素（不包括最后一个元素本身），它们的值一定都严格小于 x；而在最小值左侧的元素，它们的值一定都严格大于 x。因此，我们可以根据这一条性质，通过二分查找的方法找出最小值。
     *
     * 在二分查找的每一步中，左边界为 low，右边界为 high，区间的中点为 pivot，最小值就在该区间内。我们将中轴元素 nums[pivot] 与右边界元素 nums[high] 进行比较，可能会有以下的三种情况：
     *
     * 第一种情况是 nums[pivot]<nums[high]。如下图所示，这说明 nums[pivot] 是最小值右侧的元素，因此我们可以忽略二分查找区间的右半部分。
     * 第二种情况是 nums[pivot]>nums[high]。如下图所示，这说明 nums[pivot] 是最小值左侧的元素，因此我们可以忽略二分查找区间的左半部分。
     * 由于数组不包含重复元素，并且只要当前的区间长度不为 1，pivot 就不会与 high 重合；而如果当前的区间长度为 1，这说明我们已经可以结束二分查找了。因此不会存在 nums[pivot]=nums[high] 的情况。
     *
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/find-minimum-in-rotated-sorted-array/solutions/698479/xun-zhao-xuan-zhuan-pai-xu-shu-zu-zhong-5irwp/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     *
     * 把官解讲得更通透一点：
     * 本题重点在于，如何利用二分。二分的思想在于，每次淘汰一半（记住这个思想，是所有二分题目的关键）。
     * 基于这个思想，我们要去想淘汰策略。我们发现本题中，有：
     * 最小元素 m 的左边所有元素都比数组的最后一个元素 x大，右边所有元素（不含x）都比 x 小
     * 于是我们的淘汰策略为：对于每一对low high，比较中间元素值和x的大小：
     * 1.nums[mid]<x：说明 mid 在 m 的右边，即目标 m 在 mid 的左边，故可淘汰右半边；
     * 2.nums[mid]>x：同理淘汰左半边；
     * 3.nums[mid]==x: 不可能，因为数组无重复值，如果成立，则必然有mid==n-1，则必然有low==high==n-1。但当low==high时，我们已经找到m
     */
    public static int findMin(int[] nums) {
        int left = 0, right = nums.length - 1;

        //不存在相等的值，所以这里 left < right ,没有 =
        // while 退出的条件是 left = right 时，所以最终结果返回 left 或right 均可
        // 注意这里没有=了，=的时候直接退出循环得到答案
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                //mid 肯定不是答案，所以 mid +1
                left = mid + 1;
                //这里和常规一样是 mid+1 是因为此时的mid不可能是m（他都比x大了 怎么可能是最小的）
            } else {
                //right可能是答案，所以这里是 = mid
                right = mid;
                //这里不是常规的 mid-1 是因为此时的mid有可能就是我们要找的m
            }
        }
        // 返回 nums[left],nums[right] 均可
        return nums[left];
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,4,5,1,2};
        System.out.println(findMin(nums));
    }

}
