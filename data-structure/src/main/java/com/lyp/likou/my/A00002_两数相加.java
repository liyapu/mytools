package com.lyp.likou.my;

import java.util.Stack;

/**
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 *
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 *
 * 提示：
 *
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 */
public class A00002_两数相加 {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        //新建哑结点，记录头结点位置
        ListNode dumpNode = new ListNode();
        ListNode tail = dumpNode;
        //是否需要加1
        boolean addOne = false;

        while (l1 != null && l2 != null) {
            int sum = l1.val + l2.val;
            if (addOne) {
                sum++;
                addOne = false;
            }
            if (sum >= 10) {
                addOne = true;
                sum = sum - 10;
            }
            ListNode temp = new ListNode(sum, null);
            tail.next = temp;
            tail = tail.next;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l1 == null) {
            while (l2 != null) {
                int sum = l2.val;
                if (addOne) {
                    sum++;
                    addOne = false;
                }
                if (sum >= 10) {
                    addOne = true;
                    sum = sum - 10;
                }
                ListNode temp = new ListNode(sum, null);
                tail.next = temp;
                tail = tail.next;

                l2 = l2.next;
            }
        }
        if (l2 == null) {
            while (l1 != null) {
                int sum = l1.val;
                if (addOne) {
                    sum++;
                    addOne = false;
                }
                if (sum >= 10) {
                    addOne = true;
                    sum = sum - 10;
                }
                ListNode temp = new ListNode(sum, null);
                tail.next = temp;
                tail = tail.next;

                l1 = l1.next;
            }
        }
        if (addOne) {
            ListNode temp = new ListNode(1, null);
            tail.next = temp;
            tail = tail.next;
        }
        return dumpNode.next;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        //新建哑结点，记录头结点位置
        ListNode dumpNode = new ListNode();
        ListNode tail = dumpNode;

        //表示进位的数,0 或者 1
        int carry = 0;

        //任何一个有数，就继续
        // carry > 0 应对 两个列表最后同时为null 但是还有进位的情况
        // 有一个不是空节点，或者还有进位，就继续迭代
        while (l1 != null || l2 != null || carry > 0) {
            int num1 = l1 == null ? 0 : l1.val;
            int num2 = l2 == null ? 0 : l2.val;
            int sum = num1 + num2 + carry;
            carry = sum / 10;
            ListNode temp = new ListNode(sum % 10);
            tail.next = temp;
            tail = tail.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dumpNode.next;
    }


}
