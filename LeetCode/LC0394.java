// Decoding String    Google Onsite
//Given an encoded string, return its decoded string.
//
//The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets
// is being repeated exactly k times. Note that k is guaranteed to be a positive integer.
//
//You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
//
//Furthermore, you may assume that the original data does not contain any digits and that digits are
// only for those repeat numbers, k. For example, there won't be input like 3a or 2[4].
//
//Input: s = "3[a]2[bc]"
//Output: "aaabcbc"
//
//Input: s = "3[a2[c]]"
//Output: "accaccacc"
package LeetCode;

import java.util.Stack;

public class LC0394 {
    public String decodeString(String s) {
        if (s == null || s.length() == 0) return "";
        Stack<Integer> numStack = new Stack<>();
        Stack<StringBuilder> strStack = new Stack<>();
        strStack.push(new StringBuilder());
        int val = 0;
        for (char ch : s.toCharArray()) {
            if (ch == '[') {
                numStack.push(val);
                val = 0;
                strStack.push(new StringBuilder());
            } else if (ch == ']') {
                StringBuilder cur = strStack.pop();
                int num = numStack.pop();
                String newStr = genNewStr(num, cur);
                strStack.peek().append(newStr);
            } else if (ch >= '0' && ch <= '9') {
                val = val * 10 + ch - '0';
            } else {
                strStack.peek().append(ch);
            }
        }
        return strStack.pop().toString();
    }

    private String genNewStr(int num, StringBuilder cur) {
        StringBuilder res = new StringBuilder();
        while (num-- > 0) {
            res.append(cur.toString());
        }
        return res.toString();
    }
}
