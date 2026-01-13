package com.lyp.likou.lk;

/**
 * 21. 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *
 *
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class A00021_合并两个有序链表 {
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

    /**
     * 方法二：迭代
     * 思路
     * 我们可以用迭代的方法来实现上述算法。当 l1 和 l2 都不是空链表时，判断 l1 和 l2 哪一个链表的头节点的值更小，将较小值的节点添加到结果里，
     * 当一个节点被添加到结果里之后，将对应链表中的节点向后移一位。
     *
     * 算法
     * 首先，我们设定一个哨兵节点 prehead ，这可以在最后让我们比较容易地返回合并后的链表。
     * 我们维护一个 prev 指针，我们需要做的是调整它的 next 指针。然后，我们重复以下过程，直到 l1 或者 l2 指向了 null ：
     * 如果 l1 当前节点的值小于等于 l2 ，我们就把 l1 当前的节点接在 prev 节点的后面同时将 l1 指针往后移一位。
     * 否则，我们对 l2 做同样的操作。不管我们将哪一个元素接在了后面，我们都需要把 prev 向后移一位。
     *
     * 在循环终止的时候， l1 和 l2 至多有一个是非空的。由于输入的两个链表都是有序的，
     * 所以不管哪个链表是非空的，它包含的所有元素都比前面已经合并链表中的所有元素都要大。
     * 这意味着我们只需要简单地将非空链表接在合并链表的后面，并返回合并链表即可。
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/merge-two-sorted-lists/solutions/226408/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param list1
     * @param list2
     * @return
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        }
        if (list2 == null) {
            return list1;
        }
        // 声明一个哑结点，记录首结点，防止丢失
        ListNode dummyNode = new ListNode();
        ListNode tail = dummyNode;

        while (list1 != null && list2 != null) {
            if (list1.val <= list2.val) {
                tail.next = list1;
                list1 = list1.next;
            } else {
                tail.next = list2;
                list2 = list2.next;
            }
            //次序移动tail
            tail = tail.next;
        }
        tail.next = list1 == null ? list2 : list1;
        return dummyNode.next;
    }

    /**
     * 递归
     *
     * 如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
     * 否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，
     * 然后递归地决定下一个添加到结果里的节点。如果两个链表有一个为空，递归结束。
     *
     */
    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if(list1 == null){
            return list2;
        }else if(list2 == null){
            return list1;
        }else if (list1.val < list2.val){
              list1.next =  mergeTwoLists1(list1.next,list2);
              return list1;
        }else{
            list2.next = mergeTwoLists1(list1,list2.next);
            return list2;
        }
    }

        public static void main(String[] args) {
        ListNode N13 = new ListNode(4,null);
        ListNode N12 = new ListNode(2,N13);
        ListNode N11 = new ListNode(1,N12);

        ListNode N23 = new ListNode(4,null);
        ListNode N22 = new ListNode(3,N23);
        ListNode N21 = new ListNode(1,N22);

        ListNode n  = mergeTwoLists(N11,N21);
        while (n != null){
            System.out.println(n.val);
            n = n.next;
        }
    }
}
