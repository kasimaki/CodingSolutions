package LeetCode;

public class LC0322 {
    public int coinChange(int[] coins, int amount) {
        if (coins == null) return -1;
        if (coins.length == 0 && amount > 0) return -1;
        if (amount == 0) return 0;

        //dp[i] = fewest number of coins to reach a target i
        //dp[i] = min(dp[i-coints[j]]+1)
        int[] dp = new int[amount+1];
        dp[0] = 0;

        int minForI;
        for (int i = 1; i <= amount; i++) {
            minForI = Integer.MAX_VALUE;
            for (int c : coins) {
                if (i - c < 0) continue;
                if (dp[i-c] < 0) continue;
                minForI = Math.min(minForI, dp[i - c] + 1);
            }
            dp[i] = minForI == Integer.MAX_VALUE ? -1 : minForI;

        }

        return dp[amount] == 0 ? -1 : dp[amount];
    }


}
