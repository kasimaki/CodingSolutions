//3Sum
// Given an array nums of n integers, are there elements a, b, c in nums such that a + b + c = 0?
// Find all unique triplets in the array which gives the sum of zero.
//
//Notice that the solution set must not contain duplicate triplets.
//

package LeetCode;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0015 {
    public List<List<Integer>> threeSum(int[] nums) {
        //cc
        if (nums == null || nums.length < 3) return new ArrayList<>();

        Arrays.sort(nums);
        int target = 0;
        List<List<Integer>> res = new ArrayList<>();


        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i-1] == nums[i]) continue;
            int subSum = target - nums[i];
            int left = i + 1;
            int right = nums.length-1;

            while (left < right) {
                if (nums[left] + nums[right] == subSum) {
                    res.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    while (left < nums.length-1 && nums[left] == nums[left+1]) left++;
                    while (right > i+1 && nums[right] == nums[right-1]) right--;
                    left++;
                    right--;

                } else if (nums[left] + nums[right] < subSum) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{0, 0, 0};
        LC0015 sol = new LC0015();
        List<List<Integer>> res = sol.threeSum(nums);
        System.out.println(res);
    }
}
