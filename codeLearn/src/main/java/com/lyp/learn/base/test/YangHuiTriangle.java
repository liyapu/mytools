package com.lyp.learn.base.test;


/**
 *打印杨辉三角
 * @author zjluoc
 *
 */
public class YangHuiTriangle {
    private static String singleSpaceString = "*";// 用于补齐数据空格的单个字符串

    public static void main(String args[]) {
        printYangHuiTriangle(6);
    }

    /**
     * 打印对应层数lines的杨辉三角形
     *
     * @param lines
     *            层数
     */
    public static void printYangHuiTriangle(int lines) {
        // 首先定义一个二维数组用于存储每一层对应行列的数值
        long yh[][] = new long[lines][lines];
        // 接着定义每一层首位和末尾均为1，此处for循环给边界赋值“1”。
        for (int i = 0; i < lines; i++) {
            yh[i][0] = yh[i][i] = 1;
        }
        // 接着计算从第三层开始那些由肩部两端相加的和
        for (int i = 2; i < lines; i++) {// 控制层从第三层到最后一层，2~lines-1
            for (int j = 1; j < i; j++) {// 每一层除去首尾2个数，只有（i+1）-2个数需要计算，说明j最大值为i-1
                // 计算肩部数字之和
                yh[i][j] = yh[i - 1][j - 1] + yh[i - 1][j];
            }
        }

        /*
         * 进行到这里已经计算好了每层的数字，现在下面开始对其进行塑造三角形形状
         */

        // 获得调整后的相同数据长度的二维字符数据数组
        String[][] adjustedNumberStringArray = adjustDigitalLength(yh,
                singleSpaceString);
        // 获取单个数据字符串长度作为单个需要打印补齐的空格长度
        int time = adjustedNumberStringArray[0][0].length()
                - singleSpaceString.length();
        if (time > 0) {
            StringBuilder sb = new StringBuilder(singleSpaceString);
            for (int i = 0; i < time; i++) {
                sb.append(singleSpaceString);
            }
            singleSpaceString = sb.toString();
        }
        for (int i = 0; i < adjustedNumberStringArray.length; i++) {
            // 打印每行数据首个数据补齐字符singleSpaceString
            for (int k = 0; k < adjustedNumberStringArray.length - i - 1; k++) {
                System.out.print(singleSpaceString);
            }
            for (int j = 0; j <= i; j++) {
                // 打印每个字符数据+singleSpaceString,最后一个数据不加
                if (j != i) {
                    System.out.print(adjustedNumberStringArray[i][j]
                            + singleSpaceString);
                } else {
                    // 每层结束后换行
                    System.out.println(adjustedNumberStringArray[i][j]);
                }
            }
        }

    }

    /**
     * 找出原始数据中最大字节长度，再对原来每个数据动态调整数据字节长度使其保持一致
     *
     * @param yh
     *            杨辉三角原始数据二维数组
     * @return String[][] 调整长度后的数据字符串二维数组
     */
    private static String[][] adjustDigitalLength(long[][] yh,
                                                  String singleSpaceString) {
        // 找出最长的数据字符串长度
        int maxNumberStringLength = 0;
        // 存储长度调整后的数据字符串二维数组
        String[][] numberStringArray = new String[yh.length][yh.length];
        for (int i = 0; i < yh.length; i++) {
            for (int j = 0; j <= i; j++) {
                // 每层数字转换为字符串后计算长度,取得最大的数据字符串长度
                numberStringArray[i][j] = String.valueOf(yh[i][j]);
                if (String.valueOf(yh[i][j]).length() > maxNumberStringLength) {
                    maxNumberStringLength = String.valueOf(yh[i][j]).length();
                }
            }
        }
        // 用空格补齐数据字符长度,让每一个数据字符串长度都达到最大数据字符串长度
        for (int i = 0; i < numberStringArray.length; i++) {
            for (int j = 0; j <= i; j++) {
                // 计算每个数据字符串相差几个空格
                int spaceLength = maxNumberStringLength
                        - numberStringArray[i][j].length();
                // 用空格补齐至最大长度
                if (spaceLength > 0) {
                    StringBuilder sb = new StringBuilder(
                            numberStringArray[i][j]);
                    for (int k = 0; k < spaceLength; k++) {
                        sb.append(singleSpaceString);
                    }
                    numberStringArray[i][j] = sb.toString();
                }
            }
        }
        return numberStringArray;
    }

}
