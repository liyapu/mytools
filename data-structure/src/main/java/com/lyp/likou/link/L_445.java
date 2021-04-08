package com.lyp.likou.link;

import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-08 13:25
 *
 * 445. 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。将这两数相加会返回一个新的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头。
 *
 *
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class L_445 {
    /**
     *
     * 本题的主要难点在于链表中数位的顺序与我们做加法的顺序是相反的，
     * 为了逆序处理所有数位，我们可以使用栈：把所有数字压入栈中，再依次取出相加。计算过程中需要注意进位的情况。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-two-numbers-ii/solution/liang-shu-xiang-jia-ii-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 参考 2. 两数相加
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode dummy = new ListNode(-1);
        Boolean hasOverBit = false;
        Integer p1 = 0;
        Integer p2 = 0;
        Integer p3 = 0;
        ListNode r;
        while (s1.size() != 0 && s2.size() != 0) {
            p1 = s1.pop();
            p2 = s2.pop();

            p3 = p1 + p2;
            if (hasOverBit) {
                p3++;
            }
            if (p3 >= 10) {
                p3 = p3 - 10;
                hasOverBit = true;
            } else {
                hasOverBit = false;
            }

            r = new ListNode(p3);
            r.next = dummy.next;
            dummy.next = r;
        }

        if (s1.size() == 0) {
            while (s2.size() != 0) {
                p2 = s2.pop();
                if (hasOverBit) {
                    p2++;
                }
                if (p2 >= 10) {
                    p2 = p2 - 10;
                    hasOverBit = true;
                } else {
                    hasOverBit = false;
                }

                r = new ListNode(p2);
                r.next = dummy.next;
                dummy.next = r;
            }
        }
        if (s2.size() == 0) {
            while (s1.size() != 0) {
                p1 = s1.pop();
                if (hasOverBit) {
                    p1++;
                }
                if (p1 >= 10) {
                    p1 = p1 - 10;
                    hasOverBit = true;
                } else {
                    hasOverBit = false;
                }

                r = new ListNode(p1);
                r.next = dummy.next;
                dummy.next = r;
            }
        }

        if (hasOverBit) {
            r = new ListNode(1);
            r.next = dummy.next;
            dummy.next = r;
        }

        return dummy.next;
    }

    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<>();
        Stack<Integer> s2 = new Stack<>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        ListNode dummy = new ListNode(-1);
        Integer p1 = 0;
        Integer p2 = 0;
        Integer p3 = 0;
        int carry = 0;
        ListNode r;
        while (!s1.isEmpty() || !s2.isEmpty() || carry > 0){
            p1 = s1.isEmpty() ? 0 : s1.pop();
            p2 = s2.isEmpty() ? 0 : s2.pop();
            p3 = p1 + p2 + carry;

            //重新计算进位值,(要先计算，然后下面重新计算新值会改变 p3)
            carry = p3/ 10;
            //重新计算新值
            p3 %= 10;

            r =new ListNode(p3);
            r.next = dummy.next;
            dummy.next = r;
        }


        return dummy.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(7, 2, 4, 3);
        ListNode l2 = LinkUtils.buildList(5, 6, 4);

        ListNode l12 = addTwoNumbers(l1, l2);
        LinkUtils.printListNode(l12);

    }
}
