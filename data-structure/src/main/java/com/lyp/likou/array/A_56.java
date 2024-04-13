package com.lyp.likou.array;

import java.util.Arrays;

/**
 * @author liyapu
 * @date 2024-03-23 19:37
 * @description 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 *
 * 示例 1：
 * 输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
 * 输出：[[1,6],[8,10],[15,18]]
 * 解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
 *
 * 示例 2：
 * 输入：intervals = [[1,4],[4,5]]
 * 输出：[[1,5]]
 * 解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。
 *
 * 示例 3：
 * 输入：intervals = [[1,4],[0,4]]
 * 输出：[[0,5]]
 * 解释：区间 [1,4] 和 [0,4] 可被视为重叠区间。
 * 提示：
 * 1 <= intervals.length <= 104
 * intervals[i].length == 2
 * 0 <= starti <= endi <= 104
 */
public class A_56 {

    public static int[][] merge(int[][] intervals) {
        int[] removeArr = new int[intervals.length];
        int[][] result = new int[intervals.length][2];
        int length = intervals.length;

        if (length == 0 || length == 1) {
            return intervals;
        }

        for (int i = 0; i < length; i++) {
            int j = i + 1;
            if (j >= length) {
                continue;
            }
            for (; j < length; j++) {
                if (!(intervals[j][1] < intervals[i][0] || intervals[j][0] > intervals[i][1])) {
                    removeArr[i] = 1;
                    intervals[j][0] = Math.min(intervals[i][0], intervals[j][0]);
                    intervals[j][1] = Math.max(intervals[i][1], intervals[j][1]);
                    break;
                }
            }
        }

        int resultIndex = 0;
        for (int i = 0; i < intervals.length; i++) {
            if (removeArr[i] == 0) {
                result[resultIndex++] = intervals[i];
            }
        }
        return Arrays.copyOfRange(result, 0, resultIndex);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {4, 5}})));
        System.out.println(Arrays.deepToString(merge(new int[][]{{1, 4}, {0, 4}})));
    }
}
