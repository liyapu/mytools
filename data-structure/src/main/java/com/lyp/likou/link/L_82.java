//package com.lyp.likou.link;
//
///**
// *@author: liyapu
// *@description:
// *@date 2021-04-06 09:10
// *
// * 82. 删除排序链表中的重复元素 II
// * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除链表中所有存在数字重复情况的节点，只保留原始链表中 没有重复出现 的数字。
// *
// * 返回同样按升序排列的结果链表。
// *
// *
// *
// * 示例 1：
// * 输入：head = [1,2,3,3,4,4,5]
// * 输出：[1,2,5]
// *
// * 示例 2：
// * 输入：head = [1,1,1,2,3]
// * 输出：[2,3]
// *
// *
// * 提示：
// *
// * 链表中节点数目在范围 [0, 300] 内
// * -100 <= Node.val <= 100
// * 题目数据保证链表已经按升序排列
// */
//public class L_82 {
//    public static ListNode deleteDuplicates(ListNode head) {
//        if(head == null || head.next == null){
//            return null;
//        }
//
//        ListNode dummyNode = new ListNode(-1);
//        dummyNode.next = head;
//
//        ListNode baseNode = dummyNode;
//
//        ListNode cur = dummyNode.next;
//        ListNode next = cur.next;
//        while (cur.next != null){
//            ListNode next = cur.next;
//
//            if(cur.val == next.val){
//                baseNode.next = null;
//            }else{
//                if(cur.next == next){
//                    cur = cur.next;
//                }else {
//                    cur = next;
//                }
//            }
//        }
//    }
//
//    public static void main(String[] args) {
//
//    }
//}
