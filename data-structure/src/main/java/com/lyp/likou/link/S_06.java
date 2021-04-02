package com.lyp.likou.link;

import java.util.Arrays;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 09:11
 *
 * 剑指 Offer 06. 从尾到头打印链表
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */
public class S_06 {
    /**
     * 栈
     * 栈的特点是后进先出，即最后压入栈的元素最先弹出。考虑到栈的这一特点，使用栈将链表元素顺序倒置。
     * 从链表的头节点开始，依次将每个节点压入栈内，然后依次弹出栈内的元素并存储到数组中。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/cong-wei-dao-tou-da-yin-lian-biao-lcof/solution/mian-shi-ti-06-cong-wei-dao-tou-da-yin-lian-biao-b/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static int[] reversePrint(ListNode head) {
        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        int size = stack.size();
        int[] arr = new int[size];

        for (int i = 0; i < size; i++) {
            arr[i] = stack.pop().val;
        }
        return arr;
    }


    /**
     * 遍历一遍 计算出链表长度，然后初始化数组
     * 再遍历一遍，倒序插入到数组中
     * @param head
     * @return
     */
    public static int[] reversePrint2(ListNode head) {
        ListNode temp = head;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        int[] arr = new int[size];
        while (head != null) {
            arr[--size] = head.val;
            head = head.next;
        }
        return arr;
    }


    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4, 5);
        int[] arr1 = reversePrint(l1);
        System.out.println(Arrays.toString(arr1));


        ListNode l2 = LinkUtils.buildList(2,4,6,8);
        int[] arr2 = reversePrint2(l2);
        System.out.println(Arrays.toString(arr2));

    }
}
