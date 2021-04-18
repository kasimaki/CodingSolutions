//Given an integer array nums that may contain duplicates, return all possible subsets (the power set).

package LeetCode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class LC0090 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException("Invalid input");
        int n = nums.length;
        int[] helper = new int[n];
        divideAndMerge(nums, 0, n - 1, helper); // Merge Sort

        List<List<Integer>> res = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        dfs(res, nums, path, 0);
        return res;
    }

    private void divideAndMerge(int[] nums, int left, int right, int[] helper) {
        if (left == right) return;
        int mid = left + (right - left) / 2;
        divideAndMerge(nums, left, mid, helper);
        divideAndMerge(nums, mid+1, right, helper);
        merge(nums, left, mid, right, helper);
    }

    private void merge(int[] nums, int left, int mid, int right, int[] helper) {
        for (int i = left; i <= mid; i++) {
            helper[i] = nums[i];
        }
        for (int j = mid+1; j <= right; j++) {
            helper[j] = nums[j];
        }
        int i = left;
        int j = mid+1;
        int c = left;
        while (i <= mid && j <= right) {
            int leftVal = helper[i];
            int rightVal = helper[j];
            if (leftVal < rightVal) {
                nums[c] = leftVal;
                i++;
            } else {
                nums[c] = rightVal;
                j++;
            }
            c++;
        }
        while (i <= mid) {
            nums[c] = helper[i];
            c++;
            i++;
        }
    }

    private void dfs(List<List<Integer>> res, int[] nums, List<Integer> path, int level ) {
        // bc success
        if (level == nums.length) {
            List<Integer> newRes = new ArrayList<>();
            for (Integer ele : path) newRes.add(ele);
            res.add(newRes);
            return;
        }

        //visited?
        // do add
        path.add(nums[level]);
        dfs(res, nums, path, level + 1);
        path.remove(path.size() - 1);

        // do not add
        while (level < nums.length - 1 && nums[level] == nums[level+1]) level++;

        dfs(res, nums, path, level+1);

    }

    public static void main(String[] args) {
        int[] nums = new int[]{5,5,5,5,5};
        LC0090 sol = new LC0090();
        List<List<Integer>> res = sol.subsetsWithDup(nums);
        System.out.print(res);
    }
}
