// Word Break
//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
// determine if s can be segmented into a space-separated sequence of one or more dictionary words.
//

//DP, initial status dp[0] = true;

package LeetCode;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC0139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        // cc

        Set<String> set = new HashSet<>(wordDict);
        int len = s.length();
        boolean[] mem = new boolean[len + 1]; // [j,i),  [0,0) represent empty string using .substring
        mem[0]  = true; //initial status

        for (int i = 0; i < len+1; i++) { // i = right boundary exclu
            for (int j = 0; j <= i; j++) { // j = left boundary inclu
                if (!mem[j]) continue;
                String rightWord = s.substring(j, i);
                if (set.contains(rightWord)) {
                    mem[i] = true;
                }
            }
        }

        return mem[len];
    }
    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("cat", "cats", "and", "sand", "dog");
        String s = new String("catsanddog");
        LC0139 sol = new LC0139();
        boolean res = sol.wordBreak(s, wordList);
        System.out.print(res);
    }
}
