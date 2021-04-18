// Contains Duplicate I
//Given an array of integers, find if the array contains any duplicates.
//
//Your function should return true if any value appears at least twice in the array,
// and it should return false if every element is distinct.


package LeetCode;

import java.util.HashSet;
import java.util.Set;

public class LC0217 {
    public boolean containsDuplicate(int[] nums) {
        // cc
        if (nums == null || nums.length < 2) return false;

        Set<Integer> set = new HashSet<>();
        set.add(nums[0]);

        for (int i = 1; i < nums.length; i++) {
            if (!set.add(nums[i])) return true;
        }

        return false;
    }
}
