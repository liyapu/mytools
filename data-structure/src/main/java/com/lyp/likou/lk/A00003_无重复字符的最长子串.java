package com.lyp.likou.lk;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 3. 无重复字符的最长子串
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长 子串 的长度。
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。注意 "bca" 和 "cab" 也是正确答案。
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
 * 请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 * 提示：
 * 0 <= s.length <= 5 * 104
 * s 由英文字母、数字、符号和空格组成
 */
public class A00003_无重复字符的最长子串 {

    public static int lengthOfLongestSubstring(String s) {
        if (Objects.isNull(s)) {
            return 0;
        }
        int maxLength = 0;
        char[] charArray = s.toCharArray();
        for (int i = 0; i < charArray.length; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = i; j < charArray.length; j++) {
                if (sb.indexOf(String.valueOf(charArray[j])) == -1) {
                    //-1表示未找到，不包含
                    sb.append(charArray[j]);
                } else {
                    break;
                }
            }
            maxLength = Math.max(maxLength, sb.length());
        }
        return maxLength;
    }


    /**
     * 什么是滑动窗口？
     * 其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。
     * 所以，我们要移动这个队列！
     *
     * 如何移动？
     * 我们只要把队列的左边的元素移出就行了，直到满足题目要求！
     * 一直维持这样的队列，找出队列出现最长的长度时候，求出解！
     */
    public static int lengthOfLongestSubstring2(String s) {
        Map<Character, Integer> seenMap = new HashMap<>();
        int maxLength = 0;
        //看做窗口的开始位置
        int left = 0;

        //i可看做右指针，不停的右移
        for (int i = 0; i < s.length(); i++) {
            if (seenMap.containsKey(s.charAt(i))) {
                left = Math.max(left, seenMap.get(s.charAt(i)) + 1);
            }
            seenMap.put(s.charAt(i), i);
            maxLength = Math.max(maxLength, i - left + 1);
        }
        return maxLength;
    }

    public static void main(String[] args) {
        String s = "abcdb";
        System.out.println(lengthOfLongestSubstring(s));
        System.out.println(lengthOfLongestSubstring2(s));
    }

}
