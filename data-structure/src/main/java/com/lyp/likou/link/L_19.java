package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 14:08
 *
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * 示例 3：
 *
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 */
public class L_19 {

    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode first = head;
        ListNode second = head;
        for (int i = 0; i < n; i++) {
            second = second.next;
        }

        ListNode pre = null;
        while (second != null){
            pre = first;
            first = first.next;
            second = second.next;
        }

        if(null == pre){
            //删除第一个节点
            head = head.next;
        }else{
            pre.next = pre.next.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1);
        ListNode l1Result = removeNthFromEnd(l1, 1);
        LinkUtils.printListNode(l1Result);
    }
}
