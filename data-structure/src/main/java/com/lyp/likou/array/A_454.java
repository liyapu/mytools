package com.lyp.likou.array;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-21 09:17
 *
 * 454. 四数相加 II
 * 给定四个包含整数的数组列表 A , B , C , D ,计算有多少个元组 (i, j, k, l) ，使得 A[i] + B[j] + C[k] + D[l] = 0。
 *
 * 为了使问题简单化，所有的 A, B, C, D 具有相同的长度 N，且 0 ≤ N ≤ 500 。所有整数的范围在 -228 到 228 - 1 之间，最终结果不会超过 231 - 1 。
 *
 *  A[i] + B[j] + C[k] + D[l] = 0
 *  总结，看到形如：A+B....+N=0的式子，要转换为(A+...T)=-((T+1)...+N)再计算，这个T的分割点一般是一半，特殊情况下需要自行判断。定T是解题的关键。
 *
 * 例如:
 *
 * 输入:
 * A = [ 1, 2]
 * B = [-2,-1]
 * C = [-1, 2]
 * D = [ 0, 2]
 *
 * 输出:
 * 2
 *
 * 解释:
 * 两个元组如下:
 * 1. (0, 0, 0, 1) -> A[0] + B[0] + C[0] + D[1] = 1 + (-2) + (-1) + 2 = 0
 * 2. (1, 1, 0, 0) -> A[1] + B[1] + C[0] + D[0] = 2 + (-1) + (-1) + 0 = 0
 */
public class A_454 {

    /**
     * 分组 + 哈希表
     * 思路与算法
     *
     * 我们可以将四个数组分成两部分，AA 和 BB 为一组，CC 和 DD 为另外一组。
     *
     * 对于 AA 和 BB，我们使用二重循环对它们进行遍历，得到所有 A[i]+B[j]A[i]+B[j] 的值并存入哈希映射中。对于哈希映射中的每个键值对，每个键表示一种 A[i]+B[j]A[i]+B[j]，对应的值为 A[i]+B[j]A[i]+B[j] 出现的次数。
     *
     * 对于 CC 和 DD，我们同样使用二重循环对它们进行遍历。当遍历到 C[k]+D[l]C[k]+D[l] 时，如果 -(C[k]+D[l])−(C[k]+D[l]) 出现在哈希映射中，那么将 -(C[k]+D[l])−(C[k]+D[l]) 对应的值累加进答案中。
     *
     * 最终即可得到满足 A[i]+B[j]+C[k]+D[l]=0A[i]+B[j]+C[k]+D[l]=0 的四元组数目。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/4sum-ii/solution/si-shu-xiang-jia-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param nums1
     * @param nums2
     * @param nums3
     * @param nums4
     * @return
     */
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        Map<Integer,Integer> countAB = new HashMap<>();
        for (int a : nums1) {
            for (int b : nums2) {
                countAB.put(a+b,countAB.getOrDefault(a+b,0)+1);
            }
        }

        int ans = 0;
        int temp = 0;
        for (int c : nums3) {
            for (int d : nums4) {
                temp = -c-d;
                if(countAB.containsKey(temp)){
                    ans += countAB.get(temp);
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {

    }
}
