package LeetCode;

import java.awt.*;

public class LC0095Count {
    public int generateTreesCount(int n) {
        // cc
        if (n < 0) throw new IllegalArgumentException();
        if (n <= 1) return 1;

        // dp[i] : with i nodes, how may types of tree shapes can be formed
        // do[i] = sum(dp[p-1] * dp[n-p]) 1 <= p <= n

        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            int count = 0;
            for (int p = 1; p <= i; p++) {
                count += dp[p-1] * dp[i-p];
            }
            dp[i] = count;
        }

        return dp[n];
    }

    public static void main(String[] args) {
        int n = 3;
        LC0095Count sol = new LC0095Count();
        int res = sol.generateTreesCount(n);
        System.out.println(res);
    }
}
