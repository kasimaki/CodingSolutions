package LeetCode;

import java.lang.reflect.Array;
import java.util.Arrays;

public class LC0072DFS {
    public int minDistance(String word1, String word2) {
        //cc
        if (word1 == null || word2 == null) throw new IllegalArgumentException();
        if (word1.length() < word2.length()) return minDistance(word2, word1); //base case
        if (word2.length() == 0) return word1.length();
        int[][] mem = new int[word1.length()+1][word2.length()+1];//[0,len) --> total is len+1
        for (int i = 0; i < word1.length()+1; i++) {
            for (int j = 0; j < word2.length()+1; j++){
                mem[i][j] = -1;
            }
        }
        return dfs(word1, word1.length(), word2, word2.length(), mem);
    }

    private int dfs(String s1, int idx1, String s2, int idx2, int[][] mem) {
        if (mem[idx1][idx2] > 0 ) return mem[idx1][idx2];

        // bc touch the bottom [0,idx) no fail case
        if (idx1 == 0) return idx2; //TODO 2维初始需要设置两条边
        if (idx2 == 0) return idx1;

        //visited? One direction, impossible to be cyclic, no need
        int ret1 = dfs(s1, idx1-1, s2, idx2-1, mem);
        int ret2 = dfs(s1, idx1, s2, idx2-1, mem);
        int ret3 = dfs(s1, idx1-1, s2, idx2, mem);
        if (s1.charAt(idx1-1) == s2.charAt(idx2-1)) { //开区间，idx-1才是String对应的位置
            mem[idx1][idx2] = ret1;
            return ret1;
        } else {
            int cost = 1 + Math.min(ret1, Math.min(ret2, ret3)); //+1不能忘
            mem[idx1][idx2] = cost;
            return cost;
        }
    }
}
