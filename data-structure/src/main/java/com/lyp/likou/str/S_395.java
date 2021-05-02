package com.lyp.likou.str;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-27 09:30
 *
 * 395. 至少有 K 个重复字符的最长子串
 * 给你一个字符串 s 和一个整数 k ，请你找出 s 中的最长子串， 要求该子串中的每一字符出现次数都不少于 k 。返回这一子串的长度。
 *
 *
 *
 * 示例 1：
 * 输入：s = "aaabb", k = 3
 * 输出：3
 * 解释：最长子串为 "aaa" ，其中 'a' 重复了 3 次。
 *
 * 示例 2：
 * 输入：s = "ababbc", k = 2
 * 输出：5
 * 解释：最长子串为 "ababb" ，其中 'a' 重复了 2 次， 'b' 重复了 3 次。
 *
 *
 * 提示：
 * 1 <= s.length <= 104
 * s 仅由小写英文字母组成
 * 1 <= k <= 105
 */
public class S_395 {

    public static int longestSubstring(String s, int k) {
        //统计每个字符出现的次数
        Map<Character, Integer> char$Times = new HashMap<>();
        char[] chars = s.toCharArray();

        for (char c : chars) {
            char$Times.put(c, char$Times.getOrDefault(c, 0) + 1);
        }


        for (char c : chars) {
            if (char$Times.get(c) < k) {
                int ans = 0;
                String[] split = s.split(String.valueOf(c));
                for (String subStr : split) {
                    //递归
                    ans = Math.max(ans, longestSubstring(subStr, k));
                }
                return ans;
            }
        }

        //如果都不少于 k 的话，原字符串长度就是 结果
        //如果每个字符都都满足大于k,会走到此处，当前串满足条件，直接返回字符串长度即可
        return s.length();
    }

    public static void main(String[] args) {
        System.out.println(longestSubstring("abbc", 2));
//        System.out.println(longestSubstring("aaabb", 3));
//        System.out.println(longestSubstring("ababbc", 2));
    }
}
