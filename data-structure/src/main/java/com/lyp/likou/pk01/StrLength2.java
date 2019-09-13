package com.lyp.likou.pk01;

import java.util.HashSet;
import java.util.Set;

/**
 */
public class StrLength2 {

    public static int lengthOfLongestSubstring(String s) {
        int n = s.length();
        Set<Character> set = new HashSet<>();
        int ans = 0, i = 0, j = 0;
        while (i < n && j < n) {
            if (!set.contains(s.charAt(j))){
                set.add(s.charAt(j++));
                ans = Math.max(ans, j - i);
            }else {
                //从开始位置一直删除到出现重复字符位置
                //同StringBuilder，打断点可见
                set.remove(s.charAt(i++));
            }
        }
        return ans;
     }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcade"));
        System.out.println(lengthOfLongestSubstring("abcdecefghikm"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
