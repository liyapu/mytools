package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 14:24
 *
 * 203. 移除链表元素
 * 给你一个链表的头节点 head 和一个整数 val ，请你删除链表中所有满足 Node.val == val 的节点，并返回 新的头节点 。
 *
 *
 * 示例 1：
 * 输入：head = [1,2,6,3,4,5,6], val = 6
 * 输出：[1,2,3,4,5]
 *
 * 示例 2：
 * 输入：head = [], val = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [7,7,7,7], val = 7
 * 输出：[]
 *
 *
 * 提示：
 *
 * 列表中的节点在范围 [0, 104] 内
 * 1 <= Node.val <= 50
 * 0 <= k <= 50
 */
public class L_203 {

    public static ListNode removeElements(ListNode head, int val) {
        if(null == head){
            return null;
        }

        ListNode cur = head;
        while (cur.next != null){
            if(val == cur.next.val){
                //此处移动，就是删除效果
                cur.next = cur.next.next;
            }else{
                // if 不相等，就把 cur 移动到此节点上
                cur = cur.next;
            }
        }

        // 头结点可能和待删除的 val 相等，需要判断一下
        if(head.val == val){
            return head.next;
        }else{
            return head;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1,2,6,3,4,5,6);
        ListNode l1Result = removeElements(l1, 6);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(7,7,7,7);
        ListNode l2Result = removeElements(l2, 7);
        LinkUtils.printListNode(l2Result);

    }
}
