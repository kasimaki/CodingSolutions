package LeetCode;

import java.util.HashMap;

public class LC0403 {
    public boolean canCross(int[] stones) {
        if (stones == null || stones.length == 0) return false;

        HashMap<Integer, Boolean>[] mem = new HashMap[stones.length];
        for (int i = 0; i < stones.length; i++) {
            mem[i] = new HashMap<>();
        }

        return jumpDFS(stones, 0, 0, mem);

    }

    private boolean jumpDFS(int[] stones, int curIdx, int preStep, HashMap<Integer, Boolean>[] mem){
        if (curIdx == stones.length-1) return true;

        Boolean status = mem[curIdx].get(preStep);
        if (status != null) return status;



        if (curIdx == 0) {
            if (stones[1] != 1) return false;
            return jumpDFS(stones, curIdx +1, 1, mem);
        }

        boolean ret;
        for (int i = curIdx + 1; i < stones.length; i++) {
            int stepDiff = stones[i] - stones[curIdx];
            if (stepDiff < preStep - 1) continue;
            else if (stepDiff > preStep + 1) break;
            else {
                ret = jumpDFS(stones, i,  stepDiff, mem);
                if (ret) {
                    mem[curIdx].put(preStep, true);
                    return true;
                }
            }
        }

        mem[curIdx].put(preStep, false);
        return false;

    }


}
