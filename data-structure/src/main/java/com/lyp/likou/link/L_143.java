package com.lyp.likou.link;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-08 09:49
 */
public class L_143 {

    /**
     * 线性表
     * 因为链表不支持下标访问，所以我们无法随机访问链表中任意位置的元素。
     *
     * 因此比较容易想到的一个方法是，我们利用线性表存储该链表，
     * 然后利用线性表可以下标访问的特点，直接按顺序访问指定元素，重建该链表即可。
     *
     */
    public static void reorderList(ListNode head) {
        if (head == null) {
            return;
        }
        List<ListNode> list = new ArrayList<>();
        ListNode node = head;
        while (node != null) {
            list.add(node);
            node = node.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j) {
            list.get(i).next = list.get(j);
            i++;
            if (i == j) {
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }

    public static void reorderList1(ListNode head) {
        if (head == null || head.next == null) {
            return;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        int size = stack.size();
        int times = (stack.size() - 1) / 2;
        ListNode cur = head;
        ListNode next = null;
        for (int i = 0; i < times; i++) {
            next = cur.next;
            ListNode popNode = stack.pop();
            popNode.next = next;
            cur.next = popNode;

            cur = next;
        }
        if (size % 2 == 0) {
            if (next != null && next.next != null) {
                next.next.next = null;
            }
        } else {
            next.next = null;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2);
        reorderList(l1);
        LinkUtils.printListNode(l1);

        ListNode l2 = LinkUtils.buildList(1, 2, 3, 4, 5);
        reorderList(l2);
        LinkUtils.printListNode(l2);
    }
}
