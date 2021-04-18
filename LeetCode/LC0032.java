package LeetCode;

import java.util.Stack;

public class LC0032 {

    public int longestValidParenthesesDP(String s) {
        if (s == null || s.length() == 0) return 0;

        int len = s.length();

        int[] dp = new int[len];
        int max = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == ')') {
                if (i == 0) continue;
                int prevLeftIdx = i - dp[i-1] - 1 ;
                if (prevLeftIdx >= 0 && s.charAt(prevLeftIdx) == '(') {
                    dp[i] = dp[i-1] + (prevLeftIdx == 0 ? 0 : dp[prevLeftIdx-1]) + 2;
                    max = Math.max(max, dp[i]);
                }
            }
        }
        return max;
    }

    public int longestValidParentheses(String s) {
        if (s == null || s.length() == 0) return 0;

        int len = s.length();
        int max = 0;
        int left = 0, right = 0;
        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') left ++;
            else right++;
            if (left == right) max = Math.max(max, left * 2);
            if (right > left) {
                left = 0;
                right = 0;
            }
        }

        left = 0;
        right = 0;
        for (int i = len-1; i >= 0; i--) {
            if (s.charAt(i) == '(') left ++;
            else right++;
            if (left == right) max = Math.max(max, left * 2);
            if (left > right) {
                left = 0;
                right = 0;
            }
        }

        return max;
    }


    public int longestValidParenthesesStack(String s) {
        if (s == null || s.length() == 0) return 0;

        int len = s.length();

        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int max = 0;

        for (int i = 0; i < len; i++) {
            if (s.charAt(i) == '(') {
                stack.push(i);
            } else {
                stack.pop();
                if (!stack.isEmpty()) {
                    max = Math.max(max, i - stack.peek());
                } else {
                    stack.push(i);
                }
            }
        }
        return max;
    }

}
