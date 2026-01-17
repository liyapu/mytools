package com.lyp.likou.lk;

import com.lyp.likou.lk.po.ListNode;
import com.lyp.likou.lk.utils.LinkUtils;

/**
 * 876. 链表的中间结点
 * 给你单链表的头结点 head ，请你找出并返回链表的中间结点。
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[3,4,5]
 * 解释：链表只有一个中间结点，值为 3 。
 *
 * 示例 2：
 *
 *
 * 输入：head = [1,2,3,4,5,6]
 * 输出：[4,5,6]
 * 解释：该链表有两个中间结点，值分别为 3 和 4 ，返回第二个结点。
 *
 *
 * 提示：
 * 链表的结点数范围是 [1, 100]
 * 1 <= Node.val <= 100
 */
public class A00876_链表的中间结点 {

    /**
     * 快慢指针
     *
     * @param head
     * @return
     */
    public static ListNode middleNode(ListNode head) {
        ListNode slow = head, fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }

    public static void main(String[] args) {
        ListNode node1 = LinkUtils.buildList(1, 2, 3, 4, 5);
        LinkUtils.printListNode(middleNode(node1));

        ListNode node2 = LinkUtils.buildList(1, 2, 3, 4, 5, 6);
        LinkUtils.printListNode(middleNode(node2));
    }


}

/**
 * 相似题型参考 快慢指针
 * A00876_链表的中间结点
 * A00141_环形链表
 * A00142_环形链表2
 * A00143_重排链表  A00021_合并两个有序链表
 *
 */
