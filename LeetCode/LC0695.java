package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC0695 {
    private static final int[][] DIRECTIONS = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};
    public int maxAreaOfIsland(int[][] grid) {
        //cc
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0)
            return 0;

        int rowSize = grid.length;
        int colSize = grid[0].length;

        Queue<Integer> queue = new LinkedList<>();

        int max = 0;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1) {
                    int curMax = 1;
                    queue.offer(i * colSize + j);
                    grid[i][j] = 2;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        while (size-- > 0) {
                            int cur = queue.poll();
                            int curI = cur / colSize;
                            int curJ = cur % colSize;
                            for (int[] dir : DIRECTIONS) {
                                int ii = curI + dir[0];
                                int jj = curJ + dir[1];
                                if (ii >= 0 && ii < rowSize && jj >= 0 && jj < colSize && grid[ii][jj] == 1) {
                                    grid[ii][jj] = 2;
                                    curMax++;
                                    queue.offer(ii * colSize + jj);
                                }
                            }
                        }
                    }
                    max = Math.max(max, curMax);
                }
            }
        }
        return max;
    }

}
