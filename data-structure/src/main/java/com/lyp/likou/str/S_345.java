package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-17 10:02
 *
 * 345. 反转字符串中的元音字母
 * 编写一个函数，以字符串作为输入，反转该字符串中的元音字母。
 *
 *
 *
 * 示例 1：
 * 输入："hello"
 * 输出："holle"
 *
 * 示例 2：
 * 输入："leetcode"
 * 输出："leotcede"
 *
 *
 * 提示：
 *
 * 元音字母不包含字母 "y" 。
 */
public class S_345 {

    /**
     * 下面的每个循环和判断，都要判断 i <= j
     * @param s
     * @return
     */
    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;
        int i = 0, j = len - 1;

        while (i <= j) {

            while (i <= j) {
                char c = arr[i];
                if (isVowel(c)) {
                    break;
                }
                i++;
            }

            while (i <= j) {
                char c = arr[j];
                if (isVowel(c)) {
                    break;
                }
                j--;
            }
            if (i <= j && isVowel(arr[i]) && isVowel(arr[j])) {
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;

                // 两个符合要求的位置，反转之后，需要 变动位置
                i++;
                j--;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char c) {
        return c == 'a' || c == 'o' || c == 'e' || c == 'i' || c == 'u' ||
                c == 'A' || c == 'O' || c == 'E' || c == 'I' || c == 'U';

    }

    /**
     * 只判断一次  i < j
     * @param s
     * @return
     */
    public String reverseVowels2(String s) {
        char[] arr = s.toCharArray();
        int len = arr.length;

        int i = 0;
        int j = len - 1;
        while (i < j) {
            char a = arr[i];
            char b = arr[j];

            if (!isVowel(a)) {
                //a字符不是，则继续向下找
                i++;
                continue;
            } else if (!isVowel(b)) {
                j--;
                continue;
            } else {
                // a b 都是符合要求的
                // 反转时，不可以使用 a b ,要用 引用类型的数组
                char temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }

            //最后要 改变位置
            i++;
            j--;
        }
        return new String(arr);
    }

    public static void main(String[] args) {
        S_345 s = new S_345();
        System.out.println(s.reverseVowels("hello"));
        System.out.println(s.reverseVowels("leetcode"));
        System.out.println("----------");

        System.out.println(s.reverseVowels2("hello"));
        System.out.println(s.reverseVowels2("leetcode"));
    }
}
