package com.lyp.likou.dynamic;

import java.util.Objects;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-31 16:00
 * 63. 不同路径 II
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为“Start” ）。
 *
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为“Finish”）。
 *
 * 现在考虑网格中有障碍物。那么从左上角到右下角将会有多少条不同的路径？
 *
 *
 *
 * 网格中的障碍物和空位置分别用 1 和 0 来表示。
 *
 * 示例 1：
 * 输入：obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
 * 输出：2
 * 解释：
 * 3x3 网格的正中间有一个障碍物。
 * 从左上角到右下角一共有 2 条不同的路径：
 * 1. 向右 -> 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右 -> 向右
 *
 * 示例 2：
 * 输入：obstacleGrid = [[0,1],[0,0]]
 * 输出：1
 */
public class D_63 {

    /**
     * 参考 D_62
     * 遇到障碍物，最优解为 0
     * @param obstacleGrid
     * @return
     */
    public static int uniquePathsWithObstacles(int[][] obstacleGrid) {
        if (Objects.isNull(obstacleGrid)) {
            return 0;
        }
        int row = obstacleGrid.length;
        int column = obstacleGrid[0].length;

        int[][] dp = new int[row][column];

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (Objects.equals(1, obstacleGrid[i][j])) {
                    dp[i][j] = 0;
                } else if (i == 0 && j != 0) {
                    dp[i][j] = dp[i][j - 1];
                } else if (j == 0 && i != 0) {
                    dp[i][j] = dp[i - 1][j];
                } else if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
                }
            }
        }
        return dp[row - 1][column - 1];
    }

    public static void main(String[] args) {
        int[][] obstacleGrid1 = {{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid1));

        int[][] obstacleGrid2 = {{0, 1}, {0, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid2));

        int[][] obstacleGrid3 = {{1, 0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid3));

        int[][] obstacleGrid4 = {{0,0},{1,1},{0,0}};
        System.out.println(uniquePathsWithObstacles(obstacleGrid4));

    }
}
