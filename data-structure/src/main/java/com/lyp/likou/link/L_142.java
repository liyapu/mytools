package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-07 17:44
 *
 * 142. 环形链表 II
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。注意，pos 仅仅是用于标识环的情况，并不会作为参数传递到函数中。
 *
 * 说明：不允许修改给定的链表。
 *
 * 进阶：
 *
 * 你是否可以使用 O(1) 空间解决此题？
 *
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *
 *
 * 提示：
 *
 * 链表中节点的数目范围在范围 [0, 104] 内
 * -105 <= Node.val <= 105
 * pos 的值为 -1 或者链表中的一个有效索引
 */
public class L_142 {
    public static ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) {
            return null;
        }

        ListNode first = head;
        ListNode second = head;
        Boolean hasCycle = false;

        while (second != null && second.next != null) {
            first = first.next;
            second = second.next.next;
            if(first == second){
                hasCycle = true;
                //记得 结束，否则死循环
                break;
            }
        }

        if(!hasCycle){
            return null;
        }

        ListNode three = head;
        while (first != three){
            first = first.next;
            three = three.next;
        }
        return three;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildCycleList(1, 3, 2, 0, -4);
        ListNode l1Result = detectCycle(l1);
        System.out.println(l1Result == null ? "没有环" : l1Result.val);
    }
}
