package LeetCode;

public class LC0010DP {
    public boolean isMatch(String s, String p) {
        //cc
        if (s == null || p == null) throw new IllegalArgumentException();
        if (s.length() == 0 && p.length() == 0) return true;
        if (s.length() > 0 && p.length() == 0) return false;

        // Memorize
        Boolean[][]  mem = new Boolean[s.length() + 1][p.length() + 1];

        //for (int i = s.length(); i >=0)

        return mem[0][0];
    }
}
