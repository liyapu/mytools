package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 14:00
 *
 * 面试题 02.02. 返回倒数第 k 个节点
 * 实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
 *
 * 注意：本题相对原题稍作改动
 *
 * 示例：
 *
 * 输入： 1->2->3->4->5 和 k = 2
 * 输出： 4
 * 说明：
 *
 * 给定的 k 保证是有效的。
 */
public class F_0202 {

    public static int kthToLast(ListNode head, int k) {
        ListNode first = head;
        ListNode second  = head;
        for (int i = 0; i < k; i++) {
            second = second.next;
        }
        while (second != null){
            first = first.next;
            second = second.next;
        }
        return first.val;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1,2,3,4,5);
        int i = kthToLast(l1, 2);
        System.out.println(i);
    }
}
