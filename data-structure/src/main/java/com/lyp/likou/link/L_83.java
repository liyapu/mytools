package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 09:43
 * 83. 删除排序链表中的重复元素
 * 存在一个按升序排列的链表，给你这个链表的头节点 head ，请你删除所有重复的元素，使每个元素 只出现一次 。
 *
 * 返回同样按升序排列的结果链表。
 *
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 */
public class L_83 {

    public static ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode headMark = head;

        ListNode curr = head;
        ListNode next = head.next;
        while (curr != null) {
            if (null == next) {
                break;
            }
            while (curr.val == next.val) {
                next = next.next;
                if (null == next) {
                    break;
                }
            }
            curr.next = next;
            curr = next;
            if (null != next) {
                next = next.next;
            }

        }
        return headMark;
    }

    /**
     * 思路与算法
     *
     * 由于给定的链表是排好序的，因此重复的元素在链表中出现的位置是连续的，因此我们只需要对链表进行一次遍历，就可以删除重复的元素。
     *
     * 具体地，我们从指针 cur 指向链表的头节点，随后开始对链表进行遍历。如果当前 cur 与 cur.next 对应的元素相同，
     * 那么我们就将 cur.next 从链表中移除；否则说明链表中已经不存在其它与cur 对应的元素相同的节点，
     * 因此可以将 cur 指向 cur.next。
     *
     * 当遍历完整个链表之后，我们返回链表的头节点即可。
     *
     * 细节
     *
     * 当我们遍历到链表的最后一个节点时，cur.next 为空节点，如果不加以判断，访问 cur.next 对应的元素会产生运行错误。
     * 因此我们只需要遍历到链表的最后一个节点，而不需要遍历完整个链表。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/solution/shan-chu-pai-xu-lian-biao-zhong-de-zhong-49v5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static ListNode deleteDuplicates2(ListNode head) {
       if(null == head){
           return null;
       }
       ListNode cur = head;
       while (cur.next != null){
           if(cur.val == cur.next.val){
               //此处移动，就是删除效果
               cur.next = cur.next.next;
           }else{
               // if 不相等，就把 cur 移动到此节点上
               cur = cur.next;
           }
       }
       return head;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1,1, 2);
//        LinkUtils.printListNode(l1);
        ListNode l1Result = deleteDuplicates(l1);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(1, 1, 2, 3, 3);
//        LinkUtils.printListNode(l2);
        ListNode l2Result = deleteDuplicates(l2);
        LinkUtils.printListNode(l2Result);

        ListNode l3 = LinkUtils.buildList(1,1, 2);
        ListNode l3Result = deleteDuplicates2(l3);
        LinkUtils.printListNode(l3Result);

        ListNode l4 = LinkUtils.buildList(1, 1, 2, 3, 3);
        ListNode l4Result = deleteDuplicates2(l4);
        LinkUtils.printListNode(l4Result);


    }
}
