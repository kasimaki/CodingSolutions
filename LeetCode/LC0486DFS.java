package LeetCode;

public class LC0486DFS {
    public boolean PredictTheWinner(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) throw new IllegalArgumentException();
        if (nums.length == 1) return true;

        int len = nums.length;

        // Pruning
        int[][] mem = new int[len][len];

        return dfs(nums, 0, len-1, mem) >= 0;

    }

    private int dfs(int[] nums, int i, int j, int[][] mem) {
        if (mem[i][j] > 0) return mem[i][j];
        //bc
        if (i == j) {
            mem[i][j] = nums[i];
            return nums[i];
        }

        // visited ? no need one direction

        // branch
        int leftEnd = nums[i] - dfs(nums, i+1, j, mem); //对手的优势，在这一层需要减去
        int rightEnd = nums[j] - dfs(nums, i, j-1, mem);
        mem[i][j] = Math.max(leftEnd, rightEnd);
        return mem[i][j];//返回自己最大的优势
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 5, 2};
        LC0486DFS sol = new LC0486DFS();
        boolean res = sol.PredictTheWinner(nums);
        System.out.println(res);
    }
}
