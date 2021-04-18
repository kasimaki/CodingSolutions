// Interleaving String
//Given strings s1, s2, and s3, find whether s3 is formed by an interleaving of s1 and s2.
//
//An interleaving of two strings s and t is a configuration where they are divided into
// non-empty substrings such that:
//s = s1 + s2 + ... + sn
//t = t1 + t2 + ... + tm
//|n - m| <= 1
//The interleaving is s1 + t1 + s2 + t2 + s3 + t3 + ... or t1 + s1 + t2 + s2 + t3 + s3 + ...
//Note: a + b is the concatenation of strings a and b.


package LeetCode;

public class LC0097 {
    public boolean isInterleave(String s1, String s2, String s3) {
        //cc
        if (s1 == null || s2 == null || s3 == null) throw new IllegalArgumentException();
        if (s3.length() == 0) return true;// 注意：空string也可以成为空String的interleaving
        if (s1.length() + s2.length() != s3.length()) return false;
        if (s1.length() == 0) return s2.equals(s3);
        if (s2.length() == 0) return s1.equals(s3);

        int len1 = s1.length();
        int len2 = s2.length();

        // dp[i][j]: if s1 [0, i) s2[0, j) can be interleaving of s3 [0, i+j-1)
        //              s1.charAt(i)  s2.charAt(j)          s3.charAt(i+j)
        // dp[i][j] = dp[i-1][j] || dp[i][j-1]  if si(i-1) == s2(j-1) == s3(k-1)
        //              false   if no match
        //              dp[i-1][j]   if only s1 match
        //              dp[i][j-1]   if only s2 match

        boolean[][] dp = new boolean[len1 + 1][len2 + 1];

        // bc
        dp[0][0] = true;

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                int k = i + j - 1;
                if (i == 0 && j == 0) continue; // base case
                if (i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(k);
                } else if (j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(k);
                } else {
                    dp[i][j] = false; // no match
                    if (s1.charAt(i - 1) == s3.charAt(k)) {
                        dp[i][j] = dp[i - 1][j];
                    }
                    if (s2.charAt(j - 1) == s3.charAt(k)) {
                        dp[i][j] = dp[i][j] || dp[i][j - 1];
                    }
                }
            }
        }
        return dp[len1][len2];
    }

    public static void main(String[] args) {
        String s1 = new String("aabcc");
        String s2 = new String("dbbca");
        String s3 = new String("aadbbcbcac");
        LC0097 sol = new LC0097();
        boolean res = sol.isInterleave(s1, s2, s3);
        System.out.println(res);

    }
}
