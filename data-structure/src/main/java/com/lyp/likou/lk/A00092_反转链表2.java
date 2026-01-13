package com.lyp.likou.lk;


import com.lyp.likou.lk.po.ListNode;
import com.lyp.likou.lk.utils.LinkUtils;

/**
 * 92. 反转链表 II
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *
 *
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 */
public class A00092_反转链表2 {

    /**
     * pre  是 precursor(前驱)的缩写
     * succ 是 successor(后继)的缩写
     *
     *  记录 preLeft,left ... right,rightNext 节点
     *  阶段 [left,right]翻转
     * @param head
     * @param left
     * @param right
     * @return
     *
     * 缺点是：如果 left 和 right 的区域很大，恰好是链表的头节点和尾节点时，找到 left 和 right 需要遍历一次，
     * 反转它们之间的链表还需要遍历一次，虽然总的时间复杂度为 O(N)，但遍历了链表 2 次，可不可以只遍历一次呢？
     * 答案是可以的
     */
    public static  ListNode reverseBetween( ListNode head, int left, int right) {
        // 因为头节点有可能发生变化，使用虚拟头节点可以避免复杂的分类讨论
        ListNode dummyNode = new ListNode(-1, head);

         ListNode preLeftNode = dummyNode;

        // 第 1 步：从虚拟头节点走 left - 1 步，来到 left 节点的前一个节点
        // 建议写在 for 循环里，语义清晰
        for (int i = 0; i < left - 1; i++) {
            preLeftNode = preLeftNode.next;
        }

         ListNode leftNode = preLeftNode.next;
         ListNode rightNode = leftNode;

        // 第 2 步：从 pre 再走 right - left + 1 步，来到 right 节点
        for (int i = 0; i < (right - left); i++) {
            rightNode = rightNode.next;
        }

        // 第 3 步：切断出一个子链表（截取链表）
         ListNode rightNextNode = rightNode.next;

        //断开[left,right]链表的指针
        // 注意：切断链接
        preLeftNode.next = null;
        rightNode.next = null;

        //翻转
        reverseNode(leftNode);

        // 第 5 步：接回到原来的链表中
        preLeftNode.next = rightNode;
        leftNode.next = rightNextNode;
        return dummyNode.next;
    }

    public static void reverseNode( ListNode head) {
         ListNode pre = null;
         ListNode cur = head;
        while (cur != null) {
             ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
    }

    /**
     *
     * 下面我们具体解释如何实现。使用三个指针变量 pre、curr、next 来记录反转的过程中需要的变量，它们的意义如下：
     *
     * curr：指向待反转区域的第一个节点 left；
     * next：永远指向 curr 的下一个节点，循环过程中，curr 变化以后 next 会变化；
     * pre：永远指向待反转区域的第一个节点 left 的前一个节点，在循环过程中不变
     *
     *
     * 操作步骤：
     * 先将 curr 的下一个节点记录为 next；
     * 执行操作 ①：把 curr 的下一个节点指向 next 的下一个节点；
     * 执行操作 ②：把 next 的下一个节点指向 pre 的下一个节点；
     * 执行操作 ③：把 pre 的下一个节点指向 next。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/reverse-linked-list-ii/solution/fan-zhuan-lian-biao-ii-by-leetcode-solut-teyq/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @param left
     * @param right
     * @return
     */
    public static  ListNode reverseBetween2( ListNode head, int left, int right) {
         ListNode dummyNode = new  ListNode(-1,head);
         ListNode preLeftNode = dummyNode;
        for (int i = 0; i < left - 1; i++) {
            preLeftNode = preLeftNode.next;
        }

         ListNode cur = preLeftNode.next;
         ListNode next = null;
        for (int i = 0; i < (right - left); i++) {
            next = cur.next;
            cur.next = next.next;
            next.next = preLeftNode.next;
            preLeftNode.next = next;
        }

        return dummyNode.next;
    }

    public static void main(String[] args) {
//        test1();
        test2();
    }

    private static void test1() {
         ListNode l1 =  LinkUtils.buildList(1, 2, 3, 4, 5);
         ListNode l1Result = reverseBetween(l1, 2, 4);
         LinkUtils.printListNode(l1Result);

         ListNode l2 =  LinkUtils.buildList(5);
         ListNode l2Result = reverseBetween(l2, 1, 1);
         LinkUtils.printListNode(l2Result);

         ListNode l3 =  LinkUtils.buildList(3, 5);
         ListNode l3Result = reverseBetween(l3, 1, 1);
         LinkUtils.printListNode(l3Result);

         ListNode l4 =  LinkUtils.buildList(3, 5);
         ListNode l4Result = reverseBetween(l4, 2, 2);
         LinkUtils.printListNode(l4Result);

         ListNode l5 =  LinkUtils.buildList(3, 5);
         ListNode l5Result = reverseBetween(l5, 1, 2);
         LinkUtils.printListNode(l5Result);

         ListNode l6 =  LinkUtils.buildList(1, 2, 3);
         ListNode l6Result = reverseBetween(l6, 1, 2);
         LinkUtils.printListNode(l6Result);
    }

    private static void test2() {
         ListNode l1 =  LinkUtils.buildList(1, 2, 3, 4, 5);
         ListNode l1Result = reverseBetween2(l1, 2, 4);
         LinkUtils.printListNode(l1Result);

         ListNode l2 =  LinkUtils.buildList(5);
         ListNode l2Result = reverseBetween2(l2, 1, 1);
         LinkUtils.printListNode(l2Result);

         ListNode l3 =  LinkUtils.buildList(3, 5);
         ListNode l3Result = reverseBetween2(l3, 1, 1);
         LinkUtils.printListNode(l3Result);

         ListNode l4 =  LinkUtils.buildList(3, 5);
         ListNode l4Result = reverseBetween2(l4, 2, 2);
         LinkUtils.printListNode(l4Result);

         ListNode l5 =  LinkUtils.buildList(3, 5);
         ListNode l5Result = reverseBetween2(l5, 1, 2);
         LinkUtils.printListNode(l5Result);

         ListNode l6 =  LinkUtils.buildList(1, 2, 3);
        ListNode l6Result = reverseBetween2(l6, 1, 2);
        LinkUtils.printListNode(l6Result);
    }
}