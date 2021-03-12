package com.lyp.likou.dynamic;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-12 17:09
 *
 * 392. 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 *
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。（例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 *
 * 进阶：
 *
 * 如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
 *
 * 致谢：
 *
 * 特别感谢 @pbrother 添加此问题并且创建所有测试用例。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "abc", t = "ahbgdc"
 * 输出：true
 * 示例 2：
 *
 * 输入：s = "axc", t = "ahbgdc"
 * 输出：false
 */
public class D_392 {

    public static boolean isSubsequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();
        if(sLen == 0){
            return true;
        }
        if(tLen == 0){
            return false;
        }

        int i = 0;
        int j =0;
        for (; i < tLen && j < sLen ; ) {
            if(t.charAt(i) == s.charAt(j)){
                i++;
                j++;
            }else{
                i++;
            }
        }

        return j==sLen;
    }

    public static void main(String[] args) {
       String  s1 = "abc", t1 = "ahbgdc";
        System.out.println(isSubsequence(s1,t1));

        String  s2 = "axc", t2 = "ahbgdc";
        System.out.println(isSubsequence(s2,t2));

        String  s3 = "abc", t3 = "abc";
        System.out.println(isSubsequence(s3,t3));

        String  s4 = "", t4 = "ahbgdc";
        System.out.println(isSubsequence(s4,t4));
    }

}
