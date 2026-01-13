package com.lyp.likou.lk;

import java.util.*;

/**
 * 438. 找到字符串中所有字母异位词
 * 给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
 *
 * 示例 1:
 * 输入: s = "cbaebabacd", p = "abc"
 * 输出: [0,6]
 * 解释:
 * 起始索引等于 0 的子串是 "cba", 它是 "abc" 的异位词。
 * 起始索引等于 6 的子串是 "bac", 它是 "abc" 的异位词。
 *
 * 示例 2:
 * 输入: s = "abab", p = "ab"
 * 输出: [0,1,2]
 * 解释:
 * 起始索引等于 0 的子串是 "ab", 它是 "ab" 的异位词。
 * 起始索引等于 1 的子串是 "ba", 它是 "ab" 的异位词。
 * 起始索引等于 2 的子串是 "ab", 它是 "ab" 的异位词。
 *
 * 提示:
 * 1 <= s.length, p.length <= 3 * 104
 * s 和 p 仅包含小写字母
 */
public class A00438_找到字符串中所有字母异位词 {

    /**
     * 字符串判断，可以使用 int[] char数组，c-'a' 准则找到具体的字符
     * 或者 Map<Character,Integer>
     * @param s
     * @param p
     * @return
     */
    public static List<Integer> findAnagrams(String s, String p) {
        int sLength = s.length();

        //先统计分析结果字符串
        int pLength = p.length();
        int[] pSeen = new int[26];
        for (int i = 0; i < pLength; i++) {
            char c = p.charAt(i);
            //统计字符出现的次数，多次出现，计数加1
            pSeen[c - 'a']++;
        }
//        System.out.println(Arrays.toString(pSeen));

        List<Integer> resultList = new ArrayList<>();
        int[] sSeen = new int[26];
        int left = 0;
        for (int i = 0; i < sLength; i++) {
            char c = s.charAt(i);
            sSeen[c - 'a']++;
            //不够窗口长度，继续加
            if (i < pLength - 1) {
                continue;
            }
            //计算最左边界值，下面两处都需要使用
            left = i - pLength + 1;
            //Arrays.equals 巧妙判断是否相等，比使用HashMap方便多了
            if (Arrays.equals(pSeen, sSeen)) {
                resultList.add(left);
            }
            //最左边减一，相当于移出了窗口，然后进行下一轮匹配
            sSeen[s.charAt(left) - 'a']--;

        }
        return resultList;
    }

    /**
     * 核心思路
     * 链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string/solutions/2969498/liang-chong-fang-fa-ding-chang-hua-chuan-14pd/
     */
    public static List<Integer> findAnagrams2(String s, String p) {
        // 统计 p 的每种字母的出现次数
        int[] cntP = new int[26];
        for (char c : p.toCharArray()) {
            cntP[c - 'a']++; // 统计 p 的字母
        }

        List<Integer> ans = new ArrayList<>();
        int[] cntS = new int[26]; // 统计 s 的长为 p.length() 的子串 s' 的每种字母的出现次数
        for (int right = 0; right < s.length(); right++) {
            cntS[s.charAt(right) - 'a']++; // 右端点字母进入窗口
            int left = right - p.length() + 1;
            if (left < 0) { // 窗口长度不足 p.length()
                continue;
            }
            if (Arrays.equals(cntS, cntP)) { // s' 和 p 的每种字母的出现次数都相同
                ans.add(left); // s' 左端点下标加入答案
            }
            cntS[s.charAt(left) - 'a']--; // 左端点字母离开窗口
        }
        return ans;
    }

    public static void main(String[] args) {
        String s = "cbaebabacd", p = "abc";
        System.out.println(findAnagrams(s, p));
    }

}
