package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-07 10:41
 *
 * 86. 分隔链表
 * 给你一个链表的头节点 head 和一个特定值 x ，请你对链表进行分隔，使得所有 小于 x 的节点都出现在 大于或等于 x 的节点之前。
 *
 * 你应当 保留 两个分区中每个节点的初始相对位置。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,4,3,2,5,2], x = 3
 * 输出：[1,2,2,4,3,5]
 *
 * 示例 2：
 * 输入：head = [2,1], x = 2
 * 输出：[1,2]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 200] 内
 * -100 <= Node.val <= 100
 * -200 <= x <= 200
 */
public class L_86 {

    public static ListNode partition(ListNode head, int x) {
        if (head == null) {
            return head;
        }
        ListNode smallNode = new ListNode(-1);
        ListNode smallTailNode = smallNode;
        ListNode bigNode = new ListNode(-1);
        ListNode bigTailNode = bigNode;

        while (head != null) {
            if (head.val < x) {
                smallTailNode.next = head;
                smallTailNode = head;
            } else {
                bigTailNode.next = head;
                bigTailNode = head;
            }
            head = head.next;
        }

        //复用节点，切断最后节点的指向为null
        smallTailNode.next = null;
        bigTailNode.next = null;

        if (smallNode == smallTailNode) {
            return bigNode.next;
        }
        if (bigNode == bigTailNode) {
            return smallNode.next;
        }

        smallTailNode.next = bigNode.next;
        return smallNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 4, 3, 2, 5, 2);
        ListNode l1Result = partition(l1, 3);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(2, 1);
        ListNode l2Result = partition(l2, 3);
        LinkUtils.printListNode(l2Result);
    }
}
