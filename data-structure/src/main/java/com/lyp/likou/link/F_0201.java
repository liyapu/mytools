package com.lyp.likou.link;

import java.util.HashSet;
import java.util.Set;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 10:20
 *
 * 面试题 02.01. 移除重复节点
 * 编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。
 *
 * 示例1:
 *
 *  输入：[1, 2, 3, 3, 2, 1]
 *  输出：[1, 2, 3]
 * 示例2:
 *
 *  输入：[1, 1, 1, 1, 2]
 *  输出：[1, 2]
 * 提示：
 *
 * 链表长度在[0, 20000]范围内。
 * 链表元素在[0, 20000]范围内。
 * 进阶：
 *
 * 如果不得使用临时缓冲区，该怎么解决？
 */
public class F_0201 {

    public static ListNode removeDuplicateNodes(ListNode head) {
        if (null == head) {
            return null;
        }
        ListNode pre = head;
        ListNode cur = head.next;
        Set<Integer> occurred = new HashSet<>();
        //初始化第一个节点值
        occurred.add(pre.val);

        while (cur != null) {
            if (occurred.add(cur.val)) {
                //添加成功，表示以前没有
                // pre和cur都更新一下，跳到下一个
                // pre 的更新，是根据 pre自身自己进行更新的
                // 1 1 1 2
                pre = pre.next;
                cur = cur.next;
            } else {
                //添加失败，表示已经存在了
                // 让prev的next直接指向cur的next，完成删除
                // 1 1
                pre.next = cur.next;
                // 更新一下cur
                cur = cur.next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 1, 3, 5, 1, 2, 6, 7);
        ListNode l1Result = removeDuplicateNodes(l1);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(1, 2, 1, 1, 1);
        ListNode l2Result = removeDuplicateNodes(l2);
        LinkUtils.printListNode(l2Result);


    }
}
