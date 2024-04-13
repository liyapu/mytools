package com.lyp.learn.recursion;

import java.util.ArrayList;

/**
 * @author liyapu
 * @date 2023-12-31 10:32
 * @description
 */
public class RepeatTargetNum {

    /**
     * 假设有四种面额的钱币，1 元、2 元、5 元和 10 元，而您一共给我 10 元，
     * 那您可以奖赏我 1 张 10 元，或者 10 张 1 元，或者 5 张 1 元外加 1 张 5 元等等。
     * 如果考虑每次奖赏的金额和先后顺序，那么最终一共有多少种不同的奖赏方式呢？
     */
    public static long[] rewards = {1, 2, 5, 10}; // 四种面额的纸币

    /**
     * @param totalReward-奖赏总金额，result-保存当前的解
     * @return void
     * @Description: 使用函数的递归（嵌套）调用，找出所有可能的奖赏组合
     */
    public static void get(long totalReward, ArrayList<Long> result) {
        if (totalReward == 0) {
            // 当totalReward = 0时，证明它是满足条件的解，结束嵌套调用，输出解
            System.out.println(result);
            return;
        } else if (totalReward < 0) {
            // 当totalReward < 0时，证明它不是满足条件的解，不输出
            return;
        } else {
            for (int i = 0; i < rewards.length; i++) {
                ArrayList<Long> newResult = (ArrayList<Long>) result.clone(); // 由于有4种情况，需要clone当前的解并传入被调用的函数
                newResult.add(rewards[i]); // 记录当前的选择，解决一点问题
                get(totalReward - rewards[i], newResult); // 剩下的问题，留给嵌套调用去解决
            }
        }
    }

     /*
        你应该能看到，虽然迭代法的思想是可行的，但是如果用循环来实现，恐怕要保存好多中间状态及其对应的变量。
        说到这里，你是不是很容易就想到计算编程常用的函数递归？

        在递归中，每次嵌套调用都会让函数体生成自己的局部变量，正好可以用来保存不同状态下的数值，
        为我们省去了大量中间变量的操作，极大地方便了设计和编程。
         */

    public static void main(String[] args) {
        long totalReward = 10;
        ArrayList<Long> result = new ArrayList<>();
        get(totalReward, result);
    }
}
