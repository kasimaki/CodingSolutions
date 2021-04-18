package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0046 {
    public List<List<Integer>> permute(int[] nums) {
        if (nums == null || nums.length == 0) return new ArrayList<>();

        List<List<Integer>> res = new ArrayList<>();

        dfs(res, nums, 0);
        return res;
    }

    private void dfs(List<List<Integer>> res, int[] nums, int level) {
        //bc
        if(level == nums.length) {
            List<Integer> newRes = new ArrayList<>();
            for (int ele : nums) {
                newRes.add(ele);
            }
            res.add(newRes);
            return;
        }

        //visited

        for (int i = level; i < nums.length; i++) {
            swap(nums, level, i);
            dfs(res, nums, level+1);
            swap(nums, level, i);
        }
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1, 2, 3};
        LC0046 sol = new LC0046();
        List<List<Integer>> res = sol.permute(nums);
        System.out.println(res);
    }
}
