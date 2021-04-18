//Burst Balloons 特殊题，单独记
//You are given n balloons, indexed from 0 to n - 1. Each balloon is painted with a number on it represented by an array nums. You are asked to burst all the balloons.
//
//If you burst the ith balloon, you will get nums[i - 1] * nums[i] * nums[i + 1] coins. If i - 1 or i + 1 goes out of bounds of the array, then treat it as if there is a balloon with a 1 painted on it.
//
//Return the maximum coins you can collect by bursting the balloons wisely.

//独一无二的DP，没有简单的base case，需要逆向思考，分叉点后打掉，每次在边缘检测位置，才能得到base case


package LeetCode;

public class LC0312 {
    public int maxCoins(int[] nums) {
        //cc
        if (nums == null) throw new IllegalArgumentException();
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] * nums[1] + Math.max(nums[0], nums[1]);

        int len = nums.length;

        int[][] dp = new int[len][len];
        //no base case

        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j < len; j++) {
                int max = 0;
                for (int k = i; k <= j; k++) {
                    int pLeft = (k - 1 >= i) ? dp[i][k - 1] : 0; // j依赖于左边
                    int pRight = (k + 1 <= j) ? dp[k + 1][j] : 0; //i依赖于右边
                    int pFinal = ((i - 1 >= 0) ? nums[i - 1] : 1)
                            * nums[k]
                            * ((j + 1) < len ? nums[j + 1] : 1);//时刻假想有左分数alid的气球，出界就赋值为1，才能计算出最后打k的时候的分述
                    max = Math.max(max, pLeft + pRight + pFinal);
                }
                dp[i][j] = max;
            }
        }
        return dp[0][len - 1];
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,1,5,8};
        LC0312 sol = new LC0312();
        int res = sol.maxCoins(nums);
        System.out.println(res);
    }
}
