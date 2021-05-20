package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-20 10:13
 *
 * 917. 仅仅反转字母
 * 给定一个字符串 S，返回 “反转后的” 字符串，其中不是字母的字符都保留在原地，而所有字母的位置发生反转。
 *
 *
 *
 * 示例 1：
 * 输入："ab-cd"
 * 输出："dc-ba"
 *
 * 示例 2：
 * 输入："a-bC-dEf-ghIj"
 * 输出："j-Ih-gfE-dCba"
 *
 * 示例 3：
 * 输入："Test1ng-Leet=code-Q!"
 * 输出："Qedo1ct-eeLg=ntse-T!"
 */
public class L_917 {

    public  String reverseOnlyLetters(String s) {
        int len;
        if (null == s || (len = s.length()) == 0) {
            return s;
        }

        int i = 0;
        int j = len - 1;
        Character a,b,temp;

        char[] arr = s.toCharArray();

        while (i <= j) {
            a = arr[i];
            b = arr[j];
            if(!isLetter(a)){
                i++;
                continue;
            }else if(!isLetter(b)){
                j--;
                continue;
            }else{
                temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                // 交互位置之后，下标要移动
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    private  boolean isLetter(Character ch){
        return  (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
    }

    public static void main(String[] args) {
        L_917 cl  = new L_917();
        System.out.println(cl.reverseOnlyLetters("Test1ng-Leet=code-Q!"));
    }
}
