package com.lyp.likou.other;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-02 21:58
 *
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 *
 * 有效字符串需满足：
 *
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
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
 * 输入：s = "([)]"
 * 输出：false
 *
 * 示例 5：
 * 输入：s = "{[]}"
 * 输出：true
 *
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 仅由括号 '()[]{}' 组成
 */
public class L_20 {

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();

        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if(stack.isEmpty()) return false;
                Character pop = stack.pop();
                if ((pop == '(' && c == ')') || (pop == '[' && c == ']') || (pop == '{' && c == '}')) {

                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

    /**
     *
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        //注意到有效字符串的长度一定为偶数，因此如果字符串的长度为奇数，
        // 我们可以直接返回 False，省去后续的遍历判断过程。
        int len = s.length();
        if(len%2 == 1) return false;

        Map<Character,Character> pairs = new HashMap<>();
        pairs.put('(',')');
        pairs.put('[',']');
        pairs.put('{','}');
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if(pairs.containsKey(c)){
                stack.push(c);
            }else{
                //栈不能为空，为空就是不成对，而且为空 后面 stack.pop 会报错
                // pairs 要用 stack 弹出的元素做key
                if(stack.isEmpty() || pairs.get(stack.pop()) != c){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }

        public static void main(String[] args) {
        System.out.println(isValid2("()"));
        System.out.println(isValid2("()))"));
        System.out.println(isValid2("((()"));
        System.out.println(isValid2("()[]{}"));
        System.out.println(isValid2("([)]"));
        System.out.println(isValid2("{[]}"));
    }
}
