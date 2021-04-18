//Different Ways To Add Parentheses 类似于L95 NB Tree
//Given a string of numbers and operators, return all possible results from computing
// all the different possible ways to group numbers and operators. The valid operators are +, - and *.

//注意: Syntax Tree能大大降低难度。连续数字的处理，可以被dfs 最后cover。并且，要clarify if input is always valid

package LeetCode;

import java.util.ArrayList;
import java.util.List;
import JavaBasics.TreeNode;
import JavaBasics.WinLovePoint;

public class LC0241 {
    public List<Integer> diffWaysToCompute(String input) {
        //cc
        if (input == null) return new ArrayList<>();

        return dfs(input, 0, input.length()-1);
    }

    private List<Integer> dfs(String s, int start, int end) {
        List<Integer> res = new ArrayList<>();
        //bc only numbers TODO

        for (int i = start+1; i < end; i++) {//必须要等于，否则连续数字会被拆开
            char ch = s.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> leftList = dfs(s, start, i-1);
                List<Integer> rightList = dfs(s, i+1, end);

                for (Integer left : leftList) {
                    for (Integer right : rightList) {
                        res. add(cal(left, ch, right));
                    }
                }
            }
        }
        if (res.size() == 0) res.add(Integer.valueOf(s.substring(start, end+1)));
        return res;
    }
    private int cal(int left, char ch, int right) {
        if (ch == '+') return left + right;
        if (ch == '-') return left - right;
        if (ch == '*') return left * right;
        throw new RuntimeException();
    }
}
