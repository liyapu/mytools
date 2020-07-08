package com.lyp.likou.str;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个字符串，请你找出其中不含有重复字符的 最长子串 的长度。
 * 示例 1:
 *
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 *
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 *
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *
 */
public class MaxLengthSubStr {

    public static void main(String[] args) {
        testMaxLength("abcabcbb");
        testMaxLength("abcade");
        testMaxLength("abcdecefghikm");
        testMaxLength("bbbbb");
        testMaxLength("pwwkew");
        testMaxLength("wabcxabcdefgabcyfabc");
    }

    public static void testMaxLength(String str){
        System.out.println(str + " : " + lengthOfLongestSubstring1(str));
        System.out.println(str + " : " + lengthOfLongestSubstring2(str));
        System.out.println(str + " : " + lengthOfLongestSubstring3(str));
        System.out.println(str + " : " + strLengthOfLongestSubstring1(str));
        System.out.println();
    }

    public static int lengthOfLongestSubstring1(String s) {
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


    public static int lengthOfLongestSubstring2(String s) {
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


    public static int lengthOfLongestSubstring3(String s) {
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


    /**
     * 返回 不重复的字符串
     * @param s
     * @return
     */
    public static String strLengthOfLongestSubstring1(String s) {
        StringBuilder sb = new StringBuilder();
        int length = 0;
        int index = 0;
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
                //只有当找到的时候，才给 index 赋值，没有找到的时候，len 是 -1
                index = i - sb.length() + 1;
            }
        }
        return s.substring(index,index+length);
    }

}
