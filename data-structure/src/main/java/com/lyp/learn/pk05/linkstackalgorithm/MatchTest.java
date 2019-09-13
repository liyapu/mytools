package com.lyp.learn.pk05.linkstackalgorithm;

import java.util.Stack;

/**
 * @Author: liyapu
 * @Description:
 * @create: 2019-05-26 11:25
 */
public class MatchTest {
        /**
         * @LEFT记录分隔符为“左”分隔符
         */
        private final int LEFT = 0;

        /**
         * @RIGHT记录分隔符为“右”分隔符
         */
        private final int RIGHT = 1;

        /**
         * @OTHER记录其他字符
         */
        private final int OTHER = 2;

        /**
         * @MAXSIZE栈的大小_也就是整个程序中的左分隔符的个数的最大值
         */
        private final int MAXSIZE = 100;

        /**
         * @Describe_判断分隔符的类型_左_右_非法
         * @param str
         * @return
         */
        public int verifyFlag(String str) {
            if ("(".equals(str) || "[".equals(str) || "{".equals(str)
                    || "/*".equals(str)) {
                return LEFT;
            } else if (")".equals(str) || "]".equals(str) || "}".equals(str)
                    || "*/".equals(str)) {
                return RIGHT;
            } else {
                return OTHER;

            }

        }

        /**
         * @Describe_判断左分隔符str1和右分隔符str2是否匹配
         * @param str1
         * @param str2
         * @return
         */
        public boolean matches(String str1, String str2) {
            if (("(".equals(str1) && ")".equals(str2))
                    || ("{".equals(str1) && "}".equals(str2))
                    || ("[".equals(str1) && "]".equals(str2))
                    || ("/*".equals(str1) && "*/".equals(str2))) {
                return true;
            } else {
                return false;
            }
        }

        /**
         * @Describe_判断是否匹配
         * @param str
         * @return
         * @throws Exception
         */
        public boolean isLegal(String str) throws Exception {
            if (!"".equals(str) && str != null) {
                Stack S = new Stack();
                int length = str.length();
                for (int i = 0; i < length; i++) {
                    // 取出元素
                    char c = str.charAt(i);
                    String t = String.valueOf(c);

                    // 对 分隔符/**/特别处理
                    if (i != length) {
                        if (('/' == c && '*' == str.charAt(i + 1))
                                || ('*' == c && '/' == str.charAt(i + 1))) {
                            t = t.concat(String.valueOf(str.charAt(i + 1)));
                            i++;
                        }
                    }
                    // 如果是左分隔符，入栈，如果是右分隔符，出栈，看是否匹配，如果不匹配，报错
                    if (LEFT == verifyFlag(t)) {
                        S.push(t);
                    } else if (RIGHT == verifyFlag(t)) {
                        if (S.isEmpty()) {
                            throw new Exception("错误：   java语法不合法,缺少左分隔符");
                        } else if (!matches(S.pop().toString(), t)) {
                            throw new Exception("错误：   java语法不合法,左右分隔符不匹配");
                        }
                    }
                }
                // 对整个语句遍历后，如果栈非空，证明栈中还有未被匹配的左分隔符，此时是错误的
                if (!S.isEmpty()) {
                    throw new Exception("错误： java语句不合法,缺少右分隔符");
                } else {
                    return true;
                }
            } else {
                throw new Exception("错误：Java语句为空 ！ ");
            }
        }

        public static void main(String[] args) throws Exception {
//            String s1 = "a = b + (c + d) * (e - f)";
//            String s2 = "s[4] = t[a[2]] + u/((i+j)*k)";
//            String s3 = "s[4] = t[a[2]] + u/((i+j)*k) /* notice */ + (k-a) * k";
//            String s4 = "s[4] = t[a[2]] + u/((i+j)*k) /* notice / + (k-a) * k";
//            String s6 = "a = {b + (c + d) * (e - f)}";
//            String s7 = "a = {b + ((c + d) * (e - f)}";
//            String s8 = "a = aa + 100";
            String s9 = "a = (aa + 100) /* notice *";

            MatchTest mt = new MatchTest();
//            System.out.println(s1 + "   :  " + mt.isLegal(s1));
//            System.out.println(s2 + "   :  " + mt.isLegal(s2));
//            System.out.println(s3 + "   :  " + mt.isLegal(s3));
//            System.out.println(s4 + "   :  " + mt.isLegal(s4));
//            System.out.println(s5 + "   :  " + mt.isLegal(s5));
//            System.out.println(s6 + "   :  " + mt.isLegal(s6));
//            System.out.println(s7 + "   :  " + mt.isLegal(s7));
//            System.out.println(s8 + "   :  " + mt.isLegal(s8));
            System.out.println(s9 + "   :  " + mt.isLegal(s9));
        }
    }
