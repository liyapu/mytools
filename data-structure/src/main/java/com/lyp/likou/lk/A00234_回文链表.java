package com.lyp.likou.lk;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * 234. 回文链表
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *
 *
 * 提示：
 * 链表中节点数目在范围[1, 105] 内
 * 0 <= Node.val <= 9
 */
public class A00234_回文链表 {


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

    public static boolean isPalindrome(ListNode head) {
        if (head == null) {
            return false;
        }
        if (head.next == null) {
            return true;
        }
        List<Integer> intList = new ArrayList<>();
        ListNode current = head;
        while (current != null) {
            intList.add(current.val);
            current = current.next;
        }
        int length = intList.size();
        int left = 0, right = length - 1;
        while (left < right) {
            if (Objects.equals(intList.get(left), intList.get(right))) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }


}
