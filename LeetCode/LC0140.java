// Word Break II
//Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, add spaces in
// s to construct a sentence where each word is a valid dictionary word.Return all such possible sentences.

package LeetCode;

import java.util.*;

public class LC0140 {

    public List<String> wordBreakNew(String s, List<String> wordDict) {
        //cc
        if(s == null || s.length() == 0 || wordDict == null || wordDict.size() == 0)
            return new ArrayList<>();

        Set<String> set = new HashSet<>(wordDict);

        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        dfsNew(res, path, s, 0, set);
        return res;
    }

    private void dfsNew(List<String> res, StringBuilder path, String s, int idx, Set<String> set) {
        //bc
        if (idx == s.length()) {
            res.add(path.toString());
            return;
        }

        int len = path.length();
        for (int i = idx+1; i <= s.length(); i++ ) {
            String sub = s.substring(idx, i);
            if (!set.contains(sub)) continue;

            if (len == 0) {
                path.append(sub);
            } else {
                path.append(" " + sub);
            }

            dfsNew(res, path, s, i, set);

            path.setLength(len);
        }

    }

    public List<String> wordBreak(String s, List<String> wordDict) {
        //cc
        if (s == null || s.length() == 0) return new ArrayList<>();

        Set<String> set = new HashSet<>(wordDict);
        int maxLen = Integer.MIN_VALUE;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        Boolean[] mem = new Boolean[s.length()];
        dfs(res, path, set, s, 0, maxLen, mem);
        return res;
    }

    private void dfs(List<String> res, StringBuilder path, Set<String> set, String s, int idx, int max, Boolean[] mem) {
        // bc success
        if (idx == s.length()) {
            String newRes = path.toString();
            res.add(path.substring(0, newRes.length() - 1));
            return;
        }

        if (mem[idx] != null) return; //此路不通 ！！！最关键，不关心通的情况，通的还是要继续走， true不会被赋值，所以作用和null一样
        int curSize = res.size(); //比较res中有没有新增结果来看是否能走通


        int len = path.length();
        int maxRange = Math.min(max, s.length() - idx);
        for (int i = 1; i <= maxRange; i++) { //TODO 等于刚好越界
            String newWord = s.substring(idx, idx + i);
            if (set.contains(newWord)) {
                path.append(newWord + " ");
                dfs(res, path, set, s, idx + i, max, mem);
                path.setLength(len);
            }
        }
        if (res.size() == curSize) mem[idx] = false; //走不通，就false
    }

    public static void main(String[] args) {
        List<String> wordList = Arrays.asList("cat", "cats", "and", "sand", "dog");
        String s = new String("catsanddog");
        LC0140 sol = new LC0140();
        List<String> res = sol.wordBreak(s, wordList);
        System.out.print(res);
    }
}
