package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-20 10:48
 *
 * 796. 旋转字符串
 * 给定两个字符串, A 和 B。
 *
 * A 的旋转操作就是将 A 最左边的字符移动到最右边。
 * 例如, 若 A = 'abcde'，在移动一次之后结果就是'bcdea' 。
 * 如果在若干次旋转操作之后，A 能变成B，那么返回True。
 *
 * 示例 1:
 * 输入: A = 'abcde', B = 'cdeab'
 * 输出: true
 *
 * 示例 2:
 * 输入: A = 'abcde', B = 'abced'
 * 输出: false
 * 注意：
 *
 * A 和 B 长度不超过 100。
 */
public class S_796 {
    public boolean rotateString(String s, String goal) {
        if (s == null || goal == null) {
            return false;
        }
        int len = s.length();
        if(len != goal.length()){
            return false;
        }
        char[] arr = s.toCharArray();
        char[] goalArr = goal.toCharArray();

        char[] arrNew = new char[len * 2];
        for (int i = 0; i < len; i++) {
            arrNew[i] = arr[i];
        }

        if (isEqual(arrNew, 0, goalArr)) {
            return true;
        }

        for (int i = 0; i < len; i++) {
            arrNew[len + i] = arrNew[i];
            if (isEqual(arrNew, i + 1, goalArr)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEqual(char[] arr, int i, char[] goalArr) {
        int len = goalArr.length;
        for (int k = 0; k < len; k++, i++) {
            if (!(arr[i] == goalArr[k])) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        S_796 s = new S_796();
        System.out.println(s.rotateString("abcde", "cdeab"));
        System.out.println(s.rotateString("abcde", "abced"));
    }
}
