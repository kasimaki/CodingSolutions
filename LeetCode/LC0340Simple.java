package LeetCode;

public class LC0340Simple {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // cc
        if (s == null) throw new IllegalArgumentException();
        if (s.length() <= k) return s.length();

        int[] count = new int[256]; //Extended ASCII Codes
        int numOfChars = 0;
        int max = 0;
        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (count[ch]++ == 0) {
                numOfChars++;
            }
            while (numOfChars > k) {
                if (--count[s.charAt(left++)] == 0) {
                    numOfChars--;
                }
            }
        max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
