// Increasing Triplet Subsequence L300简化版
//Given an integer array nums, return true if there exists a triple of indices (i, j, k)
// such that i < j < k and nums[i] < nums[j] < nums[k]. If no such indices exists, return false.
//
//Follow up: Could you implement a solution that runs in O(n) time complexity and O(1) space complexity?

package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC0334 {
    public boolean increasingTriplet(int[] nums) {
        //cc
        if (nums == null || nums.length < 3) return false;

        List<Integer> buffer = new ArrayList<>();

        for (int num : nums) {
            int idx = findSmallestLarger(buffer, num);
            if (idx >= 2) return true;
            if (idx >= buffer.size()) {
                buffer.add(num);
            } else {
                buffer.set(idx, num);
            }
        }
        return false;
    }

    private int findSmallestLarger(List<Integer> buffer, int num) {

        for (int i = 0; i < buffer.size(); i++) {
            if (buffer.get(i) > num) return i;
        }
        return buffer.size();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{5, 6, 1, 3, 4};
        LC0334 sol = new LC0334();
        boolean res = sol.increasingTriplet(nums);
        System.out.println(res);
    }
}
