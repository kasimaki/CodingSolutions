package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LC0301HashSet {
    public Set<String> removeInvalidParentheses(String s) {
        //cc
        if (s == null || s.length() == 0) return new HashSet<String>();

        int[] leftAndRight = getInvalidLeftAndRight(s);

        Set<String> res = new HashSet<>();
        StringBuilder path = new StringBuilder();

        dfs(res, path, s,0, leftAndRight[0], leftAndRight[1], 0);
        return res;
    }

    private int[] getInvalidLeftAndRight(String s) {
        //int n = s.length();
        int rmL = 0;
        int rmR = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') rmL++;
            if (ch == ')') {
                if (rmL == 0) rmR++;
                else rmL--;
            }
        }
        return new int[]{rmL, rmR};
    }

    private void dfs(Set<String> res, StringBuilder path, String s, int idx, int rmL, int rmR, int delta) {
        // bc success
        if (idx == s.length()) {
            if (rmL == 0 && rmR == 0 && delta == 0) { // rmL == 0 && rmR == 0也是判定条件
                res.add(path.toString());
            }
            return;
        }
        if (rmL < 0 || rmR < 0 || delta < 0) return;

        int len = path.length();
        // visited?

        if (s.charAt(idx) == '(') {
            // do remove
            dfs(res, path, s, idx+1, rmL-1, rmR, delta);

            // do NOT remove
            path.append('('); //跳过的都加上

            dfs(res, path, s, idx+1, rmL, rmR, delta+1);
            path.setLength(len);
        } else if (s.charAt(idx) == ')') {
            // do remove
            dfs(res, path, s, idx+1, rmL, rmR-1, delta);

            // do NOT remove
            path.append(')');
            dfs(res, path, s, idx+1, rmL, rmR, delta-1);
            path.setLength(len);
        } else {
            path.append(s.charAt(idx));
            dfs(res, path, s, idx+1, rmL, rmR, delta);
            path.setLength(len); //需要set back，因为非括号的位置后结果是错的，需要退回
        }


        //visited back?
    }
    public static void main(String[] args) {
        String s = new String("(a)())()");
        LC0301HashSet sol = new LC0301HashSet();
        Set<String> res = sol.removeInvalidParentheses(s);
        System.out.print(res);
    }
}
