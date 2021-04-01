package com.lyp.likou.link;

import java.util.HashSet;
import java.util.Set;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 13:37
 *
 * 160. 相交链表
 * 编写一个程序，找到两个单链表相交的起始节点。
 *
 * 如下面的两个链表：
 *
 *
 *
 * 在节点 c1 开始相交。
 *
 *
 *
 * 示例 1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个链表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 *
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 *
 * 注意：
 *
 * 如果两个链表没有交点，返回 null.
 * 在返回结果后，两个链表仍须保持原有的结构。
 * 可假定整个链表结构中没有循环。
 * 程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 *
 */
public class L_160 {

    /**
     * 哈希表法
     * 遍历链表 A 并将每个结点的地址/引用存储在哈希表中。然后检查链表 B 中的每一个结点 bi 是否在哈希表中。
     * 若在，则 bi为相交结点。
     *
     * 复杂度分析
     * 时间复杂度 : O(m+n)
     * 空间复杂度 : O(m) 或 O(n)
     *
     * @param headA
     * @param headB
     * @return
     */
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (null == headA) {
            return null;
        }
        if (null == headB) {
            return null;
        }
        Set<ListNode> seenSet = new HashSet<>();
        while (headA != null) {
            seenSet.add(headA);
            headA = headA.next;
        }

        while (headB != null) {
            if (seenSet.contains(headB)) {
                return headB;
            }
            headB = headB.next;
        }
        return null;
    }

    /**
     * pA走过的路径为A链+B链
     * pB走过的路径为B链+A链
     *
     * pA和pB走过的长度都相同，都是A链和B链的长度之和，相当于将两条链从尾端对齐，
     * 如果相交，则会提前在相交点相遇，
     * 如果没有相交点，则会在最后相遇。
     *
     * 双指针法
     * 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点 (你没看错，就是链表 B); 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 若在某一时刻 pApA 和 pBpB 相遇，则 pApA/pBpB 为相交结点。
     * 如果两个链表存在相交，它们末尾的结点必然相同。因此当 pApA/pBpB 到达链表结尾时，记录下链表 A/B 对应的元素。若最后元素不相同，则两个链表不相交。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static ListNode getIntersectionNode2(ListNode headA, ListNode headB) {
        if(null == headA || null == headB){
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;
        while (pA != pB){
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }
        return pA;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(4, 1);
        ListNode l2 = LinkUtils.buildList(5, 0, 1);
        ListNode l12 = LinkUtils.buildList(8, 4, 5);
        LinkUtils.joinList(l1,l12);
        LinkUtils.joinList(l2,l12);

        LinkUtils.printListNode(l1);
        LinkUtils.printListNode(l2);


//        ListNode l1Result = getIntersectionNode(l1, l2);
//        System.out.println(l1Result == null ? "null" : l1Result.getVal());


        ListNode l2Result = getIntersectionNode2(l1, l2);
        System.out.println(l2Result == null ? "null" : l2Result.getVal());

//        Set<Integer> set = new HashSet<>();
//        System.out.println(set.add(1));
//        System.out.println(set.add(2));
//        System.out.println(set.add(null));
//        System.out.println(set.add(null));
//        System.out.println(set.add(1));
    }

}
