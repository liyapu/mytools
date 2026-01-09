package com.lyp.likou.my;

import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *
 *
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *
 * 示例 4：
 * 输入：s = "([])"
 * 输出：true
 *
 * 示例 5：
 * 输入：s = "([)]"
 * 输出：false
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class A00020_有效的括号 {

    public static boolean isValid(String s) {
        char[] charArray = s.toCharArray();
        Stack<Character> stack = new Stack<>();

        for (char c : charArray) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    //弹出的都是左括号
                    Character pop = stack.pop();
                    //此处的c 必现判断是右括号  三种括号间为或
                    if ((c == ')' && pop == '(') || (c == ']' && pop == '[') || (c == '}' && pop == '{')) {
                        continue;
                    } else {
                        return false;
                    }
                }
            }
        }
        return stack.isEmpty();
    }

    public static void main(String[] args) {
        String s = "()";
        System.out.println(isValid(s));
    }

}
