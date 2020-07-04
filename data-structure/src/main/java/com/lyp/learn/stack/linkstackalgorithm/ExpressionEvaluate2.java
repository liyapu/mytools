package com.lyp.learn.stack.linkstackalgorithm;



import java.util.ArrayList;
import java.util.Stack;

/**
 *  支持多位数
 *  中缀表达式转为 后缀表达式
 */
public class ExpressionEvaluate2 {
    public static void main(String[] args) {
//        String expression1 = "(1 + 2)*(5  - 2)/2^2 + 5%3";
//        String expression1 = "(1.2 + 2.8)*(500  - 100)";
        String expression1 = "12.8 + (2 - 3.55) * 4 + 10/5.0";
        ArrayList<String> al = convertToPostfix(expression1);
        System.out.println(expression1);
        System.out.println(al);
        double result1 = numberCalculate(al);
        System.out.println(result1);

//        System.out.println("-------------------");
//
//        String expression2 = "3+(6*9)+2-6/(3-1)";
//        String postfix2 = convertToPostfix(expression2);
//        System.out.println(expression2);
//        System.out.println(postfix2);
//        double result2 = numberCalculate(postfix2);
//        System.out.println(result2);
    }


    /**
     * 将算法表达式转换为后缀表达式
     * 结果以列表的形式返回
     *
     * @param expression
     * @return
     */
    public static ArrayList<String> convertToPostfix(String expression) {
        //后缀表达式
        ArrayList<String> al = new ArrayList<>();
        // 运算符栈
        Stack stack = new Stack();
        for (int i = 0; i < expression.length(); i++) {
            String str = String.valueOf(expression.charAt(i));
            // 过滤空字符 ,之间有距离
            if (" ".equals(str)) {
                continue;
            }
            //左括号就入栈
            if (isOpenParenthesis(str)) {
                stack.push(str);
                // 右括号就把所有的操作符出栈直到遇到一个左括号为止，然后将该左括号出栈并丢弃
            }else if(isCloseParenthesis(str)){
                String temp;
                while (!isOpenParenthesis(temp = (String)stack.pop())) {
                    al.add(temp);
                }

            }else if(isOperator(str)){
                //如果栈为空，直接进栈。
                // 如果栈非空，则需要将栈顶运算符的优先级和要入栈的运算符的优先级进行比较
                // 将栈中比要入栈的运算符优先级高的运算符都出栈，然后将该运算符入栈
                while (!stack.isEmpty() && priority((String) stack.peek()) >= priority(str)) {
                    al.add((String)stack.pop());
                }
                stack.push(str);
            }else{
                //操作数
                //分析思路
                //1. 当处理多位数时，不能发现是一个数就立即入栈，因为他可能是多位数
                //2. 在处理数，需要向expression的表达式的index 后再看一位,如果是数就进行扫描，如果是符号才入栈
                //3. 因此我们需要定义一个变量 字符串，用于拼接

                //处理多位数
                String keepNum = "";
                keepNum += str;

                //如果str已经是expression的最后一位，就直接入栈
                if (i == expression.length() - 1) {
                    al.add(keepNum);
                }else{
                    //判断下一个字符是不是数字，如果是数字，就继续扫描，如果是运算符，则入栈
                    //注意是看后一位，不是index++
                    int index = i+1;
                    String strNext = String.valueOf(expression.charAt(index));
                    while (isNum(strNext)){
                        keepNum += strNext;
                        index++;
                        if(index >= expression.length()){
                            break;
                        }
                        strNext = String.valueOf(expression.charAt(index));
                    }
                    al.add(keepNum);
                    i += keepNum.length() - 1;
                }

            }
        }

        // 最后的时候，如果栈非空，则需要栈中的所有的运算符串联到后缀表达式的末尾
        while (!stack.isEmpty()) {
            al.add((String)stack.pop());
        }
        return al;
    }

    /**
     * 判断 字符 是否是操作符
     *
     * @param str
     * @return
     */
    private static boolean isOperator(String str) {
        if ("+".equals(str) || "-".equals(str) || "*".equals(str) || "/".equals(str) || "%".equals(str) || "^".equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断是否是 开括号
     *
     * @param str
     * @return
     */
    private static boolean isOpenParenthesis(String str) {
        return "(".equals(str);
    }

    /**
     * 判断是否是闭括号
     *
     * @param str
     * @return
     */
    private static boolean isCloseParenthesis(String str) {
        return ")".equals(str);
    }

    /**
     * 判断是否是数字
     * @param str 支持小数点
     * @return
     */
    private static boolean isNum(String str){
        return "0".equals(str)||"1".equals(str)||"2".equals(str)||"3".equals(str)||"4".equals(str)||"5".equals(str) ||
                "6".equals(str)||"7".equals(str)||"8".equals(str)||"9".equals(str) || ".".equals(str);
    }
    /**
     * 判断运算符的优先级
     *
     * @param str
     * @return
     */
    private static int priority(String str) {
        if ("^".equals(str)) {
            return 3;
        }
        if ("*".equals(str) || "/".equals(str) || "%".equals(str)) {
            return 2;
        }
        if ("+".equals(str) || "-".equals(str)) {
            return 1;
        }
        return 0;
    }

    /**
     * 对后缀表达式进行求值运算
     *
     * @param al
     * @return
     */
    public static double numberCalculate(ArrayList<String> al) {
        Stack stack = new Stack();
        for (int i = 0; i < al.size(); i++) {
            String str = al.get(i);
            if (isOperator(str)) {
                //***重点****
                //d2 先弹出 ：作为第二个操作数
                //d1 后弹出 ：作为第一个操作数
                double d2 = Double.parseDouble(stack.pop().toString());
                double d1 = Double.parseDouble(stack.pop().toString());
                double d3 = 0;
                if ("+".equals(str)) {
                    d3 = d1 + d2;
                } else if ("-".equals(str)) {
                    d3 = d1 - d2;
                } else if ("*".equals(str)) {
                    d3 = d1 * d2;
                } else if ("/".equals(str)) {
                    d3 = d1 / d2;
                } else if ("%".equals(str)) {
                    d3 = d1 % d2;
                } else if ("^".equals(str)) {
                    d3 = Math.pow(d1, d2);
                }
                stack.push(d3);
            } else {
                stack.push(str);
            }
        }
        return Double.parseDouble(stack.pop().toString());
    }

}
