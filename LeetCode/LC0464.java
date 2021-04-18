// Can I Win
//In the "100 game" two players take turns adding, to a running total, any integer from 1 to 10.
// The player who first causes the running total to reach or exceed 100 wins.
//
//What if we change the game so that players cannot re-use integers?
//
//For example, two players might take turns drawing from a common pool of numbers
// from 1 to 15 without replacement until they reach a total >= 100.
//
//Given two integers maxChoosableInteger and desiredTotal, return true if the first player
// to move can force a win, otherwise return false. Assume both players play optimally.

// only can be used once

package LeetCode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC0464 {
    public boolean canIWinBit(int maxChoosableInteger, int desiredTotal) {
        //cc
        if (maxChoosableInteger < 1) throw new IllegalArgumentException();

        if (desiredTotal > (1 + maxChoosableInteger) * maxChoosableInteger) return false;
        if (desiredTotal < 2) return true;

        // HashSet --> boolean[] --> Bit
        boolean[] pool = new boolean[maxChoosableInteger+1];
        Arrays.fill(pool, true); // void fill
        Map<Integer, Boolean> mem = new HashMap<>();

        return dfsBit(maxChoosableInteger, desiredTotal, pool, 0, mem);
    }

    private boolean dfsBit(int max, int target, boolean[] pool, int accuSum, Map<Integer, Boolean> mem) {
        int status = boolToBit(pool);
        Boolean ifWin = mem.get(status);
        if (ifWin != null) return ifWin;

        // bc success
        if (accuSum >= target) {
            mem.put(status, false);
            return false;
        }
        // fail?

        // visited?
        // branch
        for (int i = 1; i <= max; i++) {
            if (pool[i]) {//if true, we can pick
                pool[i] = false;
                boolean ret = dfsBit(max, target, pool, accuSum + i, mem);
                pool[i] = true;
                if (!ret) {
                    mem.put(status, true);
                    return true;
                }
            }
        }
        mem.put(status, false);
        return false;
    }

    private int boolToBit(boolean[] pool) {
        int status = 0;
        for (int i = 0; i < pool.length; i++) {
            if (pool[i]) status = status | (1 << i);
        }
        return status;
    }


    public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
        //cc
        if (maxChoosableInteger < 1) throw new IllegalArgumentException();

        if (desiredTotal > (1 + maxChoosableInteger) * maxChoosableInteger) return false;
        if (desiredTotal == 0) return true;

        // HashSet --> boolean[] --> Bit
        boolean[] pool = new boolean[maxChoosableInteger+1];
        Arrays.fill(pool, true); // void fill
        return dfs(maxChoosableInteger, desiredTotal, pool, 0);
    }

    private boolean dfs(int max, int target, boolean[] pool, int accuSum) {
        // bc success
        if (accuSum >= target) return false;
        // fail?

        // visited?
        // branch
        for (int i = 1; i <= max; i++) {
            if (pool[i]) {//if true, we can pick
                pool[i] = false;
                boolean ret = dfs(max, target, pool, accuSum + i);
                pool[i] = true;
                if (!ret) return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int max = 10;
        int target = 11;
        LC0464 sol = new LC0464();
        boolean res = sol.canIWinBit(max, target);
        System.out.println(res);
    }
}
