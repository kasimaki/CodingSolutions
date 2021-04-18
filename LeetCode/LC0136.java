// Single Number
//Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.
//
//Follow up: Could you implement a solution with a linear runtime complexity and without using extra memory?
package LeetCode;

public class LC0136 {
    public int singleNumber(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }
        return xor;
    }
}
