package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 14:33
 *
 * 125. 验证回文串
 * 给定一个字符串，验证它是否是回文串，只考虑字母和数字字符，可以忽略字母的大小写。
 *
 * 说明：本题中，我们将空字符串定义为有效的回文串。
 *
 * 示例 1:
 * 输入: "A man, a plan, a canal: Panama"
 * 输出: true
 *
 * 示例 2:
 * 输入: "race a car"
 * 输出: false
 */
public class S_125 {

    /**
     * Character api 使用
     * @param s
     * @return
     */
    public static boolean isPalindrome(String s) {
        if (s == null) return false;
        if ("".equals(s)) return true;
        char[] chars = s.toCharArray();
        int len = chars.length;

        for (int i = 0, j = len - 1; i <= j; ) {
            //判断是否是 字母和数字
            if (!Character.isLetterOrDigit(chars[i])) {
                i++;
                continue;
            }
            if (!Character.isLetterOrDigit(chars[j])) {
                j--;
                continue;
            }
            //先变为 小写的，然后再判断，上下加 32 那个方式不行，数字加32整好有等于字母的
            if(Character.toLowerCase(chars[i]) == Character.toLowerCase(chars[j])){
                i++;
                j--;
                continue;
            }else{
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println(isPalindrome("A man, a plan, a canal: Panama"));
        System.out.println(isPalindrome("0P"));
        System.out.println(isPalindrome("race a car"));
    }

}
