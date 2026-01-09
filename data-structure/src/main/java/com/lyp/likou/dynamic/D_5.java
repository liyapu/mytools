package com.lyp.likou.dynamic;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-17 09:15
 *
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 *
 * 示例 1：
 *
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *
 * 示例 3：
 * 输入：s = "a"
 * 输出："a"
 *
 * 示例 4：
 * 输入：s = "ac"
 * 输出："a"
 *
 * 提示：
 *
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母（大写和/或小写）组成
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class D_5 {

    /**
     * 暴力破解
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     *
     * @param s
     * @return
     */
    public static String longestPalindrome(String s) {
        if (null == s) {
            return "";
        }
        int len = s.length();
        // s.charAt(i) 每次都会检查数组下标越界，因此先转换成字符数组
        char[] chars = s.toCharArray();

        int maxLen = 1;
        int begin = 0;
        //遍历所有以 i 开始，以 j 结束的字符串，
        for (int i = 0; i < len - 1; i++) {
            for (int j = i + 1; j < len; j++) {
                if (j - i + 1 > maxLen && isHuiWen(chars, i, j)) {
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }

    // 判断 chars[left..right] 子串是否是 回文
    private static boolean isHuiWen(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left] != chars[right]) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    /**
     * 动态规划
     * https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     * 即当子串 s[i..j] 的长度等于 2 或者等于 3 的时候，其实只需要判断一下头尾两个字符是否相等就可以直接下结论了
     *
     * 注意事项：总是先得到小子串的回文判定，然后大子串才能参考小子串的判断结果，即填表顺序很重要。
     * @param s
     * @return
     */
    public static String longestPalindrome_A(String s) {
        if (null == s) {
            return "";
        }
        int len = s.length();
        if (len == 1) {
            return s;
        }

        char[] chars = s.toCharArray();
        int maxLen = 1;
        int begin = 0;


        Boolean[][] dp = new Boolean[len + 1][len + 1];

        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }

        for (int j = 1; j < len; j++) {
            for (int i = 0; i < j; i++) {
                if (chars[i] != chars[j]) {
                    dp[i][j] = false;
                } else {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j-i+1;
                    begin = i;
                }
            }
        }

        return s.substring(begin, begin + maxLen);
    }

    public static void main(String[] args) {
        String s0 = "aaaa";
        String s1 = "cbbd";
        String s2 = "a";
        String s3 = "ac";
        String s4 = "babad";
        System.out.println(longestPalindrome(s0));
        System.out.println(longestPalindrome(s1));
        System.out.println(longestPalindrome(s2));
        System.out.println(longestPalindrome(s3));
        System.out.println(longestPalindrome(s4));
        System.out.println();

        System.out.println(longestPalindrome_A(s0));
        System.out.println(longestPalindrome_A(s1));
        System.out.println(longestPalindrome_A(s2));
        System.out.println(longestPalindrome_A(s3));
        System.out.println(longestPalindrome_A(s4));
    }
}
