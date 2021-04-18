// Best Time to Buy and Sell Stock with Transaction Fee
// Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i; and a non-negative integer fee representing a transaction fee.
//
//You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction. You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
//
//Return the maximum profit you can make.
//

// based on LC122
package LeetCode;

public class LC0714 {
    public int maxProfit(int[] prices, int fee) {
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
            buy[i] = Math.max(buy[i-1], sell[i-1] - prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i] - fee); // 加个fee
        }
        return sell[len-1];
    }

    public static void main(String[] args) {
        int[] prices = new int[] {1, 3, 2, 8, 4, 9};
        int fee = 2;
        LC0714 sol = new LC0714();
        int res = sol.maxProfit(prices, fee);
        System.out.println(res);
    }
}
