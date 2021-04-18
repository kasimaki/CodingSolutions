//Longest Substring with At Most Two Distinct Characters
//Given a string s , find the length of the longest substring t  that contains at most 2 distinct characters.

package LeetCode;

public class LC0159 {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        char c1 = '\0';
        char c2 = '\0';
        int idx1 = -1;
        int idx2 = -1;
        int left = 0;
        int max = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (c1 == ch) {
                idx1 = right;
            } else if (c2 == ch) {
                idx2 = right;
            } else {
                if (idx1 < idx2) {
                    left = idx1 + 1; //left shrinks
                    idx1 = right;
                    c1 = ch;
                } else {
                    left = idx2 + 1;
                    idx2 = right;
                    c2 = ch;
                }
            }
            max = Math.max(max, right - left +1);
        }
        return max;
    }

    public static void main(String[] args){
        String s = new String("eceba");
        LC0159 sol = new LC0159();
        int res = sol.lengthOfLongestSubstringTwoDistinct(s);
        System.out.println(res);
    }
}
