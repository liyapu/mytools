package com.lyp.likou.str;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-19 15:11
 * 剑指 Offer 50. 第一个只出现一次的字符
 * 在字符串 s 中找出第一个只出现一次的字符。如果没有，返回一个单空格。 s 只包含小写字母。
 *
 * 示例:
 *
 * s = "abaccdeff"
 * 返回 "b"
 *
 * s = ""
 * 返回 " "
 *
 *
 * 限制：
 *
 * 0 <= s 的长度 <= 50000
 */
public class S_50 {

    /**
     * 我们可以对字符串进行两次遍历。
     *
     * 在第一次遍历时，我们使用哈希映射统计出字符串中每个字符出现的次数。
     * 在第二次遍历时，我们只要遍历到了一个只出现一次的字符，
     * 那么就返回该字符，否则在遍历结束后返回空格。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/solution/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-by-3zqv5/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public static char firstUniqChar(String s) {
        if (s == null || s.length() == 0) return ' ';
        char[] chars = s.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < chars.length; i++) {
            map.put(chars[i], map.getOrDefault(chars[i], 0) + 1);
        }

        for (int i = 0; i < chars.length; i++) {
            if (map.get(chars[i]) == 1) {
                return chars[i];
            }
        }

        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("abaccdeff"));
        System.out.println(firstUniqChar("aa"));
        System.out.println(firstUniqChar(""));
    }
}
