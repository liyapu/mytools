package com.lyp.likou.pk01;

/**
 */
public class StrLength1 {

    public static int lengthOfLongestSubstring(String s) {
        StringBuilder sb = new StringBuilder();
        int length = 0;
        for(int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            int len = 0;
            //字符出现过，截取剩余的
            if((len = sb.indexOf(String.valueOf(c))) >= 0){
                sb = new StringBuilder(sb.substring(len+1,sb.length()));
            }
            //拼接此新字符
            sb.append(c);
            //长度最长，更新最新长度值
            if(sb.length() > length){
                length = sb.length();
            }
        }
        return length;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("abcade"));
        System.out.println(lengthOfLongestSubstring("abcdecefghikm"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
}
