package com.lyp.likou.my;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * 394. 字符串解码
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 * 测试用例保证输出的长度不会超过 105。
 *
 * 示例 1：
 * 输入：s = "3[a]2[bc]"
 * 输出："aaabcbc"
 *
 * 示例 2：
 * 输入：s = "3[a2[c]]"
 * 输出："accaccacc"
 *
 * 示例 3：
 * 输入：s = "2[abc]3[cd]ef"
 * 输出："abcabccdcdcdef"
 *
 * 示例 4：
 * 输入：s = "abc3[cd]xyz"
 * 输出："abccdcdcdxyz"
 *
 *
 * 提示：
 * 1 <= s.length <= 30
 * s 由小写英文字母、数字和方括号 '[]' 组成
 * s 保证是一个 有效 的输入。
 * s 中所有整数的取值范围为 [1, 300]
 */
public class A00394_字符串解码 {

    public static String decodeString(String s) {
        Stack<Character> stack = new Stack();
        int n = s.length();
        List<Character> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                stack.add(c);
            } else if (Character.isLowerCase(c)) {
                stack.add(c);
            } else if (c == '[') {
                stack.add(c);
            } else if (c == ']') {
                List<Character> tempChList = new ArrayList<>();
                List<Character> tempNumList = new ArrayList<>();
                boolean hasLeft = false;
                while (!stack.isEmpty()) {
                    Character top = stack.pop();
                    if (top == '[') {
                        hasLeft = true;
                        continue;
                    }
                    if (Character.isLowerCase(top)) {
                        tempChList.add(top);
                    }
                    if (Character.isDigit(top)) {
                        tempNumList.add(top);
                        if (hasLeft && !stack.isEmpty() && !Character.isDigit(stack.peek())) {
                            break;
                        }
                    }
                }
                StringBuffer sbNum = new StringBuffer();
                for (int j = tempNumList.size() - 1; j >= 0; j--) {
                    sbNum.append(tempNumList.get(j));
                }
                Integer num = Integer.parseInt(sbNum.toString());
                List<Character> tempReslutChList = new ArrayList<>();

                for (int k = 0; k < num; k++) {
                    for (int m = tempChList.size() - 1; m >= 0; m--) {
                        tempReslutChList.add(tempChList.get(m));
                    }
                }
                if (stack.isEmpty()) {
                    result.addAll(tempReslutChList);
                } else {
                    for (Character chh : tempReslutChList) {
                        stack.add(chh);
                    }
                }

            } else {
                System.out.println("为止情况");
            }
        }
        List<Character> chLastList = new ArrayList<>();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            chLastList.add(pop);
        }
        for (int i = chLastList.size() - 1; i >= 0; i--) {
            result.add(chLastList.get(i));
        }
        // 使用Stream API转换并收集结果为String
        return result.stream()
                .map(String::valueOf)  // 将Character转换为String，以便收集器可以处理
                .collect(Collectors.joining());  // 连接所有字符串元素形成一个单一的String

    }

    public static String decodeString2(String s) {
        Stack<Character> stack = new Stack();
        int n = s.length();
        List<Character> result = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            char c = s.charAt(i);
            if (Character.isLetterOrDigit(c) || c == '[') {
                stack.add(c);
            } else if (c == ']') {
                List<Character> tempChList = new ArrayList<>();
                List<Character> tempNumList = new ArrayList<>();
                boolean hasLeft = false;
                while (!stack.isEmpty()) {
                    Character top = stack.pop();
                    if (top == '[') {
                        hasLeft = true;
                        continue;
                    }
                    if (Character.isLowerCase(top)) {
                        tempChList.add(top);
                    }
                    if (Character.isDigit(top)) {
                        tempNumList.add(top);
                        if (hasLeft && !stack.isEmpty() && !Character.isDigit(stack.peek())) {
                            break;
                        }
                    }
                }
                StringBuffer sbNum = new StringBuffer();
                for (int j = tempNumList.size() - 1; j >= 0; j--) {
                    sbNum.append(tempNumList.get(j));
                }
                Integer num = Integer.parseInt(sbNum.toString());
                List<Character> tempReslutChList = new ArrayList<>();

                for (int k = 0; k < num; k++) {
                    for (int m = tempChList.size() - 1; m >= 0; m--) {
                        tempReslutChList.add(tempChList.get(m));
                    }
                }
                if (stack.isEmpty()) {
                    result.addAll(tempReslutChList);
                } else {
                    for (Character chh : tempReslutChList) {
                        stack.add(chh);
                    }
                }

            } else {
                System.out.println("为止情况");
            }
        }
        List<Character> chLastList = new ArrayList<>();
        while (!stack.isEmpty()) {
            Character pop = stack.pop();
            chLastList.add(pop);
        }
        for (int i = chLastList.size() - 1; i >= 0; i--) {
            result.add(chLastList.get(i));
        }
        // 使用Stream API转换并收集结果为String
        return result.stream()
                .map(String::valueOf)  // 将Character转换为String，以便收集器可以处理
                .collect(Collectors.joining());  // 连接所有字符串元素形成一个单一的String

    }

    public static String decodeString3(String s) {
        Stack<Integer> numStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();


        char[] charArray = s.toCharArray();
        int num = 0;
        String currentString = "";

        for (char c : charArray) {
            if (Character.isDigit(c)) {
                num = num * 10 + c - '0';
            } else if (c == '[') {
                numStack.push(num);
                stringStack.push(currentString);
                num = 0;
                currentString = "";
            } else if (c == ']') {
                StringBuffer sb = new StringBuffer(stringStack.pop());
                Integer times = numStack.pop();
                for (int i = 0; i < times; i++) {
                    sb.append(currentString);
                }
                currentString = sb.toString();
            } else {
                currentString += c;
            }
        }
        return currentString;
    }

    public static void main(String[] args) {
        String s = "mn23[ab]fg2[bc]";
        System.out.println(decodeString(s));
        System.out.println(decodeString2(s));
        System.out.println(decodeString3(s));

        String ss =  "3[a]2[bc]";
        System.out.println(decodeString(ss));
        System.out.println(decodeString2(ss));
        System.out.println(decodeString3(ss));
//
//        String s1 = "3[a2[c]]";
//        System.out.println(decodeString(s1));
//        System.out.println(decodeString2(s1));
//        System.out.println(decodeString3(s1));
//
//        String s2 = "2[abc]3[cd]ef";
//        System.out.println(decodeString(s2));
//        System.out.println(decodeString2(s2));
//        System.out.println(decodeString3(s2));
//
//        String s3 = "abc3[cd]xyz";
//        System.out.println(decodeString(s3));
//        System.out.println(decodeString2(s3));
//        System.out.println(decodeString3(s3));
//
//        String s4 = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
//        System.out.println(decodeString(s4));
//        System.out.println(decodeString2(s4));
//        System.out.println(decodeString3(s4));

    }
}
