package LeetCode;

public class LC0300DFS {
    public int lengthOfLIS(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int len = nums.length;
        // mem
        int[] mem = new int[len];
        // dfs(i) longest LIS for [0,i] ending with i
        return dfs(nums, len-1, mem);
    }

    private int dfs(int[] nums, int idx, int[] mem) {
        //bc
        if (idx == 0) return 1;

        if (mem[idx] > 0) return mem[idx];

        // visited? no need

        //branch
        int max = 1;// TODO check initial max
        for (int i = idx - 1; i >= 0; i--) {
            if (nums[i] < nums[idx]) {
                max = Math.max(max, dfs(nums, i, mem) + 1);
            }
        }
        mem[idx] = max;
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {7, 7, 7, 7, 7};
        LC0300DFS sol = new LC0300DFS();
        int res = sol.lengthOfLIS(nums);
        System.out.println(res);
    }
}
