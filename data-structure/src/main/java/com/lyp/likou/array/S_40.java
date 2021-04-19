package com.lyp.likou.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-16 16:23
 *
 * 剑指 Offer 40. 最小的k个数
 * 输入整数数组 arr ，找出其中最小的 k 个数。例如，输入4、5、1、6、2、7、3、8这8个数字，则最小的4个数字是1、2、3、4。
 *
 *
 *
 * 示例 1：
 *
 * 输入：arr = [3,2,1], k = 2
 * 输出：[1,2] 或者 [2,1]
 * 示例 2：
 *
 * 输入：arr = [0,1,2,1], k = 1
 * 输出：[0]
 *
 *
 * 限制：
 *
 * 0 <= k <= arr.length <= 10000
 * 0 <= arr[i] <= 10000
 */
public class S_40 {

    /**
     *  大根堆(前 K 小) / 小根堆（前 K 大),Java中有现成的 PriorityQueue，实现起来最简单：O(NlogK)O(NlogK)
     *
     * 我们用一个大根堆实时维护数组的前 kk 小值。首先将前 kk 个数插入大根堆中，随后从第 k+1k+1 个数开始遍历，如果当前遍历到的数比大根堆的堆顶的数要小，就把堆顶的数弹出，
     * 再插入当前遍历到的数。最后将大根堆里的数存入数组返回即可
     * 。在下面的代码中，由于 C++ 语言中的堆（即优先队列）为大根堆，我们可以这么做。
     * 而 Python 语言中的堆为小根堆，因此我们要对数组中所有的数取其相反数，才能使用小根堆维护前 kk 小值
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/solution/zui-xiao-de-kge-shu-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param arr
     * @param k
     * @return
     *
     * PriorityQueue默认是一个小顶堆，然而可以通过传入自定义的Comparator函数来实现大顶堆
     *
     *  // 默认实现了一个最小堆。
     *  Queue<Integer> priorityQueue = new PriorityQueue<>();
     *
     *  // 实现最大堆
     *   Queue<ListNode> priorityQueue = new PriorityQueue<ListNode>(lists.size(),new Comparator<ListNode>(){
     *      @Override
     *       public int compare(ListNode o1, ListNode o2) {
     *         return o2.val-o1.val;
     *       }
     *   });
     *
     */
    public static int[] getLeastNumbers(int[] arr, int k) {
        if (arr == null || k <= 0) return null;
        int length = arr.length;
        if (k >= length) return arr;

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2 - o1;
            }
        });
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        for (int i = k; i < length; i++) {
            if (pq.peek() > arr[i]) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }

        int[] result = new int[k];

        for (int i = 0; i < k; i++) {
            result[i] = pq.poll();
        }
        return result;
    }

    public static int[] getLeastNumbers2(int[] arr, int k) {
        if (arr == null || k <= 0) return null;
        int length = arr.length;
        if (k >= length) return arr;

        PriorityQueue<Integer> pq = new PriorityQueue<>(k, (o1, o2) -> o2 - o1);
        for (int i = 0; i < k; i++) {
            pq.offer(arr[i]);
        }
        for (int i = k; i < length; i++) {
            if (pq.peek() > arr[i]) {
                pq.poll();
                pq.offer(arr[i]);
            }
        }

        int[] result = new int[k];
        int index = 0;
        for (Integer i : pq) {
            result[index++] = i;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] arr = {3, 2, 1};
        int[] result = getLeastNumbers(arr, 2);
        System.out.println(Arrays.toString(result));
    }
}
