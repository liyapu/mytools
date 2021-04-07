package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-06 09:10
 *
 * 82. 删除排序链表中的重复元素 II
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
 *
 * 返回同样按升序排列的结果链表。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *
 *
 * 提示：
 *
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序排列
 */
public class L_82 {
    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode dummyNode = new ListNode(-1,head);

        ListNode baseNode = dummyNode;

        ListNode cur = dummyNode.next;
        ListNode next = cur.next;

        while (next != null) {
            if (cur.val == next.val) {
                // 1 1 2
                //发现cur不能要，立刻就断开
                baseNode.next = null;
                next = next.next;
            } else {
                if (cur.next == next) {
                    // 1 2 3
                    baseNode = cur;
                    cur = next;
                    next = next.next;
                } else {
                    //这里，要先设置上 next, 否则 next 可能会丢失
                    //next或符合要求，则带着，
                    //next不符合要求，cur.val == next.val 时，会自动删除
                    // 1 1 2 3
                    baseNode.next = next;
                    cur = next;
                    next = next.next;
                }
            }
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 1, 2, 2, 3, 4, 4, 5, 5);
        ListNode l1Result = deleteDuplicates(l1);
        LinkUtils.printListNode(l1Result);
    }
}
