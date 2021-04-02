package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 10:07
 *
 * 剑指 Offer 24. 反转链表
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 *
 *
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 *
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 *
 */
public class S_24 {
    public static ListNode reverseList(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }

    public static ListNode reverseList2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4, 5);
        ListNode l1Result = reverseList(l1);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(1, 2, 3, 4, 5);
        ListNode l2Result = reverseList2(l2);
        LinkUtils.printListNode(l2Result);
    }
}
