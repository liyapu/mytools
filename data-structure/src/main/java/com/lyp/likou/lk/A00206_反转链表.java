package com.lyp.likou.lk;

import com.lyp.likou.lk.po.ListNode;
import com.lyp.likou.lk.utils.LinkUtils;

/**
 * 206. 反转链表
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 */
public class A00206_反转链表 {

    public static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //pre 表示新链表的尾节点
        ListNode current = head, temp = null, pre = null;
        //假设列表为 1->2->3->4->5
        while (current != null) {
            //当前 current为1
            //temp 指向2，防止丢失
            temp = current.next;
            //当前current 1的next指针指向反转链表的尾部
            current.next = pre;
            //反转链表的尾部指向当前current元素
            pre = current;
            //当前原始current 指向 temp,即current的下一个元素
            current = temp;
        }
        return pre;
    }

    /**
     * 假设链表为 1→2→3→∅，我们想要把它改成  ∅←1←2←3。
     *
     * 在遍历链表时，将当前节点的 next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。
     * 在更改引用之前，还需要存储后一个节点。最后返回新的头引用
     *
     * 1    2      3     4    5
     * pre  cur  next
     * pre  cur    next
     * 用三个指针，记录三个值，然后顺序下推
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
        //pre 执行新的反转链表的头结点
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            //先记录cur的下一个结点，防止丢失
            ListNode next = cur.next;
            //当前结点的next 指向反转链表
            cur.next = pre;
            //反转链表头结点 后移
            pre = cur;
            //当前结点后移，进行下一个结点的操作
            cur = next;
        }
        return pre;
    }

    /**
     * 自身递归
     *
     * 1 → 2
     * 变成 1->2->1 成环 然后 变成 2->1
     * 作为list 输入，按照这个进行写程序
     *
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        // 因为下面有 head.next.next ，这里需要判断 head.next 是否为null
        if (null == head || null == head.next) {
            return head;
        }

        ListNode newHead = reverseList3(head.next);
        // head 的下一个节点的 next 是 head
        head.next.next = head;
        //断掉 head.next 的指针，否则有环
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4, 5);
        ListNode l1Result = reverseList(l1);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(1, 2, 3, 4, 5);
        ListNode l2Result = reverseList2(l2);
        LinkUtils.printListNode(l2Result);

        ListNode l3 = LinkUtils.buildList(1, 2, 3, 4, 5);
        ListNode l3Result = reverseList3(l3);
        LinkUtils.printListNode(l3Result);
    }
}


/**
 * 相似题型参考
 *
 * A00206_反转链表
 * A00092_反转链表2
 */
