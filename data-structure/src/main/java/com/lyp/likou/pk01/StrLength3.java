package com.lyp.likou.pk01;

import java.util.HashMap;

/**
 */
public class StrLength3 {

    public static int lengthOfLongestSubstring(String s) {
        HashMap<Character,Integer> map = new HashMap<>();
        int answer = 0;
        for(int i = 0, j = 0; j < s.length(); j++){
            char c = s.charAt(j);
            if(map.containsKey(c)) {
                //map中同一个字母的i,可能很小，被其它字母更新了i
                i = Math.max(map.get(c), i);
            }
            //看是否要更新结果值
           answer = Math.max(answer,j - i + 1);
            //本字符的下一个下标
            map.put(c,j + 1);
        }
        return answer;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcade"));
        System.out.println(lengthOfLongestSubstring("abcdecefghikm"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
