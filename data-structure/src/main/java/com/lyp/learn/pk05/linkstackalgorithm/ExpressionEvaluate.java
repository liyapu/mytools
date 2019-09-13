package com.lyp.learn.pk05.linkstackalgorithm;


import java.util.Stack;

public class ExpressionEvaluate {
    public static void main(String[] args) {
        String expression1 = "(1 + 2)*(5  - 2)/2^2 + 5%3";
        String postfix1 = convertToPostfix(expression1);
        System.out.println(expression1);
        System.out.println(postfix1);
        double result1 = numberCalculate(postfix1);
        System.out.println(result1);

        System.out.println("-------------------");

        String expression2 = "3+(6*9)+2-6/(3-1)";
        String postfix2 = convertToPostfix(expression2);
        System.out.println(expression2);
        System.out.println(postfix2);
        double result2 = numberCalculate(postfix2);
        System.out.println(result2);
    }


    /**
     * 将算法表达式转换为后缀表达式
     * 结果以字符串的形式返回
     *
     * @param expression
     * @return
     */
    public static String convertToPostfix(String expression) {
        //后缀表达式
        StringBuilder sb = new StringBuilder();
        // 运算符栈
        Stack stack = new Stack();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);
            // 过滤空字符 ,''之间有距离
            if (' ' == c) {
                continue;
            }
            //左括号就入栈
            if (isOpenParenthesis(c)) {
                stack.push(c);
                // 右括号就把所有的操作符出栈直到遇到一个左括号为止，然后将该左括号出栈并丢弃
            }else if(isCloaseParenthesis(c)){
                char temp;
                while (!isOpenParenthesis(temp = (char) stack.pop())) {
                    sb.append(temp);
                }

            }else if(isOperator(c)){
                //如果栈为空，直接进栈。
                // 如果栈非空，则需要将栈顶运算符的优先级和要入栈的运算符的优先级进行比较
                // 将栈中比要入栈的运算符优先级高的运算符都出栈，然后将该运算符入栈
                while (!stack.isEmpty() && priority((char) stack.peek()) >= priority(c)) {
                    sb.append(stack.pop());
                }
                stack.push(c);
            }else{
                //操作数，直接添加到后缀表达式中
                sb.append(c);
            }
        }

        // 最后的时候，如果栈非空，则需要栈中的所有的运算符串联到后缀表达式的末尾
        while (!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

    /**
     * 判断 字符 是否是操作符
     *
     * @param c
     * @return
     */
    private static boolean isOperator(char c) {
        if ('+' == c || '-' == c || '*' == c || '/' == c || '%' == c || '^' == c) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是 开括号
     *
     * @param c
     * @return
     */
    private static boolean isOpenParenthesis(char c) {
        return '(' == c;
    }

    /**
     * 判断是否是闭括号
     *
     * @param c
     * @return
     */
    private static boolean isCloaseParenthesis(char c) {
        return ')' == c;
    }

    /**
     * 判断运算符的优先级
     *
     * @param c
     * @return
     */
    private static int priority(char c) {
        if ('^' == c) {
            return 3;
        }
        if ('*' == c || '/' == c || '%' == c) {
            return 2;
        }
        if ('+' == c || '-' == c) {
            return 1;
        }
        return 0;
    }

    /**
     * 对后缀表达式进行求值运算
     *
     * @param postfix
     * @return
     */
    public static double numberCalculate(String postfix) {
        Stack stack = new Stack();
        for (int i = 0; i < postfix.length(); i++) {
            char c = postfix.charAt(i);
            if (isOperator(c)) {
                //***重点****
                //d2 先弹出 ：作为第二个操作数
                //d1 后弹出 ：作为第一个操作数
                double d2 = Double.parseDouble(stack.pop().toString());
                double d1 = Double.parseDouble(stack.pop().toString());
                double d3 = 0;
                if ('+' == c) {
                    d3 = d1 + d2;
                } else if ('-' == c) {
                    d3 = d1 - d2;
                } else if ('*' == c) {
                    d3 = d1 * d2;
                } else if ('/' == c) {
                    d3 = d1 / d2;
                } else if ('%' == c) {
                    d3 = d1 % d2;
                } else if ('^' == c) {
                    d3 = Math.pow(d1, d2);
                }
                stack.push(d3);
            } else {
                stack.push(c);
            }
        }
        return (Double) stack.pop();
    }

}
