package com.lyp.likou.pk01;

import com.lyp.learn.likou.pk01.ListNode;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
public class LinkTwoNumbers {

    public static com.lyp.learn.likou.pk01.ListNode addTwoNumbers(com.lyp.learn.likou.pk01.ListNode l1, com.lyp.learn.likou.pk01.ListNode l2) {
        //将当前结点初始化为返回列表的哑结点
        com.lyp.learn.likou.pk01.ListNode head = new com.lyp.learn.likou.pk01.ListNode(0);
        com.lyp.learn.likou.pk01.ListNode curr = head;
        //将 node1 和 node2 分别初始化为列表 l1 和 l2 的头部
        com.lyp.learn.likou.pk01.ListNode node1 = l1;
        com.lyp.learn.likou.pk01.ListNode node2 = l2;
        //将进位 carry 初始化为 0,进位 carry必定是 0或 1
        int carry = 0;

        //任何一个有数，就继续
        while(node1 != null || node2 != null){
            //将 num1 设为结点 node1 的值。如果node1已经到达l1的末尾，则将其值设置为0
            int num1 = (node1 == null) ? 0 : node1.val;
            int num2 = (node2 == null) ? 0 : node2.val;
            int sum = num1 + num2 + carry;
            //更新进位的值
            carry = sum / 10;
            //创建一个数值为 (sum \mod 10) 的新结点，
            // 并将其设置为当前结点的下一个结点，
            // 然后将当前结点前进到下一个
            com.lyp.learn.likou.pk01.ListNode newNode = new com.lyp.learn.likou.pk01.ListNode(sum % 10);
            curr.next = newNode;
            curr = newNode;

            if(node1 != null){
                node1 = node1.next;
            }
            if(node2 != null){
                node2 = node2.next;
            }
        }

        //检查 carry = 1是否成立，如果成立，则向返回列表追加一个含有数字 11 的新结点
        if(carry > 0){
            curr.next = new com.lyp.learn.likou.pk01.ListNode(carry);
        }
        //返回哑结点的下一个结点
        return head.next;
    }

    public static void main(String[] args) {
        com.lyp.learn.likou.pk01.ListNode l1 = new com.lyp.learn.likou.pk01.ListNode(2);
        com.lyp.learn.likou.pk01.ListNode l11 = new com.lyp.learn.likou.pk01.ListNode(4);
        com.lyp.learn.likou.pk01.ListNode l111 = new com.lyp.learn.likou.pk01.ListNode(3);
        l1.next = l11;
        l11.next = l111;

        com.lyp.learn.likou.pk01.ListNode l2 = new com.lyp.learn.likou.pk01.ListNode(5);
        com.lyp.learn.likou.pk01.ListNode l22 = new com.lyp.learn.likou.pk01.ListNode(6);
        com.lyp.learn.likou.pk01.ListNode l222 = new com.lyp.learn.likou.pk01.ListNode(4);
        l2.next = l22;
        l22.next = l222;

        ListNode result = addTwoNumbers(l1,l2);
        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }

    }
}
