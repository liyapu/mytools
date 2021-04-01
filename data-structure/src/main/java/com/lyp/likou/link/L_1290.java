package com.lyp.likou.link;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-01 17:46
 *
 * 1290. 二进制链表转整数
 * 给你一个单链表的引用结点 head。链表中每个结点的值不是 0 就是 1。已知此链表是一个整数数字的二进制表示形式。
 *
 * 请你返回该链表所表示数字的 十进制值 。
 *
 *
 * 示例 1：
 * 输入：head = [1,0,1]
 * 输出：5
 * 解释：二进制数 (101) 转化为十进制数 (5)
 *
 * 示例 2：
 * 输入：head = [0]
 * 输出：0
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：1
 *
 * 示例 4：
 * 输入：head = [1,0,0,1,0,0,1,1,1,0,0,0,0,0,0]
 * 输出：18880
 *
 * 示例 5：
 * 输入：head = [0,0]
 * 输出：0
 *
 *
 * 提示：
 *
 * 链表不为空。
 * 链表的结点总数不超过 30。
 * 每个结点的值不是 0 就是 1。
 */
public class L_1290 {

    /**
     * 先转为列表，然后倒序计算
     * @param head
     * @return
     */
    public static int getDecimalValue(ListNode head) {
        List<Integer> al = new ArrayList<>();
        while (head != null) {
            al.add(head.val);
            head = head.next;
        }

        int sum = 0;
        int unit = 1;
        for (int i = al.size() - 1; i >= 0; i--) {
            sum += al.get(i) * unit;
            unit *= 2;
        }
        return sum;
    }

    /**
     * 总体思路很简单，就是利用位移运算进行运算，大概原理如下：
     * 当一个数进行左移或右移时候，如果是正数的情况下，空位补0，如下所展示：
     * 10 左移一位置，那么就变成 100，利用这个性质，结合这个题目，我们可知道如果将位移后出现的空位再加上我们所获得的值，就可以计算出对应的十进制
     *
     * 作者：dreamscat
     * 链接：https://leetcode-cn.com/problems/convert-binary-number-in-a-linked-list-to-integer/solution/er-jin-zhi-lian-biao-zhuan-zheng-shu-jav-0us1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param head
     * @return
     */
    public static int getDecimalValue2(ListNode head) {
        int sum = 0;
        while (head != null) {
            sum = (sum << 1) + head.val;
            head = head.next;
        }
        return sum;
    }

    public static void main(String[] args) {
//        ListNode l1 = LinkUtils.buildList(1,0);
//        int l1Result = getDecimalValue(l1);
//        System.out.println(l1Result);

        ListNode l2 = LinkUtils.buildList(1, 0);
        int l2Result = getDecimalValue2(l2);
        System.out.println(l2Result);
    }

}
