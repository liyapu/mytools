package com.lyp.learn;

import java.util.Arrays;
import java.util.Objects;

/**
 * 给定长度为n的数组，每个元素代表一个木头的长度，木头可以任意截断，从这堆木头中截出至少k个相同长度为m的木块。已知k，求max(m)。
 * 输入两行，第一行n,k，第二行为数组序列。输出最大值。
 *
 * 输入:
 * n=5 k=5
 * arr=[4,7,2,10,5]
 * 输出:4
 * 解释：最多可以把它分成5段长度为4的木头
 */
public class Demo {
    /*
    [4,7,2,10,5]
    [2,4,5,7,10]
   */
    public static void main(String[] args) {

    }

    public static int findMaxLen(Integer len,Integer k,Integer[] arrs){
        if(Objects.isNull(arrs)){
            return -1;
        }
        Arrays.sort(arrs);
        int midNum = 0;
        int middleIndex = len/2;
        if(len%2 == 0){
            midNum = (arrs[middleIndex] + arrs[middleIndex+1])/2;
        }else{
            midNum = arrs[middleIndex];
        }
        int total = 0;
        while(total != k){

        }



    }
}
