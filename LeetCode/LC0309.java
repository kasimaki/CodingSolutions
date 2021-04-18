// Best Time to Buy and Sell Stock with Cooldown
// Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete as many transactions as you like
// (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
//
//You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
//After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)
//

// based on LC122
package LeetCode;

public class LC0309 {
    public int maxProfit(int[] prices) {
        //cc
        if (prices == null) throw new IllegalArgumentException();
        if (prices.length < 2) return 0;
        int len = prices.length;

        // dp matrix
        // dp[k][i]: highest profit with most k transactions from day 0 to day i
        int[] buy = new int[len];
        int[] sell = new int[len];

        // base case: when i == 0, if buy, should give -price[0]
        // if sell, should be 0
        buy[0] = -prices[0];

        // fill dp
        for (int i = 1; i < len; i++) { //先买，再卖，循环更新
            if (i == 1) buy[i] = Math.max(buy[i-1], -prices[i]);  //i==1时，取股价最低的时候买入
            else buy[i] = Math.max(buy[i-1], sell[i-2] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]);
        }
        return sell[len-1];
    }

    public static void main(String[] args) {
        int[] prices = new int[] {1, 2, 4};
        LC0309 sol = new LC0309();
        int res = sol.maxProfit(prices);
        System.out.println(res);
    }
}
