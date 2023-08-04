package com.lyp.likou.link;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;

/**
 * @author: liyapu
 * @description:
 * @date 2021-04-01 09:43
 */
public class LinkUtils {

    /**
     * 构建指定数字的链表
     *
     * @param arrs 可变参数
     * @return 链表头结点
     */
    public static ListNode buildList(Integer... arrs) {
        if (ArrayUtils.isEmpty(arrs)) {
            return null;
        }
        ListNode head = new ListNode(-1);
        ListNode pre = head;
        for (Integer a : arrs) {
            ListNode tempNode = new ListNode(a);
            pre.next = tempNode;
            pre = tempNode;
        }
        return head.next;
    }


    /**
     *
     * @param pos 从0开始算
     * @param arrs
     * @return
     */
    public static ListNode buildCycleList(Integer pos,Integer ... arrs){
        if(null == arrs){
            return null;
        }
        if(pos < 0 || pos >= arrs.length){
            return buildList(arrs);
        }

        ListNode head = new ListNode(-1);
        ListNode pre = head;

        ListNode spcecialNode = null;
        for (int i = 0; i < arrs.length; i++) {
            ListNode tempNode = new ListNode(arrs[i]);
            pre.next = tempNode;
            pre = tempNode;

            if(pos == i){
                spcecialNode = tempNode;
            }
        }

        pre.next =  spcecialNode;
        return head.next;
    }

    public static void joinList(ListNode pre,ListNode head){
        ListNode cur = pre;
        while (cur.next != null){
            cur = cur.next;
        }
        cur.next = head;
    }


    public static void printListNode(ListNode head) {
        ListNode temp = head;
        while (Objects.nonNull(temp)) {
//            System.out.print(temp.val+"\t");

            System.out.print(temp.val);
            temp = temp.next;
            if (Objects.nonNull(temp)) {
                System.out.print(" --> ");
            }
        }
        System.out.println();
    }
}
