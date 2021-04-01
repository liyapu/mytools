package com.lyp.likou.link;


import java.util.Objects;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-31 17:41
 *
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 *
 *
 * 示例 1：
 *
 *
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 *
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 *
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 *
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class L_21 {

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     *     int val;
     *     ListNode next;
     *     ListNode() {}
     *     ListNode(int val) { this.val = val; }
     *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     * }
     */
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (Objects.isNull(l1)) {
            return l2;
        }
        if (Objects.isNull(l2)) {
            return l1;
        }

        // head 节点用于记录开始节点
        ListNode head = new ListNode(-1);
        // pre 指向head 节点，在循环里使用
        ListNode pre = head;

        while (Objects.nonNull(l1) && Objects.nonNull(l2)) {
            if (l1.val < l2.val) {
//                pre.next = new ListNode(l1.val);
                pre.next = l1;
                l1 = l1.next;
            } else {
//                pre.next = new ListNode(l2.val);
                pre.next = l2;
                l2 = l2.next;
            }
            pre = pre.next;
        }

        // 合并后 l1 和 l2 最多只有一个还未被合并完，我们直接将链表末尾指向未合并完的链表即可
        if (Objects.isNull(l1)) {
            pre.next = l2;
        }

        if (Objects.isNull(l2)) {
            pre.next = l1;
        }

        //此处返回的是 头结点的 next 节点
        return head.next;
    }


    /**
     * 如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
     * 否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。
     * 如果两个链表有一个为空，递归结束。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
        if(l1 == null){
            return l2;
        }
        if(l2 == null){
            return  l1;
        }
        if(l1.val < l2.val){
            l1.next = mergeTwoLists2(l1.next,l2);
            return l1;
        }else{
            l2.next = mergeTwoLists2(l1,l2.next);
            return l2;
        }
    }


        public static void main(String[] args) {
//        ListNode l1 = new ListNode(1);
//        ListNode l11 = new ListNode(2);
//        ListNode l12 = new ListNode(4);
//        l1.next = l11;
//        l11.next = l12;



//        ListNode l2 = new ListNode(1);
//        ListNode l21 = new ListNode(3);
//        ListNode l22 = new ListNode(4);
//        l2.next = l21;
//        l21.next = l22;

            ListNode l1 = LinkUtils.buildList(1,2,4);
            ListNode l2 = LinkUtils.buildList(1,3,4);

        // 每个测试方法，单个的执行，有些计算方式，已经改变了 l1,l2 内部的节点值了
        ListNode head1 = mergeTwoLists(l1, l2);
        LinkUtils.printListNode(head1);

//        ListNode head2 = mergeTwoLists2(l1,l2);
//        LinkUtils.printListNode(head2);

    }


}
