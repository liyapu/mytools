package com.lyp.learn.pk7;

/**
 *  测试解释器模式和JIT编译模式
 *   -Xint   解释器模式         花费时间为 : 52299 ms
 *   -Xcomp  即时编译器jit模式    花费时间为 : 6828 ms
 *   -Xmixed 混合模式            花费时间为 : 6857 ms
 *
 *  VM options 配置上面的参数
 *
 *  宋红康  shkstart@126.com
 */
public class IntCompTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        testPrimeNumber(10000000);
        long end = System.currentTimeMillis();
        System.out.println("花费时间为 : " + (end - start) + " ms");
    }

    private static void testPrimeNumber(int count) {
        for (int i = 0; i < count; i++) {
            //计算100以内的质数
            label:for(int j = 2 ; j <= 100; j++){
                for(int k = 2; k <= Math.sqrt(j); k++){
                    if(j % k == 0){
                        continue label;
                    }
                }
            }
        }
    }
}




















