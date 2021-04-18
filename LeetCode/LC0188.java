// Best Time to Buy and Sell Stock IV 股票题根源，必回
//You are given an integer array prices where prices[i] is the price of a given stock on the ith day.
//
//Design an algorithm to find the maximum profit. You may complete at most k transactions.
//
//Notice that you may not engage in multiple transactions simultaneously (i.e., you must sell the stock
// before you buy again).
//

package LeetCode;

public class LC0188 {
    public int maxProfit(int k, int[] prices) {
        //cc
        if (prices == null) throw new IllegalArgumentException();
        if (k <= 0 || prices.length == 0) return 0;

        int len = prices.length;

        // dp matrix
        // dp[k][i]: highest profit with most k transactions from day 0 to day i
        int[][] buy = new int[k+1][len];
        int[][] sell = new int[k+1][len];

        // base case: at the first day, can buy prices[0], no matter k; 0 transaction will lead to 0 balance
        for (int idxK = 0; idxK <= k; idxK++) buy[idxK][0] = -prices[0];
        for (int i = 0; i < len; i++) {
            buy[0][i] = 0;
            sell[0][i] = 0;
        }

        // fill dp
        for (int idxK = 1; idxK <= k; idxK++) { // idxK starts from 1, after initialization
            for (int i = 1; i < len; i++) { // day 0 has only two set base cases: sell[0][0] and buy[1][0]
                buy[idxK][i] = Math.max(buy[idxK][i-1], sell[idxK-1][i-1] - prices[i]);
                sell[idxK][i] = Math.max(sell[idxK][i-1], buy[idxK][i-1] + prices[i]);
            }
        }
        return sell[k][len-1];
    }

    public static void main(String[] args) {
        int k = 2;
        int[] prices = new int[] {2, 4, 1};
        LC0188 sol = new LC0188();
        int res = sol.maxProfit(k, prices);
        System.out.println(res);
    }
}
