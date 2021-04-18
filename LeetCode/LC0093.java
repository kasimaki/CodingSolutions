//Restore IP Addresses
//        Given a string s containing only digits, return all possible valid IP addresses that can be obtained from s. You can return them in any order.
//
//        A valid IP address consists of exactly four integers, each integer is between 0 and 255, separated by single dots and cannot have leading zeros. For example, "0.1.2.201" and "192.168.1.1" are valid IP addresses and "0.011.255.245", "192.168.1.312" and "192.168@1.1" are invalid IP addresses.


package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC0093 {
        public List<String> restoreIpAddresses(String s) {
                // cc
                if (s == null || s.length() == 0 || s.length() > 12) return new ArrayList<>();

                List<String> res = new ArrayList<>();
                StringBuilder path = new StringBuilder();
                dfs(res, path, s, 0, 0);

                return res;
        }
        private void dfs(List<String> res, StringBuilder path, String s, int idx, int part) {
                // base case success
                if (idx == s.length()) {
                        if (part == 4) {
                                String newRes = path.toString();
                                res.add(newRes.substring(0,newRes.length()-1));
                        }
                        // base case fail
                        return;
                }
                //if (idx == s.length()) return; //过短的情况，也需要停止，因为input是idk+1，要check
                int len = path.length();
                //visited
                if (s.charAt(idx) == '0') {
                        path.append(0 + ".");
                        dfs(res, path, s, idx+1, part+1);
                        path.setLength(len);
                } else {
                        for (int i = 1; i <= 3; i++) { //TODO i range
                                if (idx + i > s.length()) break; //如果搜索越界，需要跳出，因为idx+i，要check右边界
                                int val = Integer.valueOf(s.substring(idx, idx+i));
                                if (val > 255) break;

                                path.append(val + ".");
                                dfs(res, path, s, idx+i, part+1);
                                path.setLength(len);
                        }
                }


                // visited back
        }

        public static void main(String args[]) {
                String s = new String("1111");
                LC0093 sol = new LC0093();
                List<String> res = sol.restoreIpAddresses(s);
                System.out.print(res);
        }
}
