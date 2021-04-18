package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC0078 {
    public List<List<Integer>> subsets(int[] nums) {
        //cc
        if (nums == null || nums.length == 0)
            return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(res, path, nums, 0);

        return res;
    }

    private void dfs(List<List<Integer>> res, List<Integer> path, int[] nums, int level) {
        //bc
        if (level == nums.length) {
            List<Integer> newRes = new ArrayList<>();
            for (Integer ele : path) {
                newRes.add(ele);
            }
            res.add(newRes);
            return;
        }

        //visited? no need
        int len = path.size();
        //do
        path.add(nums[level]);
        dfs(res, path, nums, level + 1);
        path.remove(len);

        //no do
        dfs(res, path, nums, level + 1);
    }

}
