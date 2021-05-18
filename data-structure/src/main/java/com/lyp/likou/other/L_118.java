package com.lyp.likou.other;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-18 09:29
 *
 * 118. 杨辉三角
 * 给定一个非负整数 numRows，生成杨辉三角的前 numRows 行。
 *
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 *
 * 输入: 5
 * 输出:
 * [
 *      [1],
 *     [1,1],
 *    [1,2,1],
 *   [1,3,3,1],
 *  [1,4,6,4,1]
 * ]
 */
public class L_118 {

    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> resultList = new ArrayList<>();

        // i 控制行数
        for (int i = 1; i <= numRows; i++) {
            List<Integer> tempList = new ArrayList<>();

            // 控制每列的值的数量
            for (int j = 0; j < i; j++) {
                //处理首尾特殊值
                if (j == 0 || j == i - 1) {
                    tempList.add(1);
                } else {
                    // 减 2 才能找到上一个
                    List<Integer> lastList = resultList.get(i - 2);
                    tempList.add(lastList.get(j - 1) + lastList.get(j));
                }
            }
            resultList.add(tempList);
        }
        return resultList;
    }

    public static void main(String[] args) {
        List<List<Integer>> resultList = generate(5);
        System.out.println(resultList);
    }
}
