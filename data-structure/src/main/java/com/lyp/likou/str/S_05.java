package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-16 17:04
 *
 * 剑指 Offer 05. 替换空格
 * 请实现一个函数，把字符串 s 中的每个空格替换成"%20"。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "We are happy."
 * 输出："We%20are%20happy."
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 10000
 */
public class S_05 {
    public  static  String replaceSpace(String s) {
        if(null == s) return null;
        char[] chars = s.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            if(chars[i] == ' '){
                sb.append("%20");
            }else{
                sb.append(chars[i]);
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
      String  s = "We are happy.";
        System.out.println(replaceSpace(s));
    }
}
