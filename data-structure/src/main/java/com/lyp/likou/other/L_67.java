package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-11 16:44
 *
 * 67. 二进制求和
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 *
 * 输入为 非空 字符串且只包含数字 1 和 0。
 *
 *
 *
 * 示例 1:
 * 输入: a = "11", b = "1"
 * 输出: "100"
 *
 * 示例 2:
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *
 *
 * 提示：
 *
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 */
public class L_67 {

    public static String addBinary(String a, String b) {
        //倒序遍历
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        int sum = 0;
        StringBuilder sb = new StringBuilder();

        //条件是 大于等于0，等于0时也要能进入
        while (i >= 0 || j >= 0 || carry > 0) {
            int aValue = 0;
            int bValue = 0;
            if (i >= 0) {
                aValue = Integer.parseInt(a.charAt(i--) + "");
            }
            if (j >= 0) {
                bValue = Integer.parseInt(b.charAt(j--) + "");
            }
            sum = aValue + bValue + carry;
            if (sum >= 2) {
                carry = sum / 2;
                sum = sum % 2;
            } else {
                carry = 0;
            }
            sb.append(sum);
        }
        return sb.reverse().toString();
    }

    public static void main(String[] args) {
//        System.out.println(addBinary("11","1"));
        System.out.println(addBinary("1010", "1011"));
    }
}

