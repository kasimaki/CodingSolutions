package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC0039 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        if (candidates == null || candidates.length == 0) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        //List<Integer> path = new ArrayList<>();
        int[] path = new int[candidates.length];

        dfs(res, path, candidates, 0, target);

        return res;
    }

    private void dfs(List<List<Integer>> res, int[] path, int[] candidates, int level, int target) {
        //bc
        if (target == 0) {
            List<Integer> newRes = new ArrayList<>();
            convert(newRes, path, candidates);
            res.add(newRes);
            return;
        }
        if (target < 0) {
            return;
        }

        if (level == candidates.length) {
            return;
        }
        //visited?

        //do
        int maxCount = target / candidates[level];
        for (int i = 0; i <= maxCount; i++) {
            path[level] = i;
            dfs(res, path, candidates, level + 1, target - candidates[level] * i);
            path[level] = 0;
        }

    }

    private void convert(List<Integer> newRes, int[] path, int[] candidates) {
        for (int i = 0; i < path.length; i++) {
            if (path[i] == 0) continue;
            for (int j = 0; j < path[i]; j++) {
                newRes.add(candidates[i]);
            }
        }
    }


}
