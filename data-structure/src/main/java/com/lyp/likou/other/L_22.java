package com.lyp.likou.other;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-25 14:33
 *
 *  22. 括号生成
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。

 *
 * 示例 1：
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：["()"]
 *
 *
 * 提示：
 * 1 <= n <= 8
 */
public class L_22 {

    /**
     * 回溯算法（深度优先遍历）+ 广度优先遍历（Java）
     * https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backTrack(result,n,n,"");
        return result;
    }

    private static void backTrack(List<String> result, int left, int right, String s) {
        if(left == 0 && right == 0){
            result.add(s);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if(left > right){
            return;
        }
        if(left > 0){
            backTrack(result,left-1,right,s+"(");
        }
        if(right > 0){
            backTrack(result,left,right-1,s+")");
        }
    }


    public static void main(String[] args) {
        generateParenthesis(3).forEach(s -> System.out.println(s));
    }

}
