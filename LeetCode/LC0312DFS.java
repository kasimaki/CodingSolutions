package LeetCode;

public class LC0312DFS {
    public int maxCoins(int[] nums) {
        //cc
        if (nums == null) throw new IllegalArgumentException();
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return nums[0] * nums[1] + Math.max(nums[0], nums[1]);

        int len = nums.length;
        int[][] mem = new int[len][len];
        return dfs(nums, 0, len-1, mem);
    }

    private int dfs(int[] nums, int i, int j, int[][] mem) {
        // pruning
        if (mem[i][j] > 0) return mem[i][j];
        // bc
//        if (i == j) {
//            mem[i][j] = nums[i];
//            return nums[i];
//        }

        // visited? no need, status never goes back

        // branch
        int max = 0;
        for (int k = i; k <= j; k++) { //最后打k，位置可以是[i,j]中的任意位置，即使是2端，也可以最后打
            int pointLeft = (k-1 >= i) ? dfs(nums, i, k-1, mem) : 0; //如果在两端，那么不存在的sub得到的点数就为0
            int pointRight = (k+1 <= j) ? dfs(nums, k+1, j, mem) : 0;
            int pointFinal = ((i-1 >= 0) ? nums[i-1] : 1) // 超出范围取1，否则取左边pivot nums[i]
                    * nums[k]
                    * ((j+1 < nums.length) ? nums[j+1] : 1);//超出范围取1，否则取右边pivot nums[j]
            max = Math.max(max, pointLeft + pointRight + pointFinal);
        }
        mem[i][j] = max;
        return max;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {3,1,5,8};
        LC0312DFS sol = new LC0312DFS();
        int res = sol.maxCoins(nums);
        System.out.println(res);
    }
}
