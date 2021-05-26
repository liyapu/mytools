package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-26 14:18
 *
 * 168. Excel表列名称
 * 给定一个正整数，返回它在 Excel 表中相对应的列名称。
 *
 * 例如，
 *
 *     1 -> A
 *     2 -> B
 *     3 -> C
 *     ...
 *     26 -> Z
 *
 *     27 -> AA
 *     28 -> AB
 *     ...
 *
 * 示例 1:
 * 输入: 1
 * 输出: "A"
 *
 * 示例 2:
 * 输入: 28
 * 输出: "AB"
 *
 * 示例 3:
 * 输入: 701
 * 输出: "ZY"
 */
public class L_168 {

    public static String convertToTitle(int columnNumber) {
        int a = 'A';

        StringBuilder sb = new StringBuilder();
        int remainder;
        while (columnNumber > 0) {
            remainder = columnNumber % 26 == 0 ? 26 : columnNumber % 26;
            sb.append((char) (a + remainder - 1));
            columnNumber = (columnNumber - remainder) / 26;
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
        System.out.println(convertToTitle(1));
        System.out.println(convertToTitle(2));
        System.out.println(convertToTitle(26));
        System.out.println(convertToTitle(27));
        System.out.println(convertToTitle(28));
    }
}
