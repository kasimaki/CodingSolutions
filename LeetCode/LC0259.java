// 3Sum Smaller
//Given an array of n integers nums and an integer target, find the number of
// index triplets i, j, k with 0 <= i < j < k < n that satisfy the condition
// nums[i] + nums[j] + nums[k] < target.

package LeetCode;

import java.util.Arrays;

public class LC0259 {
    public int threeSumSmaller(int[] nums, int target) {
        // cc
        if (nums == null || nums.length < 3) return 0;
        int res = 0;
        Arrays.sort(nums);

        for (int i = 0; i < nums.length; i++) {
            int subTarget = target - nums[i];
            int left = i+1;
            int right = nums.length - 1;

            while (left < right) {
                int subSum = nums[left] + nums[right];
                if (subSum < subTarget) {
                    res += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        return res;
    }
}
