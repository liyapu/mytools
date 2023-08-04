package com.lyp.likou.link;

/**
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class L_206_my1 {


    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode pre = head;
        ListNode next = pre.next;
        ListNode tail;
        while (next != null) {
            tail = next.next;
            next.next = head;
            head.next = null;
        }
        return next;
    }


//    public static ListNode reverseList2(ListNode head) {
//
//    }


//    public static ListNode reverseList3(ListNode head) {
//
//    }
//
//    public ListNode reverseList4(ListNode head) {
//
//    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4, 5, 6);
        LinkUtils.printListNode(l1);
        ListNode l1Result = reverseList(l1);
        LinkUtils.printListNode(l1Result);
//
//        ListNode l2 = LinkUtils.buildList(1,2,3,4,5);
//        ListNode l2Result = reverseList2(l2);
//        LinkUtils.printListNode(l2Result);
//
//        ListNode l3 = LinkUtils.buildList(1,2,3,4,5);
//        ListNode l3Result = reverseList3(l3);
//        LinkUtils.printListNode(l3Result);
    }
}
