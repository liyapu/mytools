package com.lyp.likou.lk;

/**
 * 19. 删除链表的倒数第 N 个结点
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 *
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class A00019_删除链表的倒数第N个结点 {
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

    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) {
            return head;
        }

        ListNode cur = head;
        int times = 0;
        while (cur != null) {
            times++;
            cur = cur.next;
        }
        if (n > times) {
            return null;
        }
        if (n == times) {
            return head.next;
        }
        cur = head;
        int tempTimes = 1;
        int finalTime = times - n;
        while (cur != null) {
            if (tempTimes == finalTime) {
                ListNode delTemp = cur.next;
                if (delTemp != null) {
                    ListNode delTempNext = delTemp.next;
                    cur.next = delTempNext;
                    delTemp.next = null;
                } else {
                    cur.next = null;
                }
                break;
            } else {
                cur = cur.next;
            }
            tempTimes++;
        }
        return head;
    }

    /**
     * 方法三：双指针
     * 思路与算法
     *
     * 我们也可以在不预处理出链表的长度，以及使用常数空间的前提下解决本题。
     *
     * 由于我们需要找到倒数第 n 个节点，因此我们可以使用两个指针 first 和 second 同时对链表进行遍历，并且 first 比 second 超前 n 个节点。当 first 遍历到链表的末尾时，second 就恰好处于倒数第 n 个节点。
     *
     * 具体地，初始时 first 和 second 均指向头节点。我们首先使用 first 对链表进行遍历，遍历的次数为 n。此时，first 和 second 之间间隔了 n−1 个节点，即 first 比 second 超前了 n 个节点。
     *
     * 在这之后，我们同时使用 first 和 second 对链表进行遍历。当 first 遍历到链表的末尾（即 first 为空指针）时，second 恰好指向倒数第 n 个节点。
     *
     * 根据方法一和方法二，如果我们能够得到的是倒数第 n 个节点的前驱节点而不是倒数第 n 个节点的话，删除操作会更加方便。因此我们可以考虑在初始时将 second 指向哑节点，其余的操作步骤不变。这样一来，当 first 遍历到链表的末尾时，second 的下一个节点就是我们需要删除的节点。
     *
     *
     *
     * 作者：力扣官方题解
     * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/solutions/450350/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode first = head;
        //first先前行n个节点，让其和secondDummyNode节点差n
        for (int i = 0; i < n; i++) {
            first = first.next;
        }
        //有可能操作第一个结点时，新建一个dummyNode 是比较合适的
        //比如删除第一个结点
        ListNode dummyNode = new ListNode(0, head);
        ListNode second = dummyNode;
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummyNode.next;
    }
}
