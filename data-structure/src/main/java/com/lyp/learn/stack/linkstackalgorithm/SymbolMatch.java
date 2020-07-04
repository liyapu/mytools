package com.lyp.learn.stack.linkstackalgorithm;

import java.util.Stack;

public class SymbolMatch {

    /**
     * 判断是否是 左符号
     * @param str
     * @return
     */
    static boolean isLeft(String str){
        return "(".equals(str) || "[".equals(str) || "{".equals(str) || "/*".equals(str);
    }

    /**
     * 判断是否是 右符号
     * @param str
     * @return
     */
    static boolean isRight(String str){
        return ")".equals(str) || "]".equals(str) || "}".equals(str) || "*/".equals(str);
    }

    /**
     * 判断是否是 单引号 ,双引号
     * 无法分 左右
     * @param str
     * @return
     */
    static boolean isQuot(String str){
        return "\'".equals(str) || "\"".equals(str);
    }

    static boolean isMatch(String left,String right){
        return "(".equals(left) && ")".equals(right) ||
                "[".equals(left) && "]".equals(right) ||
                "{".equals(left) && "}".equals(right) ||
                "/*".equals(left) && "*/".equals(right) ||
                "\'".equals(left) && "\'".equals(right) ||
                "\"".equals(left) && "\"".equals(right);

    }

    /**
     * 判断字符串表达式是否合法
     * @param str
     * @return
     */
    public static boolean isLegal(String str){
        Stack<String> stack = new Stack<>();
        int length = str.length();
        boolean isLegal = true;
        int i = 0;
        while(isLegal && i < length){
            char c = str.charAt(i);
            String cstr = String.valueOf(c);
            if(i <= length - 2){ //防止(i+1)越界
                String next = String.valueOf(str.charAt(i+1));
                if("/".equals(cstr) && "*".equals(next) ||
                        "*".equals(cstr) && "/".equals(next)){
                    cstr = cstr.concat(next);
                    i++;
                }
            }

            if(isLeft(cstr)){
                stack.push(cstr);
            }else if(isRight(cstr)){
                if(stack.size() > 0 && isMatch(stack.pop(),cstr)){
                    isLegal = true;
                }else{
                    isLegal = false;
                }
            }else if(isQuot(cstr)){
                if(stack.size() > 0 && isMatch(stack.peek(),cstr)){
                    stack.pop();
                }else{
                    stack.push(cstr);
                }
            }
            i++;
        }

        return isLegal && stack.size() == 0;
    }

    public static void main(String[] args) {
        String s1 = "a = b + (c + d) * (e - f)";
        String s2 = "s[4] = t[a[2]] + u/((i+j)*k)";
        String s3 = "s[4] = t[a[2]] + u/((i+j)*k) /* notice */ + (k-a) * k";
        String s4 = "s[4] = t[a[2]] + u/((i+j)*k) /* notice / + (k-a) * k";
        String s5 = "\'a\'" + "aa" + "bb" + 'c' + "aa";
        String s6 = "a = {b + (c + d) * (e - f)}";
        String s7 = "a = {b + ((c + d) * (e - f)}";
        String s8 = "a = aa + 100";
        String s9 = "a = (aa + 100) /* notice */";
        String s10 = "a = (aa + 100) /* notice *";

        System.out.println(s1 + "   :  " + isLegal(s1));
        System.out.println(s2 + "   :  " + isLegal(s2));
        System.out.println(s3 + "   :  " + isLegal(s3));
        System.out.println(s4 + "   :  " + isLegal(s4));
        System.out.println(s5 + "   :  " + isLegal(s5));
        System.out.println(s6 + "   :  " + isLegal(s6));
        System.out.println(s7 + "   :  " + isLegal(s7));
        System.out.println(s8 + "   :  " + isLegal(s8));
        System.out.println(s9 + "   :  " + isLegal(s9));
        System.out.println(s10 + "   :  " + isLegal(s10));

        System.out.println();
        System.out.println("'");
        System.out.println("\"");
    }

}
