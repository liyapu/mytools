package com.lyp.learn.utils;

/**
 * @author liyapu
 * @date 2024-10-22 17:37
 * @description
 */
public class StrUtils {

    /**
     * 移除字符串中的多种字符
     *
     * @param str   原字符串
     * @param chars 需要移除的字符数组
     * @return 移除指定字符后的字符串
     */
    public static String removeChars(String str, char... chars) {
        if (str == null || str.isEmpty() || chars == null || chars.length == 0) {
            return str;
        }
        StringBuilder sb = new StringBuilder(str);
        for (int i = 0; i < sb.length(); ) {
            if (containsChar(chars, sb.charAt(i))) {
                sb.deleteCharAt(i);
            } else {
                i++;
            }
        }
        return sb.toString();
    }

    /**
     * 判断字符是否在字符数组中
     *
     * @param chars 字符数组
     * @param ch    需要检查的字符
     * @return 如果字符存在于数组中，则返回 true
     */
    private static boolean containsChar(char[] chars, char ch) {
        for (char c : chars) {
            if (c == ch) {
                return true;
            }
        }
        return false;
    }


}
