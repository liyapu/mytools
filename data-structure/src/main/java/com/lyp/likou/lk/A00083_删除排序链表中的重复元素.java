package com.lyp.likou.lk;

import com.lyp.likou.lk.po.ListNode;
import com.lyp.likou.lk.utils.LinkUtils;

/**
 * 83. 删除排序链表中的重复元素
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *
 * 提示：
 * * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 */
public class A00083_删除排序链表中的重复元素 {

    /**
     * 使用双指针，第一个指针指向上一个不同数的末尾，第二个指针指向下一个数的末尾
     *
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates(ListNode head) {
        ListNode first = head, second = head;
        while (second != null) {
            if (first.val == second.val) {
                second = second.next;
                //这里判断是否null,解决默认都是相同数，比如  5，5，5，5 时,无法去除的问题
                if (second == null) {
                    first.next = second;
                }
            } else {
                first.next = second;
                first = second;
            }
        }
        return head;
    }

    public static ListNode deleteDuplicates2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode cur = head;
        //判断条件是，是cur的下一个元素
        while (cur.next != null) {
            if (cur.val == cur.next.val) {
                //每次发现一个相同的数，就立刻删除
                cur.next = cur.next.next;
            } else {
                cur = cur.next;
            }
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode node1 = LinkUtils.buildList(1, 1, 2, 2, 2, 3, 4, 4, 5, 5);
        deleteDuplicates(node1);
        LinkUtils.printListNode(node1);

        ListNode node2 = LinkUtils.buildList(1, 2, 2, 3, 4, 4, 5, 6, 6, 6, 7);
        deleteDuplicates(node2);
        LinkUtils.printListNode(node2);

    }
}
