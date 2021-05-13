package com.lyp.likou.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-20 10:03
 *
 * 3. 无重复字符的最长子串
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 *
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 示例 4:
 * 输入: s = ""
 * 输出: 0
 *
 *
 * 提示：
 *
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class S_3 {

    public static int lengthOfLongestSubstring(String s) {
        int len = 0;
        int index = 0;
        StringBuilder sb = new StringBuilder();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {

            if ((index = sb.indexOf(chars[i] + "")) >= 0) {
                sb = new StringBuilder(sb.substring(index + 1));
            }
            sb.append(chars[i]);
            if (sb.length() > len) {
                len = sb.length();
            }
        }
        return len;
    }

    /**
     * 逐个字符遍历，然后向后找最长字符串
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring2(String s) {
        int len = 0;
        int left = 0;
        int right = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            left = right = i;
            Set<Character> set = new HashSet<>();
            set.add(chars[i]);
            for (int j = left + 1; j < chars.length; j++) {
                if (set.add(chars[j])) {

                } else {
                    break;
                }
            }
            len = Math.max(len, set.size());
        }
        return len;
    }

    public static int lengthOfLongestSubstring3(String s) {
        if (s == null || s.length() == 0) return 0;
        Map<Character, Integer> value$Index = new HashMap<>();
        int left = 0;
        int maxLen = 0;
        char[] chars = s.toCharArray();

        for (int i = 0; i < chars.length; i++) {
            if (value$Index.containsKey(chars[i])) {
                left = Math.max(left, value$Index.get(chars[i]) + 1);
            }
            value$Index.put(chars[i], i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        return maxLen;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring(""));
        System.out.println("---------");
        System.out.println(lengthOfLongestSubstring2("abcabcbb"));
        System.out.println(lengthOfLongestSubstring2("bbbbb"));
        System.out.println(lengthOfLongestSubstring2("pwwkew"));
        System.out.println(lengthOfLongestSubstring2(""));
        System.out.println("---------");
        System.out.println(lengthOfLongestSubstring3("abcabcbb"));
        System.out.println(lengthOfLongestSubstring3("bbbbb"));
        System.out.println(lengthOfLongestSubstring3("pwwkew"));
        System.out.println(lengthOfLongestSubstring3(""));
    }
}
