package com.lyp.likou.str;

import java.util.Objects;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-17 17:32
 *
 * 680. 验证回文字符串 Ⅱ
 * 给定一个非空字符串 s，最多删除一个字符。判断是否能成为回文字符串。
 *
 * 示例 1:
 * 输入: "aba"
 * 输出: True
 *
 * 示例 2:
 * 输入: "abca"
 * 输出: True
 * 解释: 你可以删除c字符。
 * 注意:
 *
 * 字符串只包含从 a-z 的小写字母。字符串的最大长度是50000。
 */
public class S_680 {

    public static boolean validPalindrome(String s) {
        int len;
        if (s == null || (len = s.length()) == 0) {
            return true;
        }

        int left = 0, right = len - 1;

        while (left <= right) {
            if (Objects.equals(s.charAt(left), s.charAt(right))) {
                left++;
                right--;
            } else {
                // 这里要用 + 号，不能用自增，因为自增会影响 || 后面的数值
                // left + 1 删除 左边的字符
                // right - 1 删除 右边的字符
                return isHuiWen(s, left + 1, right) || isHuiWen(s, left, right - 1);
            }
        }
        return true;
    }

    public static boolean isHuiWen(String s, int left, int right) {
        while (left <= right) {
            if (Objects.equals(s.charAt(left), s.charAt(right))) {
                left++;
                right--;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
//        System.out.println(validPalindrome("aba"));
//        System.out.println(validPalindrome("abca"));
//        System.out.println(validPalindrome("abcdefedba"));
        System.out.println(validPalindrome("cbbcc"));
    }
}
