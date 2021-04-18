// Contains Duplicate III
//Given an array of integers, find out whether there are two distinct indices i and j in the array
// such that the absolute difference between nums[i] and nums[j] is at most t and
// the absolute difference between i and j is at most k.

//nums[i] - nums[j] | <= t && | i - j | <= k

// search range of targets --> TreeSet
package LeetCode;

//import java.util.Set;
import java.util.TreeSet;

public class LC0220 {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        // cc
        if (nums == null || nums.length < 2 || k <= 0 || t < 0) return false;

        TreeSet<Long> set = new TreeSet<>();

        for (int i = 0; i < nums.length; i++) { //start from 0 --> edge case
            if (i - k > 0) set.remove((long) nums[i-k-1]);

            long upperBound = (long) nums[i] + t;
            long lowerBound = (long) nums[i] - t;

            Long val = set.floor(upperBound); // .floor() return Integer
            if (val != null && val >= lowerBound) return true;
            if (!set.add((long)nums[i])) return true;
        }
        return false;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,5,9,1,5,9};
        int k = 2;
        int t = 3;
        LC0220 sol = new LC0220();
        boolean res = sol.containsNearbyAlmostDuplicate(nums, k, t);
        System.out.println(res);
    }
}
