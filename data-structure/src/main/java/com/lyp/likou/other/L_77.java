package com.lyp.likou.other;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-26 09:28
 *
 * 77. 组合
 * 给定两个整数 n 和 k，返回 1 ... n 中所有可能的 k 个数的组合。
 *
 * 示例:
 * 输入: n = 4, k = 2
 * 输出:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class L_77 {


    public static List<List<Integer>> combine(int n, int k) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        backTrackNum(result, path, n, k, 1);
        return result;
    }

    private static void backTrackNum(List<List<Integer>> result, List<Integer> path, int n, int k, int begin) {
        // 递归终止条件是：path 的长度等于 k
        if (path.size() == k) {
            result.add(new ArrayList<>(path));
            return;
        }

        // 遍历可能的搜索起点
        //  剪枝 只有这里 i <= n - (k - path.size()) + 1 与参考代码 1 不同
        for (int i = begin; i <= n; i++) {
            // 向路径变量里添加一个数
            path.add(i);
            // 下一轮搜索，设置的搜索起点要加 1，因为组合数理不允许出现重复的元素
            backTrackNum(result, path, n, k, i + 1);
            // 重点理解这里：深度优先遍历有回头的过程，因此递归之前做了什么，递归之后需要做相同操作的逆向操作
            path.remove(path.size() - 1);
        }
    }

    public static void main(String[] args) {
        combine(4, 2).stream().forEach(s -> System.out.println(s));
    }
}
