package com.lyp.likou.link;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 15:54
 *
 * 234. 回文链表
 * 请判断一个链表是否为回文链表。
 *
 * 示例 1:
 * 输入: 1->2
 * 输出: false
 * 示例 2:
 *
 * 输入: 1->2->2->1
 * 输出: true
 * 进阶：
 * 你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 */
public class L_234 {
    /**
     * 先 逆序链表，然后对比两个链表是否相等
     * 相等则为 回文
     * 不等则不是 回文
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (null == head) {
            return false;
        }
        //这里标记一下 head, 因为 逆序链表时，head 已经指向 null 了
        ListNode headMark = head;

        ListNode newHead = new ListNode(-1);
        ListNode pre = newHead;
        while (head != null) {
            ListNode tempNode = new ListNode(head.val);
            tempNode.next = pre.next;
            pre.next = tempNode;
            head = head.next;
        }

        //第一个节点是 -1, 需要获取下一个节点
        pre = newHead.next;

        while (headMark != null) {
            if (headMark.val != pre.val) {
                return false;
            }
            headMark = headMark.next;
            pre = pre.next;
        }
        return true;
    }

    /**
     * 把 链表数据，放入到 数组中(java中使用 ArrayList，因为不知道长度)，然后使用 首尾下标判断即可
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {
        if (null == head) {
            return false;
        }
        List<Integer> al = new ArrayList<>();
        while (head != null) {
            al.add(head.val);
            head = head.next;
        }

        for (int i = 0, j = al.size() - 1; i < j; i++, j--) {
            if (al.get(i) != al.get(j)) {
                return false;
            }
        }
        return true;
    }


    public static void main(String[] args) {
//        ListNode l1 = LinkUtils.buildList(1,1,2,1);
//        boolean l1Result = isPalindrome(l1);
//        System.out.println(l1Result);
//
//        ListNode l2 = LinkUtils.buildList(1,2);
//        boolean l2Result = isPalindrome(l2);
//        System.out.println(l2Result);

        ListNode l3 = LinkUtils.buildList(1, 2, 2, 1);
        boolean l3Result = isPalindrome2(l3);
        System.out.println(l3Result);

        ListNode l4 = LinkUtils.buildList(1, 2);
        boolean l4Result = isPalindrome2(l4);
        System.out.println(l4Result);
    }
}
