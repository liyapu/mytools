package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-20 08:55
 *
 * 69. x 的平方根
 * 实现 int sqrt(int x) 函数。
 *
 * 计算并返回 x 的平方根，其中 x 是非负整数。
 *
 * 由于返回类型是整数，结果只保留整数的部分，小数部分将被舍去。
 *
 * 示例 1:
 * 输入: 4
 * 输出: 2
 *
 * 示例 2:
 * 输入: 8
 * 输出: 2
 * 说明: 8 的平方根是 2.82842...,
 *      由于返回类型是整数，小数部分将被舍去。
 */
public class L_69 {

    public static int mySqrt(int x) {
        if (x == 0) return 0;

        int last = 1;
        // 这里 i 需要使用 long型，否则 int型 溢出
        for (long i = 1; i < x; i++) {
            if (i * i <= x) {
                last = (int) i;
            } else {
                break;
            }
        }
        return last;
    }

    /**
     * 使用  二分查找
     * @param x
     * @return
     */
    public static int mySqrt2(int x) {
        // 特殊值处理
        if (x == 0 || x == 1) {
            return x;
        }

        int low = 1;
        int high = x;
        int mid;
        int ans = 1;
        while (low <= high) {
            mid = low + (high - low) / 2;
            //这里要 强制转换为 long 否则有溢出
//            if ((long) mid * mid <= x) {
            // 或者使用 除法
            if (mid <= x / mid) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        System.out.println(mySqrt(1));
        System.out.println(mySqrt(4));
        System.out.println(mySqrt(8));
        System.out.println(mySqrt(2147395600));

        System.out.println("----------");
        System.out.println(mySqrt2(1));
        System.out.println(mySqrt2(4));
        System.out.println(mySqrt2(8));
        System.out.println(mySqrt2(2147395600));
    }
}
