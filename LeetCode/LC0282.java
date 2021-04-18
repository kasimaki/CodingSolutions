//Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
// (not unary) +, -, or * between the digits so they evaluate to the target value.


//此题有个加减乘的evaluate，需要手动实现，需要carry on2个量，sum和lastVal，乘法的操作会略有不同
package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class LC0282 {
    public List<String> addOperators(String num, int target) { //长整形
        //cc
        if(num == null || num.length() == 0) return new ArrayList<>();;

        List<String> res = new ArrayList<>();
        StringBuilder path = new StringBuilder();

        dfs(res, path, num, (long) target, 0, 0, 0);

        return res;
    }

    private void dfs(List<String> res, StringBuilder path, String s, long target, int idx, long sum, long last) {
        // bc success
        if (idx == s.length()) {
            if (sum == target) {
                res.add(path.toString());
            }
            return;
        }
        //TODO fail case

        int len = path.length();
        long val = 0;
        //visited?
        for (int i = idx; i < s.length(); i++) { //TODO check range
            //if (s.charAt(idx) == '0' && i > idx) break;
            val = 10 * val + (s.charAt(i) - '0');
            if (path.length() == 0) {
                path.append(val);
                dfs(res, path, s, target, i+1, sum+val, val);
                path.setLength(0);
                continue;//一定要跳过
            }
            // +
            path.append("+" + val); //append
            dfs(res, path, s, target, i+1, sum+val, val);
            path.setLength(len);

            // -
            path.append("-" + val); //append
            dfs(res, path, s, target, i+1, sum-val, -val);
            path.setLength(len);

            // +
            path.append("*" + val); //append
            dfs(res, path, s, target, i+1, sum - last + last * val, last * val);
            path.setLength(len);
            if (val == 0) break;
        }

    }
    public static void main(String args[]) {
        String s = new String("00");
        int target = 0;
        LC0282 sol = new LC0282();
        List<String> res = sol.addOperators(s, target);
        System.out.print(res);
    }
}
