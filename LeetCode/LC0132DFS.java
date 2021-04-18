package LeetCode;

public class LC0132DFS {
    public int minCut(String s) {
        //cc
        if (s == null) throw new IllegalArgumentException();
        if (s.length() < 2) return 0;

        int[] mem = new int[s.length()+1];
        //mem[1] = 0;
        Boolean[][] memPal = new Boolean[s.length()+1][s.length()+1];
        return dfs(s, s.length(), mem, memPal); // [0, idx) min number of cuts
    }

    private int dfs(String s, int idx, int[] mem, Boolean[][] memPal) {
        // bc, only on char, always succeed
        if (idx == 0) return -1;
        if (idx == 1) return 0;

        if (mem[idx] > 0) return mem[idx];

        // visited? no need, one dir
        int min = Integer.MAX_VALUE;
        for (int i = idx -1; i >= 0; i--) {
            String rightSub = s.substring(i, idx);
            if (isPal(s, i, idx, memPal)) {
                int ret = dfs(s, i, mem, memPal);
                min = Math.min(min, ret);
            }
        }
        mem[idx] = min + 1;
        return min + 1;
    }

    private boolean isPal(String s, int i, int j, Boolean[][] mem) {
        if (mem[i][j] != null) return mem[i][j];
        // bc
        if ((i + 1 )== j || i == j) {
            mem[i][j] = true;
            return true;
        }

        if (s.charAt(i) == s.charAt(j-1)) {
            mem[i][j] = isPal(s, i+1, j-1, mem);
        } else {
            mem[i][j] = false;
        }
        return mem[i][j];
    }

    public static void main(String[] args) {
        String s = new String("aab");
        LC0132DFS sol = new LC0132DFS();
        int res = sol.minCut(s);
        System.out.println(res);
    }
}
