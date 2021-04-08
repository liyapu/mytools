package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-08 11:10
 *
 * 148. 排序链表
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 进阶：
 *
 * 你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 *
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 5 * 104] 内
 * -105 <= Node.val <= 105
 */
public class L_148 {

    /**
     * 把链表拆分成两个链表，分别针对单个链表进行排序，然后再对有序后的链表进行排序
     * @param head
     * @return
     */
    public static ListNode sortList(ListNode head) {
        if(head ==  null || head.next == null){
            return head;
        }
        ListNode slow = head;
        ListNode faster = head;

        // 1，2，3，4 找中点，偶数个元素，让 slow 指向 2
        while (faster != null && faster.next != null && faster.next.next != null){
            slow = slow.next;
            faster = faster.next.next;
        }

        //找到 第二个头结点
        ListNode head2 = slow.next;
        //断开链表
        slow.next = null;
        //合并并递归
       return mergeList(sortList(head),sortList(head2));
    }

    private static ListNode mergeList(ListNode head1, ListNode head2) {
        if(head1 == null){
            return head2;
        }
        if(head2 == null){
            return head1;
        }
        if(head1.val < head2.val){
            head1.next = mergeList(head1.next,head2);
            return head1;
        }else{
            head2.next = mergeList(head1,head2.next);
            return head2;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(4,2,1,3);
        ListNode l1Result = sortList(l1);
        LinkUtils.printListNode(l1Result);

    }
}
