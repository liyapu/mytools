package com.lyp.likou.lk;

import com.lyp.likou.lk.po.ListNode;
import com.lyp.likou.lk.utils.LinkUtils;

/**
 * 25. K 个一组翻转链表
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 *
 *
 *
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 */
public class A00025_K个一组翻转链表 {

    public static ListNode reverseKGroup(ListNode head, int k) {
        //先求出整体长度
        int len = 0;
        ListNode cur = head;
        while (cur != null) {
            len++;
            cur = cur.next;
        }
        ListNode dummyNode = new ListNode(-1, head);
        ListNode pre = dummyNode;
        cur = head;
        ListNode next = null;

        //大于等于k ,才进行反转
        for (; len >= k ; len = len-k) {
            for (int i = 0; i < k-1; i++) {
                next = cur.next;
                cur.next = next.next;
                next.next = pre.next;
                pre.next = next;
            }
            // 小分组反转之后，pre,cur 后移
            //next 不需要后移，因为在内层for循环中，next会等于 cur.next;
            pre = cur;
            cur = cur.next;

        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
//        ListNode listNode1 = LinkUtils.buildList(1, 2, 3, 4, 5);
//        LinkUtils.printListNode(reverseKGroup(listNode1, 2));
//
//        ListNode listNode2 = LinkUtils.buildList(1, 2, 3, 4, 5,6,7);
//        LinkUtils.printListNode(reverseKGroup(listNode2, 3));

        ListNode listNode3 = LinkUtils.buildList(1, 2);
        LinkUtils.printListNode(reverseKGroup(listNode3, 2));
    }


}
