//Distinct Subsequences
//Given two strings s and t, return the number of distinct subsequences of s which equals t.
//
//A string's subsequence is a new string formed from the original string by deleting some (can be none)
// of the characters without disturbing the relative positions of the remaining characters.
// (i.e., "ACE" is a subsequence of "ABCDE" while "AEC" is not).
//
//It's guaranteed the answer fits on a 32-bit signed integer.
package LeetCode;

public class LC0115 {
    public int numDistinct(String s, String t) {
        //cc
        if (s == null || t == null) throw new IllegalArgumentException();
        if (t.length() == 0) return 1;
        if (s.length() < t.length()) return 0;

        int lenS = s.length();
        int lenT = t.length();

        // dp[i][j]: s [0, i) matches t [0, j)
        // dp[i][j] = dp[i-1][j]   if s[i] != t[j]
        //            dp[i-1][j-1] + dp[i-1][j] if s[i] == t[j]
        //              选当前i，则j被使用    不选当前i来匹配，则j是跟i之前匹配，2种分叉，结果相加

        int[][] dp = new int[lenS+1][lenT+1];

        // bc
        //dp[0][0] = 1;
        for (int i = 0; i <= lenS; i++) dp[i][0] = 1;
        for (int j = 1; j <= lenT; j++) dp[0][j] = 0;

        for (int i = 1; i <= lenS; i++) {
            for (int j = 1; j <= lenT; j++) {
                if (s.charAt(i-1) != t.charAt(j-1)) { //不匹配，则必定只依赖于i-1时候的结果
                    dp[i][j] = dp[i-1][j];
                } else { //匹配，则当前状态，可能取，也可不取i，取i的话，结果依赖于i-1 j-1，不取i的话，结果就是i-1 j，两个相加就是所有可能
                    dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
                }
            }
        }
        return dp[lenS][lenT];
    }

    public static void main(String[] args) {
        String s = new String("rabbbit");
        String t = new String("rabbit");
        LC0115 sol = new LC0115();
        int res = sol.numDistinct(s, t);
        System.out.println(res);
    }
}
