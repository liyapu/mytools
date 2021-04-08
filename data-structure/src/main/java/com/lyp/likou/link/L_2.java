package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-08 13:55
 *
 * 2. 两数相加
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 *
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 *
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 * 示例 2：
 *
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 * 示例 3：
 *
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
public class L_2 {

    /**
     * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
     *
     * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
     *
     * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。具体而言，如果当前两个链表处相应位置的数字为 n1,n2n1,n2，进位值为 \textit{carry}carry，则它们的和为 n1+n2+\textit{carry}n1+n2+carry；其中，答案链表处相应位置的数字为 (n1+n2+\textit{carry}) \bmod 10(n1+n2+carry)mod10，而新的进位值为 \lfloor\frac{n1+n2+\textit{carry}}{10}\rfloor⌊
     * 10
     * n1+n2+carry
     * ​
     *  ⌋。
     *
     * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 00 。
     *
     * 此外，如果链表遍历结束后，有 \textit{carry} > 0carry>0，还需要在答案链表的后面附加一个节点，节点的值为 \textit{carry}carry。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param l1
     * @param l2
     * @return
     *
     * 参考 445. 两数相加 II
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Integer p1 = 0;
        Integer p2 = 0;
        Integer p3 = 0;

        ListNode dummyNode = new ListNode();
        ListNode tailNode = dummyNode;
        ListNode r = null;
        //表示进位的数
        int carry = 0;
        // /任何一个有数，或者有进位  就继续
        while (l1 != null || l2 != null || carry > 0) {
            p1 = l1 == null ? 0 : l1.val;
            p2 = l2 == null ? 0 : l2.val;
            p3 = p1 + p2 + carry;
            //重新计算进位值,(要先计算，然后下面重新计算新值会改变 p3)
            carry = p3 / 10;
            //重新计算新值
            p3 %= 10;


            r = new ListNode(p3);
            tailNode.next = r;
            tailNode = r;

            l1 = l1 == null ? null : l1.next;
            l2 = l2 == null ? null : l2.next;
        }
        return dummyNode.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(2, 4, 3);
        ListNode l2 = LinkUtils.buildList(5, 6, 4);

        ListNode l12 = addTwoNumbers(l1, l2);
        LinkUtils.printListNode(l12);


        ListNode l3 = LinkUtils.buildList(0);
        ListNode l4 = LinkUtils.buildList(0);

        ListNode l34 = addTwoNumbers(l3, l4);
        LinkUtils.printListNode(l34);


        ListNode l5 = LinkUtils.buildList(9,9,9,9,9,9,9);
        ListNode l6 = LinkUtils.buildList(9,9,9,9);

        ListNode l56 = addTwoNumbers(l5, l6);
        LinkUtils.printListNode(l56);
    }

}
