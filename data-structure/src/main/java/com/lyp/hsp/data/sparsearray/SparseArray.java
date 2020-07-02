package com.lyp.hsp.data.sparsearray;

import org.apache.commons.lang3.ArrayUtils;

import java.util.Objects;

/**
 * 稀疏数组
 *     原始数组 ---> 稀疏数组
 *     稀疏数组 ---> 原始数组
 *
 *    当一个数组中大部分元素为0，或者为同一个值的数组时，可以使用稀疏数组来保存该数组
 *
 *    稀疏数组的处理方法是:
 *       1) 记录数组一共有几行几列，有多少个不同的值
 *       2) 把具有不同值的元素的行列及值记录在一个小规模的数组中，从而缩小程序的规模
 */
public class SparseArray {

    //指定的特殊值
    static final int SPECIAL_VAL = 0;

    public static void main(String[] args) {
        int[][] twoArr = new int[][]{
                {0, 0, 0, 9, 0},
                {0, 0, 8, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 2, 5, 0, 0},
                {1, 0, 0, 0, 0},
                {0, 3, 4, 5, 0},
                {0, 0, 7, 6, 0},
        };

        outputTwoArr(twoArr, "第一次输出原始数组");

        //原始数组 转  稀疏数组
        int[][] sparseArr = twoArrToSparseArr(twoArr);
        outputTwoArr(sparseArr, "稀疏数组");

        //稀疏数组 恢复 原始数组
        int[][] twoArrRecover = sparseArrToTwoArr(sparseArr);
        outputTwoArr(twoArrRecover, "恢复的 原始数组");
    }

    //输出二维数组
    public static void outputTwoArr(int[][] twoArr, String msg) {
        if (ArrayUtils.isEmpty(twoArr)) {
            return;
        }
        System.out.println("----" + msg + "----");

        for (int i = 0; i < twoArr.length; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                System.out.print(twoArr[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     *  获取 二维数组中，非特殊值的个数
     * 比如特殊值是 0，或者 其它具有很多重复的数据
     */
    public static int getNonSpecialValueCount(int[][] twoArr, int specialVal) {
        int sum = 0;
        if (null == twoArr || twoArr.length == 0) {
            return sum;
        }
        for (int i = 0; i < twoArr.length; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                if (!Objects.equals(specialVal, twoArr[i][j])) {
                    sum++;
                }
            }
        }
        return sum;
    }


    /**
     * 原始数组 ---> 稀疏数组
     * 1.遍历 原始的二维数组，得到有效数据的个数 sum
     * 2.根据sum将可以创建 稀疏数组 sparseArr int[sum+1][3]
     *   稀疏数组，行数固定为 sum + 1 行 (第一行存储原始数组的 行数，列数，和非特殊值的个数)
     *   稀疏数组，列数固定为 3列
     * 3.将二维数组的有效数据存入到 稀疏数组
     *
     * @param twoArr
     * @return
     */
    public static int[][] twoArrToSparseArr(int[][] twoArr) {
        int[][] sparseArr;
        if (null == twoArr || twoArr.length == 0) {
            return null;
        }

        int sum = getNonSpecialValueCount(twoArr, SPECIAL_VAL);
        System.out.println("sum = " + sum);
        //一维数组的长度为 sum + 1,列为固定的 3 列
        //第一行存储 二维数组原始的行和列数
        int oneArrLength = sum + 1;
        sparseArr = new int[oneArrLength][3];
        sparseArr[0][0] = twoArr.length;
        sparseArr[0][1] = twoArr[0].length;
        sparseArr[0][2] = sum;

        int k = 1;
        for (int i = 0; i < twoArr.length; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                if (!Objects.equals(SPECIAL_VAL, twoArr[i][j])) {
                    sparseArr[k][0] = i;
                    sparseArr[k][1] = j;
                    sparseArr[k][2] = twoArr[i][j];
                    k++;
                }
            }
        }
        return sparseArr;
    }


    /**
     * 稀疏数组 ---> 原始数组
     * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
     * 2.再读取稀疏数组后几行数据，并赋给 原始的二维数组 即可
     *
     * @param sparseArr
     * @return
     */
    public static int[][] sparseArrToTwoArr(int[][] sparseArr) {
        int rows = sparseArr[0][0];
        int columns = sparseArr[0][1];
        int sum = sparseArr[0][2];
        int k = 1;
        int twoArr[][] = new int[rows][columns];
        for (int i = 0; i < twoArr.length; i++) {
            for (int j = 0; j < twoArr[i].length; j++) {
                if (i == sparseArr[k][0] && j == sparseArr[k][1]) {
                    //在稀疏数组中，恢复为原值
                    twoArr[i][j] = sparseArr[k][2];

                    //只有匹配上 稀疏数组中的值之后，才让稀疏数组移到下一行
                    //稀疏数组的 最后一行，不能再加了
                    if (k < sum) {
                        k++;
                    }
                    //System.out.println("k = " + k);
                } else {
                    //其余值 恢复为特殊值
                    twoArr[i][j] = SPECIAL_VAL;
                }
            }
        }
        return twoArr;
    }
}

















