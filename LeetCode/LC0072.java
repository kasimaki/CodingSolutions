//Edit Distance
//Given two strings word1 and word2, return the minimum number of operations required to convert word1 to word2.
//
//You have the following three operations permitted on a word:
//
//Insert a character
//Delete a character
//Replace a character

// DP

package LeetCode;

public class LC0072 {
    public int minDistance(String word1, String word2) {
        // cc
        if (word1 == null || word2 == null) throw new IllegalArgumentException();
        if (word1.length() < word2.length()) return minDistance(word2, word1);
        if (word2.length() == 0) return word1.length();

        int len1 = word1.length();
        int len2 = word2.length();

        int[][] dp = new int[len1+1][len2+1];
        for (int i = 0; i < len1+1; i++) dp[i][0] = i;
        for (int j = 0; j < len2+1; j++) dp[0][j] = j;

        // result is located at dp[len1+1][len2+1];

        for (int i = 1; i < len1+1; i++) {
            for (int j = 1; j < len2+1; j++) {
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = dp[i-1][j-1];
                } else {
                    int ret1 = dp[i-1][j];
                    int ret2 = dp[i][j-1];
                    int ret3 = dp[i-1][j-1];
                    dp[i][j] = 1 + Math.min(ret1, Math.min(ret2, ret3));
                }
            }
        }

        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = new String("horse");
        String s2 = new String("ros");
        LC0072 sol = new LC0072();
        int res = sol.minDistance(s1, s2);
        System.out.println(res);
    }
}
