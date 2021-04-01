package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 17:41
 * 876. 链表的中间结点
 * 给定一个头结点为 head 的非空单链表，返回链表的中间结点。
 *
 * 如果有两个中间结点，则返回第二个中间结点。
 *
 *
 *
 * 示例 1：
 * 输入：[1,2,3,4,5]
 * 输出：此列表中的结点 3 (序列化形式：[3,4,5])
 * 返回的结点值为 3 。 (测评系统对该结点序列化表述是 [3,4,5])。
 * 注意，我们返回了一个 ListNode 类型的对象 ans，这样：
 * ans.val = 3, ans.next.val = 4, ans.next.next.val = 5, 以及 ans.next.next.next = NULL.
 *
 * 示例 2：
 * 输入：[1,2,3,4,5,6]
 * 输出：此列表中的结点 4 (序列化形式：[4,5,6])
 * 由于该列表有两个中间结点，值分别为 3 和 4，我们返回第二个结点。
 *
 *
 * 提示：
 *
 * 给定链表的结点数介于 1 和 100 之间。
 */
public class L_876 {
    public static ListNode middleNode(ListNode head) {
        ListNode first = head;
        ListNode second = head;
        while (second != null && second.next != null){
            first = first.next;
            second = second.next.next;
        }
        return first;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1,2,3,4,5);
        ListNode l1Result = middleNode(l1);
        System.out.println(l1Result.val);

        ListNode l2 = LinkUtils.buildList(1,2,3,4,5,6);
        ListNode l2Result = middleNode(l2);
        System.out.println(l2Result.val);
    }
}
