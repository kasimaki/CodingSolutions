//Ternary Expression Parser
//Given a string representing arbitrarily nested ternary expressions, calculate the result of the expression.
// You can always assume that the given expression is valid and only consists of digits 0-9, ?, :, T and F
// (T and F represent True and False respectively).
//
//Note:
//
//The length of the given string is ≤ 10000.
//Each number will contain only one digit.
//The conditional expressions group right-to-left (as usual in most languages).
//The condition will always be either T or F. That is, the condition will never be a digit.
//The result of the expression will always evaluate to either a digit 0-9, T or F.
//
//Input: "F?1:T?4:5"
//
//Output: "4"

//逻辑是从右到左

package LeetCode;

import java.util.Stack;

public class LC0439 {
    public String parseTernary(String expression) {
        //cc
        if (expression == null || expression.length() == 0)
            throw new IllegalArgumentException();

        Stack<String> stack = new Stack<>();
        int len = expression.length();
        int i = len -1;
        while (i >= 0) {
            char ch = expression.charAt(i);
            if (ch == ' ' || ch == ':') {
                i--;
            } else if (ch == '?') {
                String strT = stack.pop();
                String strF = stack.pop();
                char condition = expression.charAt(--i);
                while (condition == ' ') {
                    condition = expression.charAt(--i);
                }
                if (condition == 'T') stack.push(strT);
                else if (condition == 'F') stack.push(strF);
                else throw new IllegalArgumentException();
                i--;
            } else {
                int tempI = i +1;
                while (i > 0 && expression.charAt(i) != '?' && expression.charAt(i) != ':') {
                    i--;
                }
                stack.push(expression.substring(i+1, tempI));
            }
        }
        return stack.pop();
    }
}
