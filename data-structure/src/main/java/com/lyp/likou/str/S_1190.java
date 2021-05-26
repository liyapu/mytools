package com.lyp.likou.str;

import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-26 13:41
 *
 * 1190. 反转每对括号间的子串
 * 给出一个字符串 s（仅含有小写英文字母和括号）。
 *
 * 请你按照从括号内到外的顺序，逐层反转每对匹配括号中的字符串，并返回最终的结果。
 *
 * 注意，您的结果中 不应 包含任何括号。
 *
 *
 * 示例 1：
 * 输入：s = "(abcd)"
 * 输出："dcba"
 *
 * 示例 2：
 * 输入：s = "(u(love)i)"
 * 输出："iloveu"
 *
 * 示例 3：
 * 输入：s = "(ed(et(oc))el)"
 * 输出："leetcode"
 *
 * 示例 4：
 * 输入：s = "a(bcdefghijkl(mno)p)q"
 * 输出："apmnolkjihgfedcbq"
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 2000
 * s 中只有小写英文字母和括号
 * 我们确保所有括号都是成对出现的
 */
public class S_1190 {

    public static String reverseParentheses(String s) {
        Stack<String> stack = new Stack<>();
        String leftBracket = "(";
        String rightBracket = ")";
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {
            String c = s.charAt(i) + "";
            if (leftBracket.equals(c)) {
                stack.push(sb.toString());
                sb.setLength(0);
            } else if (rightBracket.equals(c)) {
                sb.reverse();
                sb.insert(0, stack.pop());
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(reverseParentheses("ab(u(love)i)yz"));
    }
}
