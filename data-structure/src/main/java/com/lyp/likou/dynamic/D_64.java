package com.lyp.likou.dynamic;

import java.util.Objects;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-31 16:32
 *
 * 64. 最小路径和
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 *
 * 说明：每次只能向下或者向右移动一步。
 *
 *
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 */
public class D_64 {
    public static int minPathSum(int[][] grid) {
        if (Objects.isNull(grid)) {
            return 0;
        }
        int row = grid.length;
        int column = grid[0].length;

        int[][] dp = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = grid[0][0];
                } else if (i == 0) {
                    dp[i][j] = dp[i][j - 1] + grid[i][j];
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] + grid[i][j];
                } else {
                    dp[i][j] = Math.min(dp[i-1][j],dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[row-1][column-1];
    }

    public static void main(String[] args) {
        int [][] grid1 = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(minPathSum(grid1));

        int [][] grid2 = {{1,2,3},{4,5,6}};
        System.out.println(minPathSum(grid2));
    }
}
