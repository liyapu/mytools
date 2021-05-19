package com.lyp.likou.str;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-19 17:53
 *
 * 409. 最长回文串
 * 给定一个包含大写字母和小写字母的字符串，找到通过这些字母构造成的最长的回文串。
 *
 * 在构造过程中，请注意区分大小写。比如 "Aa" 不能当做一个回文字符串。
 *
 * 注意:
 * 假设字符串的长度不会超过 1010。
 *
 * 示例 1:
 * 输入:
 * "abccccdd"
 *
 * 输出:
 * 7
 *
 * 解释:
 * 我们可以构造的最长的回文串是"dccaccd", 它的长度是 7。
 */
public class S_409 {
    public static int longestPalindrome(String s) {
        int len;
        int maxLen = 0;
        if (s == null || (len = s.length()) == 0) {
            return maxLen;
        }

        Map<Character, Integer> char$Times = new HashMap<>();
        //标记是否有单个的 字母，比如 1，3，5
        boolean hasSingle = false;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            char$Times.put(ch, char$Times.getOrDefault(ch, 0) + 1);
        }

        for (Map.Entry<Character, Integer> entity : char$Times.entrySet()) {
            Integer value = entity.getValue();
            if (!hasSingle && value % 2 == 1) {
                hasSingle = true;
            }

            //这里要分别计算 value 长度
            if(value%2 == 0){
                maxLen += value;
            }else{
                maxLen += value -1;
            }
        }

        if (hasSingle) {
            maxLen++;
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(longestPalindrome("abccccdd"));
    }
}
