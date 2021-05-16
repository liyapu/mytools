package com.lyp.likou.str;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-13 09:56
 *
 * 389. 找不同
 * 给定两个字符串 s 和 t，它们只包含小写字母。
 *
 * 字符串 t 由字符串 s 随机重排，然后在随机位置添加一个字母。
 *
 * 请找出在 t 中被添加的字母。
 *
 *
 *
 * 示例 1：
 * 输入：s = "abcd", t = "abcde"
 * 输出："e"
 * 解释：'e' 是那个被添加的字母。
 *
 * 示例 2：
 * 输入：s = "", t = "y"
 * 输出："y"
 *
 * 示例 3：
 * 输入：s = "a", t = "aa"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ae", t = "aea"
 * 输出："a"
 */
public class S_389 {

    /**
     * 复杂度高
     * @param s
     * @param t
     * @return
     */
    public static char findTheDifference(String s, String t) {
        char[] sArr = s.toCharArray();
        char[] tArr = t.toCharArray();

        Map<Character, Integer> sMap = new HashMap<>();
        for (char c : sArr) {
            sMap.put(c, sMap.getOrDefault(c, 0) + 1);
        }

        Map<Character, Integer> tMap = new HashMap<>();
        for (char c : tArr) {
            tMap.put(c, tMap.getOrDefault(c, 0) + 1);
        }

        // t 多出一个字符，此处需要遍历  tMap
        for (Map.Entry<Character, Integer> entry : tMap.entrySet()) {
            Character key = entry.getKey();
            //getOrDefault 可能返回 null,这里要设置一个默认值
            if (entry.getValue() != sMap.getOrDefault(key, 0)) {
                return key;
            }
        }
        return ' ';
    }

    /**
     * 首先遍历字符串 s，对其中的每个字符都将计数值加 1；然后遍历字符串 t，对其中的每个字符都将计数值减 1。
     * 当发现某个字符计数值为负数时，说明该字符在字符串 t 中出现的次数大于在字符串 s 中出现的次数，因此该字符为被添加的字符
     */
    public static char findTheDifference2(String s, String t) {
        //定义为 int 类型的数组
        int[] arr = new int[26];

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            arr[c - 'a']++;
        }

        for (int i = 0; i < t.length(); i++) {
            char c = t.charAt(i);
            arr[c - 'a']--;
            if (arr[c - 'a'] < 0) {
                return c;
            }
        }
        return ' ';
    }

    public static void main(String[] args) {
        System.out.println(findTheDifference("abcd", "abcde"));
        System.out.println(findTheDifference("", "y"));
        System.out.println(findTheDifference("a", "aa"));
        System.out.println(findTheDifference("ae", "aea"));
        System.out.println("-----------");
        System.out.println(findTheDifference2("abcd", "abcde"));
        System.out.println(findTheDifference2("", "y"));
        System.out.println(findTheDifference2("a", "aa"));
        System.out.println(findTheDifference2("ae", "aea"));
    }
}
