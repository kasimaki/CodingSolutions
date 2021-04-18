// Predict The Winner
//Given an array of scores that are non-negative integers. Player 1 picks one of the numbers
// from either end of the array followed by the player 2 and then player 1 and so on.
// Each time a player picks a number, that number will not be available for the next player.
// This continues until all the scores have been chosen. The player with the maximum score wins.
//
//Given an array of scores, predict whether player 1 is the winner. You can assume each player
// plays to maximize his score.


package LeetCode;

public class LC0486 {
    public boolean PredictTheWinner(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        if (nums.length == 1) return true;

        int len = nums.length;

        // dp
        int[][] dp = new int[len][len];
        // bc?
        for (int i = 0; i < len; i++) dp[i][i] = nums[i];

        // for loop中记得跳过base case，或者把base case用if来check
        for (int i = len-2; i >= 0; i--) { //TODO check order
            for (int j = i+1; j < len; j++) {//TODO check order and start
                int leftEnd = nums[i] - dp[i+1][j]; //i依赖于右边
                int rightEnd = nums[j] - dp[i][j-1];//j依赖于左边
                dp[i][j] = Math.max(leftEnd, rightEnd);
            }
        }
        return dp[0][len-1] >=0;
    }
}
