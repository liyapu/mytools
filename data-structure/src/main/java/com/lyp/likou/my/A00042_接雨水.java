package com.lyp.likou.my;


import java.util.Objects;
import java.util.Stack;

/**
 * 42.接雨水
 * 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 *
 * 示例 1：
 * 输入：height = [0,1,0,2,1,0,1,3,2,1,2,1]
 * 输出：6
 * 解释：上面是由数组 [0,1,0,2,1,0,1,3,2,1,2,1] 表示的高度图，在这种情况下，可以接 6 个单位的雨水（蓝色部分表示雨水）。
 *
 * 示例 2：
 * 输入：height = [4,2,0,3,2,5]
 * 输出：9
 *
 * 提示：
 * n == height.length
 * 1 <= n <= 2 * 104
 * 0 <= height[i] <= 105
 */
public class A00042_接雨水 {
    /**
     * 方法一：动态规划
     * 对于下标 i，下雨后水能到达的最大高度等于下标 i 两边的最大高度的最小值，
     * 下标 i 处能接的雨水量等于下标 i 处的水能到达的最大高度减去 height[i]。
     *
     * 朴素的做法是对于数组 height 中的每个元素，分别向左和向右扫描并记录左边和右边的最大高度，然后计算每个下标位置能接的雨水量。
     * 假设数组 height 的长度为 n，该做法需要对每个下标位置使用 O(n) 的时间向两边扫描并得到最大高度，
     * 因此总时间复杂度是 O(n²‌)
     *
     * 上述做法的时间复杂度较高是因为需要对每个下标位置都向两边扫描。
     * 如果已经知道每个位置两边的最大高度，则可以在 O(n) 的时间内得到能接的雨水总量。
     * 使用动态规划的方法，可以在 O(n) 的时间内预处理得到每个位置两边的最大高度。
     *
     * 创建两个长度为 n 的数组 leftMax 和 rightMax。
     * 对于 0≤i<n，
     * leftMax[i]  表示下标 i 及其左边的位置中，height 的最大高度，
     * rightMax[i] 表示下标 i 及其右边的位置中，height 的最大高度。
     *
     * 显然，leftMax[0]=height[0]，rightMax[n−1]=height[n−1]。
     * 两个数组的其余元素的计算如下：
     *
     * 当 1≤i≤n−1 时，leftMax[i]=max(leftMax[i−1],height[i])；
     * 当 0≤i≤n−2 时，rightMax[i]=max(rightMax[i+1],height[i])。
     *
     * 因此可以正向遍历数组 height 得到数组 leftMax 的每个元素值，反向遍历数组 height 得到数组 rightMax 的每个元素值。
     * 在得到数组 leftMax 和 rightMax 的每个元素值之后，
     * 对于 0≤i<n，下标 i 处能接的雨水量等于 min(leftMax[i],rightMax[i]) − height[i]。
     * 遍历每个下标位置即可得到能接的雨水总量。
     *
     * 动态规划做法可以由下图体现。
     */
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }

        int[] leftMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        int[] rightMax = new int[n];
        rightMax[n - 1] = height[n - 1];
        for (int j = n - 2; j >= 0; j--) {
            rightMax[j] = Math.max(rightMax[j + 1], height[j]);
        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return sum;
    }

    /**
     * 方法二：单调栈*
     * 维护一个单调栈，单调栈存储的是下标，满足从栈底到栈顶的下标对应的数组 height 中的元素递减。
     *
     * 从左到右遍历数组，遍历到下标 i 时，如果栈内至少有两个元素，记栈顶元素为 top，top 的下面一个元素是 left，则一定有 height[left]≥height[top]。
     * 如果 height[i]>height[top]，则得到一个可以接雨水的区域，该区域的宽度是 i−left−1，高度是 min(height[left],height[i])−height[top]，根据宽度和高度即可计算得到该区域能接的雨水量。
     *
     * 为了得到 left，需要将 top 出栈。在对 top 计算能接的雨水量之后，left 变成新的 top，重复上述操作，直到栈变为空，或者栈顶下标对应的 height 中的元素大于或等于 height[i]。
     * 在对下标 i 处计算能接的雨水量之后，将 i 入栈，继续遍历后面的下标，计算能接的雨水量。遍历结束之后即可得到能接的雨水总量。
     *
     */
    public int trap1(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        Stack<Integer> stack  = new Stack<>();
        int sum = 0;
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]){
                //每次只弹出一个元素，其它地方都是 peek 瞥看一眼
                Integer top = stack.pop();
                //为空则是挨着的，无法计算
                if(stack.isEmpty()){
                    break;
                }
                Integer left = stack.peek();
                int width = i - left - 1;
                //left 和 i 是最左和最右
                int high = Math.min(height[left],height[i]) - height[top];
                sum += width*high;
            }
            stack.add(i);
        }
        return sum;
    }

    /**
     * 方法三：双指针
     * 动态规划的做法中，需要维护两个数组 leftMax 和 rightMax，因此空间复杂度是 O(n)。是否可以将空间复杂度降到 O(1)？
     *
     * 注意到下标 i 处能接的雨水量由 leftMax[i] 和 rightMax[i] 中的最小值决定。由于数组 leftMax 是从左往右计算，数组 rightMax 是从右往左计算，因此可以使用双指针和两个变量代替两个数组。
     *
     * 维护两个指针 left 和 right，以及两个变量 leftMax 和 rightMax，初始时 left=0,right=n−1,leftMax=0,rightMax=0。指针 left 只会向右移动，指针 right 只会向左移动，在移动指针的过程中维护两个变量 leftMax 和 rightMax 的值。
     *
     * 当两个指针没有相遇时，进行如下操作：
     * 使用 height[left] 和 height[right] 的值更新 leftMax 和 rightMax 的值；
     * 如果 height[left]<height[right]，则必有 leftMax<rightMax，下标 left 处能接的雨水量等于 leftMax−height[left]，将下标 left 处能接的雨水量加到能接的雨水总量，然后将 left 加 1（即向右移动一位）；
     * 如果 height[left]≥height[right]，则必有 leftMax≥rightMax，下标 right 处能接的雨水量等于 rightMax−height[right]，将下标 right 处能接的雨水量加到能接的雨水总量，然后将 right 减 1（即向左移动一位）。
     *
     * 当两个指针相遇时，即可得到能接的雨水总量
     *
     */
    public int trap2(int[] height) {
        if (Objects.isNull(height)) {
            return 0;
        }
        int n = height.length;
        int left = 0, right = n - 1, leftMax = 0, rightMax = 0;
        int sum = 0;
        while (left < right) {

//            if (height[left] > leftMax) {
//                leftMax = height[left];
//            }
//            if (height[right] > rightMax) {
//                rightMax = height[right];
//            }

            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            if (leftMax < rightMax) {
                sum += leftMax - height[left];
                left++;
            } else {
                sum += rightMax - height[right];
                //这里是右--，都向中间走
                right--;
            }
        }
        return sum;
    }


    public static void main(String[] args) {
        A00042_接雨水 c = new A00042_接雨水();
        int[] nums = new int[]{0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        System.out.println(c.trap(nums));
        System.out.println(c.trap1(nums));
        System.out.println(c.trap2(nums));

        int[] nums1 = new int[]{4, 2, 0, 3, 2, 5};
        System.out.println(c.trap(nums1));
        System.out.println(c.trap1(nums1));
        System.out.println(c.trap2(nums1));
    }

}
