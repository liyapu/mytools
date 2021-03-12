package com.lyp.likou.dynamic;

/**
 *@author: liyapu
 *@description:
 *@date 2021-03-12 16:44
 *
 * 121. 买卖股票的最佳时机
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 *
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 *
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 *
 *
 * 示例 1：
 *
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 */
public class D_121 {

    /**
     * 我们需要找出给定数组中两个数字之间的最大差值（即，最大利润）。此外，第二个数字（卖出价格）必须大于第一个数字（买入价格）。
     *
     * 形式上，对于每组 i 和 j（其中 j>i）我们需要找出 max(prices[j]−prices[i])
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int maxProfit = 0;
        int len = prices.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                int temp = prices[j] - prices[i];
                maxProfit = Math.max(temp, maxProfit);
            }
        }
        return maxProfit;
    }

    /**
     *  我们只需要遍历价格数组一遍，记录历史最低点，然后在每一天考虑这么一个问题：
     *     如果我是在历史最低点买进的，那么我今天卖出能赚多少钱？
     *     当考虑完所有天数之时，我们就得到了最好的答案。
     *
     * 作者：LeetCode-Solution
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock/solution/121-mai-mai-gu-piao-de-zui-jia-shi-ji-by-leetcode-/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 第二种解法的解读应该是，
     *    先选一个最低点i，向后遍历的时候看它的最高点是什么；
     *    如果发现更低的最低点j，那么就以新的j替换i，因为此后出现的最高点k，k-j > k-i；
     *    而在j之前出现的高点，都和j没有关系。
     * @param prices
     * @return
     */
    public static int maxProfit_A(int[] prices) {
        int len = prices.length;
        if(len == 0){
            return 0;
        }

        int maxProfit = 0;
        int base = prices[0];

        for (int i = 1; i < len; i++) {
           if(prices[i] > base){
               maxProfit = Math.max(maxProfit,prices[i]-base);
           }else{
               base = prices[i];
           }

        }
        return maxProfit;
    }

    public static int maxProfit_B(int[] prices) {
        int len = prices.length;
        if(len == 0){
            return 0;
        }

        int maxProfit = 0;
        int minPrice = prices[0];

        for (int i = 1; i < len; i++) {
            minPrice = Math.min(minPrice,prices[i]);
            maxProfit = Math.max(maxProfit,prices[i]-minPrice);
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] profitArr1 = {7, 1, 5, 3, 6, 4};
        int[] profitArr2 = {7, 6, 4, 3, 1};

        System.out.println(maxProfit(profitArr1));
        System.out.println(maxProfit(profitArr2));
        System.out.println();

        System.out.println(maxProfit_A(profitArr1));
        System.out.println(maxProfit_A(profitArr2));
        System.out.println();

        System.out.println(maxProfit_B(profitArr1));
        System.out.println(maxProfit_B(profitArr2));
        System.out.println();
    }
}
