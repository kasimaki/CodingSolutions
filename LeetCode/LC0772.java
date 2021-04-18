//Calculator III 加减乘括号  高频题，按结构理解
//Implement a basic calculator to evaluate a simple expression string.
//
//The expression string contains only non-negative integers, '+', '-', '*', '/' operators,
// and open '(' and closing parentheses ')'. The integer division should truncate toward zero.
//
//You may assume that the given expression is always valid.
// All intermediate results will be in the range of [-2^31, 2^31 - 1].

package LeetCode;

import java.util.HashMap;
import java.util.Stack;

public class LC0772 {
    private HashMap<Character, Integer> oprMap;
    private Stack<Integer> numStack;
    private Stack<Character> oprStack;

    public LC0772() {
        this.oprMap = new HashMap<>();
        oprMap.put('+', 1);
        oprMap.put('-', 1);
        oprMap.put('*', 2);
        oprMap.put('/', 2);
        this.numStack = new Stack<>();
        this.oprStack = new Stack<>();
    }

    public int calculate(String s) {
        //cc
        if (s == null || s.length() == 0) throw new IllegalArgumentException();
        int len = s.length();

        processStacks('(', -1, s);

        int i = 0;
        while (i < len) {
            char ch = s.charAt(i);

            if (ch == ' ') {
                i++;
            } else if (ch >= '0' && ch <= '9') {
                int val = 0;
                while (i < len && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                    val = val * 10 + (s.charAt(i) - '0');
                    i++;
                }
                numStack.push(val);
            } else if (ch == '(' || ch == ')' || oprMap.containsKey(ch)) {
                processStacks(ch, i, s);
                i++;
            } else {
                throw new IllegalArgumentException("Invalid operator");
            }

        }
        processStacks(')', len, s);
        return numStack.pop();

    }

    private void processStacks(char ch, int i, String s) {
        if (ch == '(') {
            int idx = i + 1;
            while (idx < s.length()) {
                char nextCh = s.charAt(idx);
                if (nextCh == ' ') idx++;
                else {
                    if (nextCh == '-') numStack.push(0);
                    break;
                }
            }
            oprStack.push(ch);
        } else if (ch == ')') {
            while (!oprStack.isEmpty()) {
                char opr = oprStack.peek();//先peek再算
                if (opr == '(') {
                    oprStack.pop();
                    break;
                }
                popAndCal();
            }
        } else {
            while (!oprStack.isEmpty()) {
                Integer prevOprLevel = oprMap.get(oprStack.peek());
                if (prevOprLevel == null ||prevOprLevel < oprMap.get(ch)) {
                    break;
                }
                popAndCal();
            }
            oprStack.push(ch); //别忘了
        }
    }

    private void popAndCal() {
        int num2 = numStack.pop();
        int num1 = numStack.pop();
        char opr = oprStack.pop();
        numStack.push(cal(num1,num2,opr));
    }

    private Integer cal(int num1, int num2, char opr) {
        if (opr == '+') {
            return num1 + num2;
        }
        if (opr == '-') {
            return num1 - num2;
        }
        if (opr == '*') {
            return num1 * num2;
        }
        if (opr == '/') {
            return num1 / num2;
        }
        throw new IllegalArgumentException();
    }

    public static void main(String[] args) {
        String s = new String("(2+6*3+5-(3*14/7+2)*5)+3");
        LC0772 sol = new LC0772();
        int res = sol.calculate(s);
        System.out.println(res);
    }
}
