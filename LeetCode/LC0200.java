//Number of Islands I
//Given an m x n 2d grid map of '1's (land) and '0's (water), return the number of islands.
//
//An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges of the grid are all surrounded by water.
//

package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC0200 {
    private static final int[][] DIRECTIONS = new int[][] {{-1,0}, {0,1}, {1,0}, {0,-1}};
    public int numIslands(char[][] grid) {
        //cc
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;

        int rowSize = grid.length;
        int colSize = grid[0].length;

        //boolean[][] visited = new boolean[rowSize][colSize];
        Queue<Integer> queue = new LinkedList<>();

        int numOfIslands = 0;

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] != '1') continue;
                int thisPos = i * colSize + j;
                numOfIslands++;
                queue.offer(thisPos);
                while (!queue.isEmpty()) {
                    int size = queue.size();
                    while (size-- > 0) {
                        int cur = queue.poll();
                        int curI = cur / colSize;
                        int curJ = cur % colSize;
                        for (int[] dir : DIRECTIONS) {
                            int ii = curI + dir[0];
                            int jj = curJ + dir[1];
                            if (ii >= 0 && ii < rowSize && jj >= 0 && jj < colSize
                                && grid[ii][jj] == '1') {
                                queue.offer(ii * colSize + jj);
                                grid[ii][jj] = '2';
                            }
                        }
                    }
                }
            }
        }
        return numOfIslands;

    }

}
