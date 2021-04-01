package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 14:50
 *
 * 206. 反转链表
 * 反转一个单链表。
 *
 * 示例:
 *
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 * 进阶:
 * 你可以迭代或递归地反转链表。你能否用两种方法解决这道题？
 */
public class L_206 {

    /**
     * 遍历一遍列表，并且 新建了节点，空间复杂度高
     * @param head
     * @return
     */
    public static ListNode reverseList(ListNode head) {
        if(null == head){
            return null;
        }

        ListNode res = new ListNode(-1);
        ListNode cur = res;
        while (null != head){
            ListNode temp = new ListNode(head.val);
            //类似于 头插法
            temp.next = cur.next;
            cur.next = temp;

            head = head.next;
        }
        return res.next;
    }

    /**
     * 假设链表为 1→2→3→∅，我们想要把它改成  ∅←1←2←3。
     *
     * 在遍历链表时，将当前节点的 next 指针改为指向前一个节点。由于节点没有引用其前一个节点，因此必须事先存储其前一个节点。
     * 在更改引用之前，还需要存储后一个节点。最后返回新的头引用
     *
     *       1    2      3     4    5
     * pre  cur  next
     *      pre  cur    next
     *      用三个指针，记录三个值，然后顺序下推
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list/solution/fan-zhuan-lian-biao-by-leetcode-solution-d1k2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static ListNode reverseList2(ListNode head) {
       ListNode pre = null;
       ListNode cur = head;
       while (cur != null){
           ListNode next = cur.next;
           //修改指针方向
           cur.next = pre;
           //后退
           pre = cur;
           cur = next;
       }
       return pre;
    }

    /**
     * 1 → 2
     * 作为list 输入，按照这个进行写程序
     * @param head
     * @return
     */
    public static ListNode reverseList3(ListNode head) {
        // 因为下面有 head.next.next ，这里需要判断 head.next 是否为null
       if(null == head || null == head.next){
           return head;
       }

       ListNode newHead = reverseList3(head.next);
       // head 的下一个节点的 next 是 head
       head.next.next = head;
       //断掉 head.next 的指针，否则有环
       head.next = null;
       return newHead;
    }

    public ListNode reverseList4(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 想象递归已经层层返回，到了最后一步
        // 以链表 1->2->3->4->5 为例，现在链表变成了 5->4->3->2->null，1->2->null（是一个链表，不是两个链表）
        // 此时 newHead是5，head是1
        ListNode newHead = reverseList(head.next);
        // 最后的操作是把链表 1->2->null 变成 2->1->null
        // head是1，head.next是2，head.next.next = head 就是2指向1，此时链表为 2->1->2
        head.next.next = head;
        // 防止链表循环，1指向null，此时链表为 2->1->null，整个链表为 5->4->3->2->1->null
        head.next = null;
        return newHead;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1,2,3,4,5);
        ListNode l1Result = reverseList(l1);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(1,2,3,4,5);
        ListNode l2Result = reverseList2(l2);
        LinkUtils.printListNode(l2Result);

        ListNode l3 = LinkUtils.buildList(1,2,3,4,5);
        ListNode l3Result = reverseList3(l3);
        LinkUtils.printListNode(l3Result);
    }
}
