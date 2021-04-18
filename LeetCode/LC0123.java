// Best Time to Buy and Sell Stock III 股票题
//Say you have an array for which the ith element is the price of a given stock on day i.
//
//Design an algorithm to find the maximum profit. You may complete at most two transactions.
//
//Note:You may not engage in multiple transactions at the same time
// (i.e., you must sell the stock before you buy again).
//
//

package LeetCode;

public class LC0123 {
    public int maxProfit(int[] prices) {
        //cc
        if (prices == null) throw new IllegalArgumentException();
        if (prices.length < 2) return 0;
        int len = prices.length;

        // dp matrix
        // dp[k][i]: highest profit with most k transactions from day 0 to day i
        int[] buy1 = new int[len];
        int[] buy2 = new int[len];
        int[] sell1 = new int[len];
        int[] sell2 = new int[len];

        // base case: buy1 will keep the max in the history
        // buy1 and buy1 on 0 day will always -price[0]
        // sell on 0 day will always 0
        buy1[0] = -prices[0];
        buy2[0] = -prices[0];//第0天买几次，都是-prices[0]

        // fill dp
        for (int i = 1; i < len; i++) {
            buy1[i] = Math.max(buy1[i-1], -prices[i]);
            buy2[i] = Math.max(buy2[i-1], sell1[i-1] - prices[i]);
            sell1[i] = Math.max(sell1[i-1], buy1[i-1] + prices[i]);
            sell2[i] = Math.max(sell2[i-1], buy2[i-1] + prices[i]);
        }
        return sell2[len-1];
    }

    public static void main(String[] args) {
        int[] prices = new int[] {2, 4, 1};
        LC0123 sol = new LC0123();
        int res = sol.maxProfit(prices);
        System.out.println(res);
    }
}

