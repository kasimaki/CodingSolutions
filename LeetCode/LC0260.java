//Single Number III
//Given an integer array nums, in which exactly two elements appear only once and
// all the other elements appear exactly twice. Find the two elements that appear only once.
// You can return the answer in any order.
//
//Follow up: Your algorithm should run in linear runtime complexity.
// Could you implement it using only constant space complexity?

package LeetCode;

public class LC0260 {
    public int[] singleNumber(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException("Invalid input");

        int xor = 0;
        for (int n : nums) {
            xor ^= n;
        }

        int count = 0;
        for (int i = 0; i < 32; i++) {
            if ((xor & (1 << i)) != 0) {
                count = i;
                break;
            }
        }

        int group1 = 0;
        for (int n : nums) {
            if ((n & (1 << count)) != 0) {
                group1 ^= n;
            }
        }
        return new int[] {group1, xor ^ group1};
    }
}
