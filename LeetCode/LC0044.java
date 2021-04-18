//Wildcard Matching
//Given an input string (s) and a pattern (p), implement wildcard pattern matching with support
// for '?' and '*' where:
//
//'?' Matches any single character.
//'*' Matches any sequence of characters (including the empty sequence).
//The matching should cover the entire input string (not partial).
package LeetCode;

public class LC0044 {
    public boolean isMatch(String s, String p) {
        //cc
        if (s == null || p == null) return false;
        if (s.length() == 0 && p.length() == 0) return true;

        int lenS = s.length();
        int lenP = p.length();

        int i = 0, j = 0;
        int tempI = 0;
        int fistCharAfterLastStar = -1; // -1 means no star sign so far

        // s需要先匹配完成。如果p先完成，则返回false
        while (i < lenS) {
            if (j < lenP && (p.charAt(j) == '?' || s.charAt(i) == p.charAt(j))) { //正常匹配
                i++;
                j++;
            } else if (j < lenP && p.charAt(j) == '*') { //遇到星号，j向右移动一格，以便尝试与当前i匹配，如果不匹配，则算作与*匹配
                fistCharAfterLastStar = ++j;
                tempI = i;
            } else if (fistCharAfterLastStar != -1) { // unmatch, and try to go back to last start sign
                j = fistCharAfterLastStar;
                i = ++tempI;  //tempI需要跟*匹配，继续check下一个i
            } else {
                return false;
            }
        }

        // while loop 后，可能p还没走完
        // ******如果之后都是*，那可以走完，否则一旦不匹配，就是false
        while (j < lenP && p.charAt(j) == '*') {
            j++;
        }
        return j == lenP;

    }
}
