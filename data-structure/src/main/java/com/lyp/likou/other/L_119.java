package com.lyp.likou.other;

import java.util.ArrayList;
import java.util.List;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-18 10:14
 *
 * 119. 杨辉三角 II
 * 给定一个非负索引 k，其中 k ≤ 33，返回杨辉三角的第 k 行。
 *
 *
 * 在杨辉三角中，每个数是它左上方和右上方的数的和。
 *
 * 示例:
 * 输入: 3
 * 输出: [1,3,3,1]
 */
public class L_119 {

    public static List<Integer> getRow(int rowIndex) {
        List<Integer> resultList = new ArrayList<>();
        List<Integer> lastList = new ArrayList<>();

        // 这里的 第三行，是 L_118 题的第四行
        // 所以这里是 小于等于
        for (int i = 0; i <= rowIndex; i++) {
            resultList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j == 0 || j == i){
                    resultList.add(1);
                }else{
                    resultList.add(lastList.get(j-1) + lastList.get(j));
                }
            }
            lastList = resultList;
        }
        return resultList;
    }

    public static void main(String[] args) {
        System.out.println(getRow(3));
    }


}
