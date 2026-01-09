package com.lyp.likou.str;

import java.util.*;

/**
 * 力扣（LeetCode）上有一道题目叫做“寻找两个字符串的最大公共子字符串”，可以通过多种算法解决。
 * 下面我将介绍一个使用动态规划的方法来解决这个问题，这种方法的时间复杂度为O(n*m)，其中n和m分别是两个字符串的长度。
 *
 * 动态规划解法
 * ‌定义状态‌：
 * 创建一个二维数组dp，其中dp[i][j]表示字符串A的前i个字符和字符串B的前j个字符之间的最大公共子字符串的长度。
 * ‌初始化状态‌：
 *
 * 初始化所有dp[i][0]和dp[0][j]为0，因为没有字符时，最大公共子字符串长度为0。
 * ‌状态转移‌：
 * 如果A[i-1] == B[j-1]，则dp[i][j] = dp[i-1][j-1] + 1。
 * 否则，dp[i][j] = 0，因为当前字符不匹配，最大公共子字符串长度重新开始计算。
 * ‌结果‌：
 * 遍历dp数组，找到最大值及其位置，这个最大值就是最大公共子字符串的长度。同时记录下这个最大值的位置，以便找到具体的子字符串。
 */
public class MaxCommonSubStr {

    public static String longestCommonSubstring(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int[][] dp = new int[lenA + 1][lenB + 1];
        int maxLength = 0;
        int endIndex = 0;
        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (Objects.equals(A.charAt(i - 1), B.charAt(j - 1))) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        //endIndex 标记为字符串A的下标
                        endIndex = i;
                    }
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return A.substring(endIndex - maxLength, endIndex);
    }


    public static String longestCommonSubstring2(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        int maxLength = 0;
        int endIndex = 0; // 记录最长公共子串结束的位置
        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                    if (dp[i][j] > maxLength) {
                        maxLength = dp[i][j];
                        endIndex = i; // 更新最长公共子串的结束位置（在A中的位置）
                    }
                } else {
                    dp[i][j] = 0; // 当前字符不匹配，重置为0
                }
            }
        }

        // 根据endIndex和maxLength提取最长公共子串
        return A.substring(endIndex - maxLength, endIndex);
    }

    public static String longestCommonSubstring3(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();
        Map<Character, List<Integer>> ch2IndexListMap = new HashMap<>();
        for (int i = 0; i < lenA; i++) {
            char c = A.charAt(i);
            List<Integer> indexList = ch2IndexListMap.getOrDefault(c, new ArrayList<>());
            indexList.add(i);
            ch2IndexListMap.put(c,indexList);
        }
        String maxStr = "";
        for (int i = 0; i < lenB; i++) {
            char chB = B.charAt(i);
            if(ch2IndexListMap.containsKey(chB)){
                List<Integer> indexList = ch2IndexListMap.get(chB);
                for (Integer k : indexList) {
                    //每次开始从A下标开始找时，A,B 对应的下标 indexA,indexB 都要从新开始
                    int indexB = i;
                    Integer indexA = k;
                    StringBuffer sb = new StringBuffer();
                    while(indexA < lenA && indexB < lenB){
                        char ca = A.charAt(indexA);
                        char cb = B.charAt(indexB);
                        if(Objects.equals(ca,cb)){
                            sb.append(ca);
                            if(sb.length() > maxStr.length()){
                                maxStr = sb.toString();
                            }
                            indexA++;
                            indexB++;
                        }else{
                            break;
                        }
                    }
                }
            }
        }
        return maxStr;
    }

    public static String longestCommonSubstring4(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();

        String maxStr = "";
        for (int i = 0; i < lenA; i++) {
            for (int j = 0; j < lenB; j++) {
                int indexA = i;
                int indexB = j;
                StringBuffer sb = new StringBuffer();
                while (indexA < lenA && indexB < lenB) {
                    char ca = A.charAt(indexA);
                    char cb = B.charAt(indexB);
                    if (Objects.equals(ca, cb)) {
                        sb.append(ca);
                        if (sb.length() > maxStr.length()) {
                            maxStr = sb.toString();
                        }
                        indexA++;
                        indexB++;
                    } else {
                        break;
                    }
                }

            }
        }
        return maxStr;
    }


        public static void main(String[] args) {
//        String A = "a123ksdtabcdpkm";
//        String B = "bksd21abcd8uiab111";

            String A = "amnkabcl";
            String B = "bwmnzabcdh";
        System.out.println(longestCommonSubstring(A, B));
//        System.out.println(longestCommonSubstring2(A, B));
//        System.out.println(longestCommonSubstring3(A, B));
//        System.out.println(longestCommonSubstring4(A, B));
    }

}
