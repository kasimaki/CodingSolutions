// Contains Duplicate II
// Given an array of integers and an integer k, find out whether there are two distinct indices i and j
// in the array such that nums[i] = nums[j] and the absolute difference between i and j is at most k.

// nums[i] - nums[j] | == t && | i - j | <= k
package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class LC0219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        //cc
        if (nums == null || nums.length < 2 || k <= 0) return false;

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (i > k) set.remove(nums[i-k-1]); //超出范围内的才删除
            if (!set.add(nums[i])) return true;
        }
        return false;
    }
}
