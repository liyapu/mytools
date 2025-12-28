package com.lyp.likou.my;

import java.util.*;

/**
 * 49. 字母异位词分组
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 *
 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 解释：
 * 在 strs 中没有字符串可以通过重新排列来形成 "bat"。
 * 字符串 "nat" 和 "tan" 是字母异位词，因为它们可以重新排列以形成彼此。
 * 字符串 "ate" ，"eat" 和 "tea" 是字母异位词，因为它们可以重新排列以形成彼此。
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * 1 <= strs.length <= 104
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class A00049_字母异位词分组 {

    /**
     * 方法一：排序
     * 由于互为字母异位词的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
     * 故可以将排序之后的字符串作为哈希表的键。
     *
     * 总结：
     * 1. 对于对次序不敏感的题，可以使用排序后再对比
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        if (Objects.isNull(strs) || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> str2StrListMap = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String newStr = new String(charArray);

            List<String> strList = str2StrListMap.getOrDefault(newStr, new ArrayList<>());
            //这里加入的是原始字符串
            strList.add(str);

            //这里使用 排序后的新字符串作为key
            str2StrListMap.put(newStr, strList);
        }

        return new ArrayList<>(str2StrListMap.values());
    }

    /**
     * 性能较第一种写法低
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams2(String[] strs) {
        if (Objects.isNull(strs) || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> str2StrListMap = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String newStr = new String(charArray);

            str2StrListMap.computeIfAbsent(newStr,k -> new ArrayList<>()).add(str);
        }

        return new ArrayList<>(str2StrListMap.values());
    }

    /**
     * 性能和第一种一样
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams3(String[] strs) {
        if (Objects.isNull(strs) || strs.length == 0) {
            return new ArrayList<>();
        }
        Map<String, List<String>> str2StrListMap = new HashMap<>();
        for (String str : strs) {
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            String newStr = new String(charArray);

            if (str2StrListMap.containsKey(newStr)) {
                str2StrListMap.get(newStr).add(str);
            } else {
                List<String> strList = new ArrayList<>();
                strList.add(str);
                str2StrListMap.put(newStr, strList);
            }
        }

        return new ArrayList<>(str2StrListMap.values());
    }

    /**
     * 方法二：计数
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，
     * 故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     *
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26 的数组记录每个字母出现的次数。
     * 需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同。
     *
     * 这种写法，性能更低
     */
    public static List<List<String>> groupAnagrams100(String[] strs) {
        if (Objects.isNull(strs) || strs.length == 0) {
            return new ArrayList<>();
        }

        Map<String, List<String>> str2StrListMap = new HashMap<>();
        for (String str : strs) {
            //都是小写，最多26个小写字母，统计其个数，下标是字母，值是出现的次数
            int[] counts = new int [26];
            int length = str.length();
            for (int i = 0; i < length; i++) {
                counts[str.charAt(i)-'a']++;
            }

            // 将每个出现次数大于 0 的字母和出现次数按顺序拼接成字符串，作为哈希表的键
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < counts.length; i++) {
                if(counts[i]==0){
                    continue;
                }
                sb.append((char)('a'+i));
                sb.append(counts[i]);
            }

            String newStr = sb.toString();
            System.out.println(newStr);

            List<String> strList = str2StrListMap.getOrDefault(newStr, new ArrayList<>());
            strList.add(str);
            str2StrListMap.put(newStr, strList);
        }
        return new ArrayList<>(str2StrListMap.values());
    }


    public static void main(String[] args) {
        String[] strs = new String[]{"eat", "tea", "tan", "ate", "nat", "bat"};
        System.out.println(groupAnagrams100(strs));
    }
}
