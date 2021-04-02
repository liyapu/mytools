package com.lyp.likou.link;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-02 14:23
 *
 * 24. 两两交换链表中的节点
 * 给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。
 *
 * 你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *
 *
 * 提示：
 *
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 */
public class L_24 {

    /**
     * 直接上三部曲模版：
     * 1.找终止条件。
     *    什么情况下递归终止？
     *    没得交换的时候，递归就终止了呗。因此当链表只剩一个节点或者没有节点的时候，自然递归就终止了。
     * 2.找返回值。
     *   我们希望向上一级递归返回什么信息？由于我们的目的是两两交换链表中相邻的节点，
     *   因此自然希望交换给上一级递归的是已经完成交换处理，即已经处理好的链表。
     * 3.本级递归应该做什么。 结合第二步，看下图！
     *    由于只考虑本级递归，所以这个链表在我们眼里其实也就三个节点：head、head.next、已处理完的链表部分。
     *    而本级递归的任务也就是交换这3个节点中的前两个节点，就很easy了。
     *
     *    上一级    head   next  已经处理完的链表部分
     *    上一级    next   head  已经处理完的链表部分
     * @param head
     * @return
     *
     * 复杂度分析
     * 时间复杂度：O(n)O(n)，其中 nn 是链表的节点数量。需要对每个节点进行更新指针的操作。
     * 空间复杂度：O(n)O(n)，其中 nn 是链表的节点数量。空间复杂度主要取决于递归调用的栈空间。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode next = head.next;
        //swapPairs(next.next) 就是 已经处理完的链表部分
        head.next = swapPairs(next.next);
        next.next = head;
        return next;
    }

    /**
     * 哑结点 是不动的
     *
     * 也可以通过迭代的方式实现两两交换链表中的节点。
     *
     * 创建哑结点 dummyHead，令 dummyHead.next = head。令 temp 表示当前到达的节点，初始时 temp = dummyHead。每次需要交换 temp 后面的两个节点。
     * 如果 temp 的后面没有节点或者只有一个节点，则没有更多的节点需要交换，因此结束交换。否则，获得 temp 后面的两个节点 node1 和 node2，通过更新节点的指针关系实现两两交换节点。
     * 具体而言，交换之前的节点关系是 temp -> node1 -> node2，交换之后的节点关系要变成 temp -> node2 -> node1，因此需要进行如下操作。
     *
     * temp.next = node2
     * node1.next = node2.next
     * node2.next = node1
     *
     * 完成上述操作之后，节点关系即变成 temp -> node2 -> node1
     *
     * 再令 temp = node1，对链表中的其余节点进行两两交换，直到全部节点都被两两交换。
     * 两两交换链表中的节点之后，新的链表的头节点是 dummyHead.next，返回新的链表的头节点即可。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/swap-nodes-in-pairs/solution/liang-liang-jiao-huan-lian-biao-zhong-de-jie-di-91/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static ListNode swapPairs2(ListNode head) {
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;
        ListNode temp = dummyHead;

        while (temp.next != null && temp.next.next != null) {
            ListNode node1 = temp.next;
            ListNode node2 = temp.next.next;

            temp.next = node2;
            node1.next = node2.next;
            node2.next = node1;

            //重置 temp
            temp = node1;
        }
        return dummyHead.next;
    }

    public static void main(String[] args) {
        ListNode l1 = LinkUtils.buildList(1, 2, 3, 4);
        ListNode l1Result = swapPairs(l1);
        LinkUtils.printListNode(l1Result);

        ListNode l2 = LinkUtils.buildList(1, 2, 3, 4);
        ListNode l2Result = swapPairs2(l2);
        LinkUtils.printListNode(l2Result);
    }

}
