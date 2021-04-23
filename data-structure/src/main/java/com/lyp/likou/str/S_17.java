package com.lyp.likou.str;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-23 09:47
 * 17. 电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 *
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * 示例 2：
 *
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *
 *
 * 提示：
 *
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字
 */
public class S_17 {

    /**
     * 回溯解法
     * @param digits
     * @return
     */
    public static List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();

        Map<Character, String> map = new HashMap<>();
        map.put('2', "abc");
        map.put('3', "def");
        map.put('4', "ghi");
        map.put('5', "jkl");
        map.put('6', "mno");
        map.put('7', "pqrs");
        map.put('8', "tuv");
        map.put('9', "wxyz");
        if (null == digits || "".equals(digits) || " ".equals(digits)) {
            return result;
        }
        char[] chars = digits.toCharArray();
        StringBuilder sb = new StringBuilder();
        backTrack(result, chars, map, 0,sb);

        return result;
    }

    private static void backTrack(List<String> result, char[] chars, Map<Character, String> map, int i, StringBuilder sb) {
        if (i >= chars.length) {
            //停止的条件
            result.add(sb.toString());
            return;
        }

        //每轮需要做的事情
        String str = map.get(chars[i]);
        char[] strChars = str.toCharArray();
        for (int j = 0; j < strChars.length; j++) {
            sb.append(strChars[j]);
            backTrack(result, chars, map, i + 1, sb);
            //这一步要删除 i
            sb.deleteCharAt(i);
        }
    }

    public static void main(String[] args) {
        letterCombinations("23").forEach(s -> System.out.print(s + "\t"));
    }
}
