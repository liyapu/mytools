package com.lyp.likou.my;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
public class A00206_反转链表 {


    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //tail 表示新链表的尾节点
        ListNode current = head, temp = null, tail = null;
        //假设列表为 1->2->3->4->5
        while (current != null) {
            //当前 current为1
            //temp 指向2，防止丢失
            temp = current.next;
            //当前current 1的next指针指向反转链表的尾部
            current.next = tail;
            //反转链表的尾部指向当前current元素
            tail= current;
            //当前原始current 指向 temp,即current的下一个元素
            current = temp;
        }
        return tail;
    }
}
