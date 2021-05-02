package com.lyp.likou.str;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-02 21:39
 *
 * 14. 最长公共前缀
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 *
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 *
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 *
 *
 * 提示：
 * 0 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 */
public class S_14 {

    public static String longestCommonPrefix(String[] strs) {
        if(null == strs || strs.length == 0) return "";

        //先假定第一个就是字符串 就是结果
        String base = strs[0];
        String common = base;

        for (int i = 1; i < strs.length; i++) {
            int k = 0;
            String newCommon;
            while (k < base.length() && k < strs[i].length()){
               if(base.charAt(k) != strs[i].charAt(k)) {
                   break;
               }
               k++;
            }
            if(k == 0){
                newCommon = "";
            }else{
                newCommon = base.substring(0,k);
            }

            if(common.length() > newCommon.length()){
                common = newCommon;
                if(common.length() == 0){
                    break;
                }
            }
        }
        return common;
    }


    public static void main(String[] args) {
        String [] strs =  {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));

        String [] strs2 =  {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs2));
    }
}
