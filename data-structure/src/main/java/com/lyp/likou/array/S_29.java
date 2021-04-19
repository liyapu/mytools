package com.lyp.likou.array;

import java.util.Arrays;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-19 09:47
 *
 * 剑指 Offer 29. 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 *
 *
 * 示例 1：
 *
 * 输入：matrix = [
 *               [1,2,3],
 *               [4,5,6],
 *               [7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [
 *        [1,2,3,4],
 *        [5,6,7,8],
 *        [9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 * 注意：本题与主站 54 题相同：https://leetcode-cn.com/problems/spiral-matrix/
 */
public class S_29 {

    /**
     * 按层模拟
     * 可以将矩阵看成若干层，首先打印最外层的元素，其次打印次外层的元素，直到打印最内层的元素。
     *
     * 定义矩阵的第 kk 层是到最近边界距离为 kk 的所有顶点。例如，下图矩阵最外层元素都是第 11 层，次外层元素都是第 22 层，剩下的元素都是第 33 层。
     *
     *
     * [[1, 1, 1, 1, 1, 1, 1],
     *  [1, 2, 2, 2, 2, 2, 1],
     *  [1, 2, 3, 3, 3, 2, 1],
     *  [1, 2, 2, 2, 2, 2, 1],
     *  [1, 1, 1, 1, 1, 1, 1]]
     * 对于每层，从左上方开始以顺时针的顺序遍历所有元素。假设当前层的左上角位于 (\textit{top}, \textit{left})(top,left)，右下角位于 (\textit{bottom}, \textit{right})(bottom,right)，按照如下顺序遍历当前层的元素。
     *
     * 从左到右遍历上侧元素，依次为 (\textit{top}, \textit{left})(top,left) 到 (\textit{top}, \textit{right})(top,right)。
     *
     * 从上到下遍历右侧元素，依次为 (\textit{top} + 1, \textit{right})(top+1,right) 到 (\textit{bottom}, \textit{right})(bottom,right)。
     *
     * 如果 \textit{left} < \textit{right}left<right 且 \textit{top} < \textit{bottom}top<bottom，则从右到左遍历下侧元素，依次为 (\textit{bottom}, \textit{right} - 1)(bottom,right−1) 到 (\textit{bottom}, \textit{left} + 1)(bottom,left+1)，以及从下到上遍历左侧元素，依次为 (\textit{bottom}, \textit{left})(bottom,left) 到 (\textit{top} + 1, \textit{left})(top+1,left)。
     *
     * 遍历完当前层的元素之后，将 \textit{left}left 和 \textit{top}top 分别增加 11，将 \textit{right}right 和 \textit{bottom}bottom 分别减少 11，进入下一层继续遍历，直到遍历完所有元素为止。
     *
     *
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/shun-shi-zhen-da-yin-ju-zhen-by-leetcode-solution/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param matrix
     * @return
     */
    public static int[] spiralOrder(int[][] matrix) {
        if (null == matrix || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        //行数，列数
        int rows = matrix.length, columns = matrix[0].length;
        //定义结果数组
        int[] orders = new int[rows * columns];
        int index = 0;

        //声明遍历 变量，减一是为了可以在数组中的下标访问到
        int left = 0, right = columns - 1, top = 0, bottom = rows - 1;

        //遍历 列，用 column
        //遍历 行，用 row
        //数组 matrix访问，用 left,right ,top ,bottom 加 column,row 访问
        //加1，减1，让其不重复访问
        while (left <= right && top <= bottom) {
            //遍历列，用 column, 用 left right 赋值
            for (int column = left; column <= right; column++) {
                orders[index++] = matrix[left][column];
            }
            //遍历行，用 row,用 top bottom 赋值
            //此处加 1，因为上一个元素已经访问过了
            for (int row = top + 1; row <= bottom; row++) {
                orders[index++] = matrix[row][right];
            }
            //上面遍历完之后，可能已经结束了，需要判断，并且要用 小于
            if (left < right && top < bottom) {
                //遍历列 用 倒序， 减 1 是因为最后一个元素已经访问过了，大于 left，剩一个元素没有访问
                for (int column = right - 1; column > left; column--) {
                    orders[index++] = matrix[bottom][column];
                }
                // 遍历行，从 bottom 开始，承接上 大于 left 没有访问到的那个元素
                for (int row = bottom; row > top; row--) {
                    orders[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return orders;
    }

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };
        int[] orders = spiralOrder(matrix);
        System.out.println(Arrays.toString(orders));
    }
}
