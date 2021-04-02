package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 15:59
 *
 * 61. 旋转链表
 * 给你一个链表的头节点 head ，旋转链表，将链表每个节点向右移动 k 个位置。
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[4,5,1,2,3]
 *
 * 示例 2：
 * 输入：head = [0,1,2], k = 4
 * 输出：[2,0,1]
 *
 *
 * 提示：
 * 链表中节点的数目在范围 [0, 500] 内
 * -100 <= Node.val <= 100
 * 0 <= k <= 2 * 109
 */
public class L_61 {
    /**
     * k 有可能超过链表长度，所以需要计算链表长度，并且 模除
     * 需要首尾节点连成 环，所以遍历的时候 判断 tail.next != null ,从而获取到 尾节点
     *
     *
     * 闭合为环
     * 思路及算法
     *
     * 记给定链表的长度为 n，注意到当向右移动的次数 k≥n 时，我们仅需要向右移动 kmodn 次即可。因为每 n 次移动都会让链表变为原状。这样我们可以知道，新链表的最后一个节点为原链表的第 (n−1)−(kmodn) 个节点（从 0 开始计数）。
     *
     * 这样，我们可以先将给定的链表连接成环，然后将指定位置断开。
     *
     * 具体代码中，我们首先计算出链表的长度 n，并找到该链表的末尾节点，将其与头节点相连。这样就得到了闭合为环的链表。然后我们找到新链表的最后一个节点（即原链表的第 (n−1)−(kmodn) 个节点），将当前闭合为环的链表断开，即可得到我们所需要的结果。
     *
     * 特别地，当链表长度不大于 1，或者 k 为 n 的倍数时，新链表将与原链表相同，我们无需进行任何处理。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/rotate-list/solution/xuan-zhuan-lian-biao-by-leetcode-solutio-woq1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @param k
     * @return
     */
    public static ListNode rotateRight(ListNode head, int k) {
        if (k == 0 || head == null || head.next == null) {
            return head;
        }

        int size = 1;

        ListNode tail = head;
        while (tail.next != null) {
            size++;
            tail = tail.next;
        }

        k %= size;
        //如果移动 0 个位置或者 size 个位置，即还是 原链表
        if (k == 0) {
            return head;
        }

        //链表变成环
        tail.next = head;
        //需要再减 1
        for (int i = 0; i < (size - k) - 1; i++) {
            head = head.next;
        }

        ListNode next = head.next;
        head.next = null;
        return next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4, 5);
        ListNode l1Result = rotateRight(l1, 0);
        LinkUtils.printListNode(l1Result);
    }
}
