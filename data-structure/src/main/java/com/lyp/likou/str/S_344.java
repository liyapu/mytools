package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-13 09:27
 *
 * 344. 反转字符串
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 char[] 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 * 你可以假设数组中的所有字符都是 ASCII 码表中的可打印字符。
 *
 *
 * 示例 1：
 * 输入：["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 * 示例 2：
 * 输入：["H","a","n","n","a","h"]
 * 输出：["h","a","n","n","a","H"]
 */
public class S_344 {

    public static void reverseString(char[] s) {
        int len;
        if (s == null || (len = s.length) <= 1) {
            return;
        }

        char temp;
        for (int i = 0, j = len - 1; i < j; i++, j--) {
            temp = s[i];
            s[i] = s[j];
            s[j] = temp;

        }
    }

    public static void main(String[] args) {
        char[] s = {'h', 'e', 'l', 'l', 'o'};
        System.out.println(s);
        reverseString(s);
        System.out.println(s);

    }


}
