//Longest Increasing Path in a Matrix
//Given an m x n integers matrix, return the length of the longest increasing path in matrix.
//
//From each cell, you can either move in four directions: left, right, up, or down.
// You may not move diagonally or move outside the boundary (i.e., wrap-around is not allowed).
package LeetCode;

public class LC0329 {
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int longestIncreasingPath(int[][] matrix) {
        //cc
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            throw new IllegalArgumentException();
        if (matrix.length == 1 && matrix[0].length == 1) return 1;

        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int max = -1;

        // memorize
        int[][] mem = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++){
                max = Math.max(max, dfs(matrix, i, j, -1, mem));
            }
        }
        return max;
    }
    private int dfs(int[][] matrix, int i, int j, int prev, int[][] mem) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        //bc fail to go deeper
        if (i < 0 || i >= rowSize || j < 0 || j >= colSize || matrix[i][j] <= prev) return 0; //BFS中是写前一层里

        //visited ? no need with increasing order

        // read or check
        if (mem[i][j] > 0) return mem[i][j];

        // branch
        int max = -1;
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            int ret = dfs(matrix, ii, jj, matrix[i][j], mem);
            max = Math.max(max, ret);
        }
        mem[i][j] = max + 1; // store result in memory
        return max + 1;
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{9,9,4}, {6,6,8}, {2,1,1}};
        LC0329 sol = new LC0329();
        int res = sol.longestIncreasingPath(matrix);
        System.out.println(res);
    }
}
