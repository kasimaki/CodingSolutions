//Regular Expression Matching
// Given an input string (s) and a pattern (p), implement regular expression matching with support for '.' and '*' where:
//
//'.' Matches any single character.
//'*' Matches zero or more of the preceding element.
//The matching should cover the entire input string (not partial).

package LeetCode;

public class LC0010 {
    public boolean isMatch(String s, String p) {
        //cc
        if (s == null || p == null ) throw new IllegalArgumentException();
        if (s.length() == 0 && p.length() == 0) return true;
        if (s.length() > 0 && p.length() == 0 ) return false;//s空String是可以被pattern获取的
        //Boolean[][] mem = new Boolean[s.length()][p.length()]; //memorize
        return dfs(s, 0, p, 0);
    }

    private boolean dfs(String s, int idxS, String p, int idxP) {
        // bc
        if (idxP == p.length()) {
            return idxS == s.length(); // string and pattern both reach ends
        }
        if (idxP == p.length() - 1 || p.charAt(idxP+1) != '*') { // non a* pattern
            if (idxS < s.length() && isMatched(s, idxS, p, idxP)) { //if matched at current position, continue check
                return dfs(s, idxS+1, p, idxP+1);
            } else {
                return false;
            }
        } else {
            // visited?

            // branches
            int i = idxS-1;// TODO ????
            while (i < s.length() && (i < idxS || isMatched(s, i, p, idxP))) { //小于idxS直接进入，大于等于就match后进入下一个
                if (dfs(s, i+1, p, idxP+2)) {  //只有t才返回t
                    return true;
                }
                i++;
            }
            return false; // 没有t，就是false
        }

    }
    private boolean isMatched(String s, int idxS, String p, int idxP) {
        if (p.charAt(idxP) == '.') return true;
        if (p.charAt(idxP) == s.charAt(idxS)) return true;
        return false;
    }

    public static void main(String[] args) {
        long startTime = System.nanoTime();
        String s = new String("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab");
        String p = new String("a*a*");
        LC0010 sol = new LC0010();
        boolean res = sol.isMatch(s, p);
        System.out.println(res);
        long endTime   = System.nanoTime();
        long totalTime = endTime - startTime;
        System.out.println(totalTime);
    }
}
