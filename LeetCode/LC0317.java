package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC0317 {
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int shortestDistance(int[][] grid) {
        // cc
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) return -1;
        // OR throw new IllegalArgumentException();
        int rowSize = grid.length;
        int colSize = grid[0].length;
        int min = Integer.MAX_VALUE;

        int[][] cost = new int[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1) {
                    boolean ifExist = bfs(grid, i, j, cost);
                    if (!ifExist) return -1; // throw new RuntimeException("Not exist"); OPTION1
                }
            }
        }

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 0) { //0的地方才可以
                    min = Math.min(min, cost[i][j]);
                }
            }
        }
        //if (min == Integer.MAX_VALUE) return -1; // throw new RuntimeException("Not Exist");
        // 当0被标记的2占满，说明没有0能Touch所有1  OPTION2
        return min;
    }

    private boolean bfs (int[][] grid, int startRow, int startCol, int[][] cost) {
        int rowSize = grid.length;
        int colSize = grid[0].length; // 别忘记[0]
        int minLen = 1;
        boolean[][] visited = new boolean[rowSize][colSize];

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(startRow * colSize + startCol);

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / colSize;
                int j = cur % colSize;
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < rowSize && jj >= 0 && jj < colSize
                    && !visited[ii][jj]) {
                        if (grid[ii][jj] == 0 ) {
                            queue.offer(ii * colSize + jj);
                            cost[ii][jj] += minLen;
                        }
                        visited[ii][jj] = true; // OPTION1
                    }
                }
            }
            ++minLen;
        }
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1 && !visited[i][j]) return false; // 未遍历的1，说明与其他不互通，也就不存在有0可以连通所有1 OPTION1
                if (grid[i][j] == 0 && !visited[i][j]) grid[i][j] = 2; // 未遍历的0位置，以后也不会touch
                // 当一个不能被0 visit的1被BFS时，会导致所有0都变为2 OPTION2
            }
        }
        return true;
    }

    public static void main(String args[]) {
        LC0317 sol = new LC0317();
        int[][] grid = new int[][]{{1,0,2,0,1}, {0,0,0,0,0}, {0,0,1,0,0}};
        int len = sol.shortestDistance(grid);
        System.out.print(len);
    }
}
