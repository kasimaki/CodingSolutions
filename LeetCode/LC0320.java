//Generalized Abbreviation
//A word's generalized abbreviation can be constructed by taking any number of non-overlapping
// substrings and replacing them with their respective lengths. For example, "abcde" can be
// abbreviated into "a3e" ("bcd" turned into "3"), "1bcd1" ("a" and "e" both turned into "1"),
// and "23" ("ab" turned into "2" and "cde" turned into "3").
//
//Given a string word, return a list of all the possible generalized abbreviations of word.
// Return the answer in any order.


//TC = O(2^n)

package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC0320 {
    public List<String> generateAbbreviations(String word) {
        // cc
        if (word == null || word.length() == 0) return new ArrayList<String>();

        //
        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();
        dfs(res, path, word, 0, 0);

        return res;
    }

    private void dfs(List<String> res, StringBuilder path, String s, int idx, int count) {
        int len = path.length();
        // bc
        if (idx == s.length()) {
            if (count > 0) {
                path.append(String.valueOf(count)); //结果特殊处理
                res.add(path.toString());
                path.setLength(len); //back tracking
            }
                 //全部压缩的状况要考虑输出纯数字
            else res.add(path.toString());
            return;
        }

        //visited?

        // branches
        // do abbr
        dfs(res, path, s, idx+1, count+1);

        // do NOT abbr
        if (count > 0) path.append(count);
        path.append(s.charAt(idx));
        dfs(res, path, s, idx+1, 0);
        path.setLength(len);

        // visited back?
    }

    public static void main(String args[]) {
        String word = new String("word");
        LC0320 sol = new LC0320();
        List<String> res = sol.generateAbbreviations(word);
        System.out.println(res);
    }
}
