package LeetCode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class LC0301Any {
    public String removeInvalidParentheses(String s) {
        //cc
        if (s == null || s.length() == 0) return null;

        StringBuilder path = new StringBuilder();

        int delta = 0;

        for (char ch : s.toCharArray()) {
            if (ch == '(') {
                delta++;
                path.append(ch);
            } else if (ch == ')') {
                if (delta > 0) {
                    delta --;
                    path.append(ch);
                }
            } else {
                path.append(ch);
            }

        }

        return path.toString();
    }


    public static void main(String[] args) {
        String s = new String("(a)())()");
        LC0301Any sol = new LC0301Any();
        String res = sol.removeInvalidParentheses(s);
        System.out.print(res);
    }
}
