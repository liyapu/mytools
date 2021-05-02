package com.lyp.likou.other;

/**
 *@author: liyapu
 *@description:
 *@date 2021-04-27 10:21
 *
 * 7. 整数反转
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 *
 * 如果反转后整数超过 32 位的有符号整数的范围 [−231,  231 − 1] ，就返回 0。
 *
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *
 *
 * 提示：
 *
 * -231 <= x <= 231 - 1
 */
public class L_7 {

    /**
     * https://leetcode-cn.com/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int result = 0;
        int last = 0;
        int temp = 0;
        //循环的判断条件应该是while(x!=0)，无论正数还是负数，按照上面不断的/10这样的操作，最后都会变成0，
        // 所以判断终止条件就是!=0
        while (x != 0) {
            //模除10,可以得到最后一位上的数字
            temp = x % 10;
            //记录上一次的结果
            last = result;
            //让 result 乘以 10，然后再加 temp,表示 temp 拼接到了最后边
            result = result * 10 + temp;
            if (last != result / 10) {
                //result 缩小10倍，并且移除了temp
                //若是和最近的 last 不相等，说明 溢出了，直接返回

                //很简洁！不过我觉得判断整数溢出还能有另外一张方法：
                // result每次更新后除10，然后跟上一次的result比较一下，如果不相等，就是溢出
                return 0;
            }
            //除以 10，继续
            x /= 10;
        }
        return result;
    }

    public static void main(String[] args) {
//        System.out.println(reverse(12345));
        System.out.println(reverse(-12345));
    }
}
