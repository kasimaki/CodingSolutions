//Longest Increasing Subsequences
//Given an integer array nums, return the length of the longest strictly increasing subsequence.
//
//A subsequence is a sequence that can be derived from an array by deleting some or no elements
// without changing the order of the remaining elements. For example, [3,6,2,7] is a subsequence
// of the array [0,3,1,6,2,2,7].


package LeetCode;

public class LC0300 {
    public int lengthOfLIS(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int len = nums.length;
        // dp
        int[] dp = new int[len];
        // dp[i] longest LIS for [0,i] ending with i
        // dp[i] = max(dp[j] + 1) if nums[j] < nums[i]
        dp[0] = 1;  //bc

        int max = 1;
        for (int i = 1; i < len; i++) {
            dp[i] = 1;//每一个位置如果成为新的sequence的最小值，应该以1开始，对应DPS中每层DFS找max都是1开始
            for (int j = 0; j < i; j++) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1); //find max for ending with i
                }
            }
            max = Math.max(max, dp[i]);// update global max
        }

        return max;
    }


    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0 , 3, 2, 3};
        LC0300 sol = new LC0300();
        int res = sol.lengthOfLIS(nums);
        System.out.println(res);
    }
}
