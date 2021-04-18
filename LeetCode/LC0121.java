// Best Time to Buy and Sell Stock II 股票题
//You are given an array prices where prices[i] is the price of a given stock on the ith day.
//
//You want to maximize your profit by choosing a single day to buy one stock and choosing a different day
// in the future to sell that stock.
//
//Return the maximum profit you can achieve from this transaction. If you cannot achieve any profit, return 0.


package LeetCode;

public class LC0121 {
    public int maxProfit(int[] prices) {
        //cc
        if (prices == null) throw new IllegalArgumentException();
        if (prices.length < 2) return 0;
        int len = prices.length;

        // dp matrix
        // dp[i]: highest profit from day 0 to day i
        int[] buy = new int[len];
        int[] sell = new int[len];

        // base case: when i == 0, if buy, should give -price[0]
        // if sell, should be 0
        buy[0] = -prices[0];

        // fill dp
        for (int i = 1; i < len; i++) { //买只能买一次，不需要跟历史sell结果比较，所以计算历史最低买入/最大的负值
            buy[i] = Math.max(buy[i-1], -prices[i]);
            sell[i] = Math.max(sell[i-1], buy[i-1] + prices[i]); //买入只需要比较找历史上最低的买入和该天的差值
        }
        return sell[len-1];
    }

    public static void main(String[] args) {
        int[] prices = new int[] {7,1,5,3,6,4};
        LC0121 sol = new LC0121();
        int res = sol.maxProfit(prices);
        System.out.println(res);
    }
}

