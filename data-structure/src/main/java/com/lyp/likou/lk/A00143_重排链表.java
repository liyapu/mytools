package com.lyp.likou.lk;

import com.lyp.likou.lk.po.ListNode;
import com.lyp.likou.lk.utils.LinkUtils;

/**
 * 143. 重排链表
 *
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 *
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 *
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *
 *
 * 提示：
 * 链表的长度范围为 [1, 5 * 104]
 * 1 <= node.val <= 1000
 */
public class A00143_重排链表 {

    /**
     * 1. 先找到中间节点  A00876_链表的中间结点
     * 2. 原地反转       A00141_环形链表
     * 3. 链表拼接
     *
     *
     * 方法二：寻找链表中点 + 链表逆序 + 合并链表
     * 注意到目标链表即为将原链表的左半端和反转后的右半端合并后的结果。
     * 这样我们的任务即可划分为三步：
     * 找到原链表的中点（参考「876. 链表的中间结点」）。
     * 我们可以使用快慢指针来 O(N) 地找到链表的中间节点。
     * 将原链表的右半端反转（参考「206. 反转链表」）。
     *
     * 我们可以使用迭代法实现链表的反转。
     * 将原链表的两端合并。
     * 因为两链表长度相差不超过 1，因此直接合并即可。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/reorder-list/solutions/452867/zhong-pai-lian-biao-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     */
    public static void reorderList(ListNode head) {
        ListNode slow = head, fast = head;
        //找中间结点的前一个结点
        // 奇数  1->2->3->4->5    ,则 slow 指向 3
        // 偶数  1->2->3->4->5->6 ,则 slow 指向 3
        while (fast != null && fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        //原地反转
        ListNode prev = null, cur = slow.next, next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        //原链表断开
        slow.next = null;


        //链表拼接
        // l1
        ListNode l1 = head;
        //l2链表
        ListNode l2 = prev;
        while (l1 != null && l2 != null) {
            ListNode l1next = l1.next;
            ListNode l2next = l2.next;

            l1.next = l2;
            l1 = l1next;

            l2.next = l1;
            l2 = l2next;
        }
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4, 5);
        reorderList(l1);
        LinkUtils.printListNode(l1);

    }
}
