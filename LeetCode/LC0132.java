// Palindrome Partitioning II → Word Break II
//
//Given a string s, partition s such that every substring of the partition is a palindrome.
//
//Return the minimum cuts needed for a palindrome partitioning of s.
package LeetCode;

public class LC0132 {
    public int minCut(String s) {
        if (s == null) throw new IllegalArgumentException();
        if (s.length() < 2) return 0;

        int len = s.length();

        // dp for checking if palindrome
        boolean[][] dpPal = new boolean[len+1][len+1];
        for (int i = 0; i < len+1; i++) {
            dpPal[i][i] = true;
            if (i < len) dpPal[i][i+1] = true;
        }

        for (int i = 1; i < len+1; i++) { //[i,j)
            int ii = i;
            int j = i;
            while (ii-1 >= 0 && j+1 < len+1 && s.charAt(ii-1) == s.charAt(j)) {
                dpPal[ii-1][j+1] = true;
                ii--;
                j++;
            }
            ii = i;
            j = i+1;
            while ( ii-1 >= 0 && j+1 < len+1 && s.charAt(ii-1) == s.charAt(j)) {
                dpPal[ii-1][j+1] = true;
                ii--;
                j++;
            }
        }

        int[] dp = new int[len+1];
        dp[0] = -1; //TODO
        dp[1] = 0;
        for (int i = 2; i < len+1; i++) {
            int min = len;
            for (int j = 0; j < i; j++) {
                if (dpPal[j][i]) {
                    min = Math.min(min, dp[j]);
                }
            }
            dp[i] = min+1;
        }

        return dp[len];

    }

    public static void main(String[] args) {
        String s = new String("aab");
        LC0132 sol = new LC0132();
        int res = sol.minCut(s);
        System.out.println(res);
    }
}
