package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC0296 {
    //private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int minTotalDistance( int[][] grid) {
        // cc
        if (grid == null || grid.length == 0 || grid[0] == null
                || grid[0].length == 0) throw new IllegalArgumentException();

        int rowSize = grid.length;
        int colSize = grid[0].length;
        int[][] cost = new int[rowSize][colSize];

        int min = Integer.MAX_VALUE;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1) {
                    bfs(grid, i, j, cost);
                }
            }
        }

        boolean valid = false;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (cost[i][j] > 0) {
                    valid = true;
                    break;
                }
            }
        }
        if (!valid) return -1;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                min = Math.min(min, cost[i][j]);
            }
        }

        return min;
    }

    private void bfs(int[][] grid, int startRow, int startCol, int[][] cost) {
        int rowSize = grid.length;
        int colSize = grid[0].length;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                cost[i][j] += Math.abs(i - startRow) + Math.abs(j - startCol);
            }
        }

    }
}
