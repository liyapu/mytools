package com.lyp.likou.dynamic;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-11 15:51
 *
 * 58. 最后一个单词的长度
 * 给你一个字符串 s，由若干单词组成，单词之间用空格隔开。返回字符串中最后一个单词的长度。如果不存在最后一个单词，请返回 0 。
 *
 * 单词 是指仅由字母组成、不包含任何空格字符的最大子字符串。
 *
 *
 *
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 *
 * 示例 2：
 * 输入：s = " "
 * 输出：0
 *
 * 输入：s = "    "
 * 输出：0
 *
 * 输入：s = "b   a    "
 * 输出：1
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅有英文字母和空格 ' ' 组成
 */
public class D_58 {

    /**
     * 从右向左遍历，从第一个不是空格的字符开始计数，一旦开始计数，再遇到空格就结束了
     */
    public static int lengthOfLastWord(String s) {
        if (s == null || s.length() == 0) return 0;

        int len = s.length() - 1;
        int count = 0;
        for (int i = len; i >= 0; i--) {
            if (s.charAt(i) == ' ') {
                //count 一旦计过数，再次遇到空格就终止
                if (count != 0) {
                    break;
                }
                //是空格，就继续
                continue;
            }
            //不是空格，就计数
            count++;
        }
        return count;
    }

    /**
     * 标签：字符串遍历
     * 从字符串末尾开始向前遍历，其中主要有两种情况
     * 第一种情况，以字符串"Hello World"为例，从后向前遍历直到遍历到头或者遇到空格为止，即为最后一个单词"World"的长度5
     * 第二种情况，以字符串"Hello World  "为例，需要先将末尾的空格过滤掉，再进行第一种情况的操作，即认为最后一个单词为"World"，长度为5
     * 所以完整过程为先从后过滤掉空格找到单词尾部，再从尾部向前遍历，找到单词头部，最后两者相减，即为单词的长度
     * 时间复杂度：O(n)，n为结尾空格和结尾单词总体长度
     *
     * 作者：guanpengchn
     * 链接：https://leetcode-cn.com/problems/length-of-last-word/solution/hua-jie-suan-fa-58-zui-hou-yi-ge-dan-ci-de-chang-d/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public static int lengthOfLastWord_A(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }
        int end = s.length() - 1;

        //此处去除尾部空格
        while (end >= 0 && s.charAt(end) == ' ') {
            end--;
        }

        //这里必须 小于 0，不能等于0，否则 "a" 这种用例不通过
        if (end < 0) {
            return 0;
        }

        int start = end;
        //此处统计start开始处，倒序不为空格的
        while (start >= 0 && s.charAt(start) != ' ') {
            start--;
        }

        return end - start;
    }

    public static void main(String[] args) {
        String s = "Hello World";
        String s1 = " ";
        String s2 = "    ";
        String s3 = "b   a    ";
        String s4 = "a";
        System.out.println(lengthOfLastWord(s));
        System.out.println(lengthOfLastWord(s1));
        System.out.println(lengthOfLastWord(s2));
        System.out.println(lengthOfLastWord(s3));
        System.out.println(lengthOfLastWord(s4));

        System.out.println();
        System.out.println(lengthOfLastWord_A(s));
        System.out.println(lengthOfLastWord_A(s1));
        System.out.println(lengthOfLastWord_A(s2));
        System.out.println(lengthOfLastWord_A(s3));
        System.out.println(lengthOfLastWord_A(s4));

    }
}
