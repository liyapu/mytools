package com.lyp.likou.interview;

import java.util.HashMap;
import java.util.Map;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-15 11:03
 *
 * 面试题 08.01. 三步问题
 * 三步问题。有个小孩正在上楼梯，楼梯有n阶台阶，小孩一次可以上1阶、2阶或3阶。实现一种方法，计算小孩有多少种上楼梯的方式。结果可能很大，你需要对结果模1000000007。
 *
 * 示例1:
 *
 *  输入：n = 3
 *  输出：4
 *  说明: 有四种走法
 * 示例2:
 *
 *  输入：n = 5
 *  输出：13
 * 提示:
 *
 * n范围在[1, 1000000]之间
 *
 */
public class I_08_01 {

    public static int waysToStep(int n) {
        if(n == 1){
            return 1;
        }
        if(n == 2){
            return 2;
        }
        if(n == 3){
            return 4;
        }
        return waysToStep(n-1) + waysToStep(n-2) + waysToStep(n-3);
    }

    public static int waysToStep_A(int n) {
        Map<Integer,Integer> cache = new HashMap<>();
        cache.put(1,1);
        cache.put(2,2);
        cache.put(3,4);
        return waysToStepCache(n,cache);
    }

    private static int waysToStepCache(int n, Map<Integer, Integer> cache) {
        if(cache.containsKey(n)){
            return cache.get(n);
        }
        int temp = waysToStep(n-1) + waysToStep(n-2) + waysToStep(n-3);
        cache.put(n,temp);
        return temp;
    }

    public static void main(String[] args) {
        int n = 4;
        int n1 = 5;
        System.out.println(waysToStep(n));
        System.out.println(waysToStep(n1));
        System.out.println();

        System.out.println(waysToStep_A(n));
        System.out.println(waysToStep_A(n1));
    }
}
