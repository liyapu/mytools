package com.lyp.likou.str;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-13 09:38
 *
 * 387. 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *
 * 示例：
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *
 *
 * 提示：你可以假定该字符串只包含小写字母。
 */
public class S_387 {

    public static int firstUniqChar(String s) {
        int len;
        if(s == null || (len = s.length()) == 0){
            return  -1;
        }
        Map<Character,Integer> frequency = new HashMap<>();
        char[] arr = s.toCharArray();

        for (char c : arr) {
            frequency.put(c,frequency.getOrDefault(c,0)+1);
        }

        for (int i = 0; i < len; i++) {
            if(frequency.get(arr[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));
    }
}
