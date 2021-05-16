package com.lyp.likou.dynamic;

/**
 *@author: liyapu
 *@description:
 *@date 2021-05-12 09:15
 *
 * 122. 买卖股票的最佳时机 II
 * 给定一个数组 prices ，其中 prices[i] 是一支给定股票第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你可以尽可能地完成更多的交易（多次买卖一支股票）。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *
 * 示例 1:
 * 输入: prices = [7,1,5,3,6,4]
 * 输出: 7
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6-3 = 3 。
 *
 * 示例 2:
 * 输入: prices = [1,2,3,4,5]
 * 输出: 4
 * 解释: 在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5-1 = 4 。
 *      注意你不能在第 1 天和第 2 天接连购买股票，之后再将它们卖出。因为这样属于同时参与了多笔交易，你必须在再次购买前出售掉之前的股票。
 *
 *
 * 示例 3:
 * 输入: prices = [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 *
 *
 * 提示：
 *
 * 1 <= prices.length <= 3 * 104
 * 0 <= prices[i] <= 104
 */
public class D_122 {

    /**
     * 第一维 i 表示下标为 i 的那一天（ 具有前缀性质，即考虑了之前天数的交易 ）；
     * 第二维 j 表示下标为 i 的那一天是持有股票，还是持有现金。这里 0 表示持有现金（cash），1 表示持有股票（stock）。
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param prices
     * @return
     */
    public static int maxProfit(int[] prices) {
        int len;
        if(prices == null || (len = prices.length) <= 1 ){
            return 0;
        }

        //第一维表示 第几天
        int[][] dp = new int[len][len];
        //第二维 0 表示持有现金
        //第二维 1 表示持有股票
        dp[0][0] = 0;
        dp[0][1] = -prices[0];

        for (int i = 1; i < len; i++) {
            // 下面这两行调换顺序也是可以的

            //持有现金  = Max(前一天持有现金，前一天持有股票 + 今天股价)
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            //持有股票 = Max(前一天持有股票，前一天持有现金 - 今天股价)
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        return dp[len - 1][0];
    }

    /**
     * 由于当前行只参考上一行，每一行就 2 个值，因此可以考虑使用「滚动变量」（「滚动数组」技巧）。
     */
    public static int maxProfit2(int[] prices) {
        int len;
        if(prices == null || (len = prices.length) <= 1 ){
            return 0;
        }

        //表示持有现金
        int preCash = 0 ;
        //表示持有股票
        int preStock = -prices[0];

        for (int i = 1; i < len; i++) {
            // 下面这两行调换顺序也是可以的

            //持有现金  = Max(前一天持有现金，前一天持有股票 + 今天股价)
            preCash = Math.max(preCash, preStock + prices[i]);
            //持有股票 = Max(前一天持有股票，前一天持有现金 - 今天股价)
            preStock = Math.max(preStock, preCash - prices[i]);
        }
        return preCash;
    }

    public static void main(String[] args) {
        System.out.println(maxProfit(new int[]{7, 1, 5, 3, 6, 4}));
        System.out.println(maxProfit2(new int[]{7, 1, 5, 3, 6, 4}));
    }
}
