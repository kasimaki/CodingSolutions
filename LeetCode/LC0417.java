package LeetCode;

import com.sun.jdi.connect.spi.TransportService;

import java.awt.*;
import java.util.*;
import java.util.List;

public class LC0417 {
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public List<List<Integer>> pacificAtlantic(int[][] matrix) {
        List<List<Integer>> res = new ArrayList<>();
        // cc
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            return res;

        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        boolean[][] pacific = new boolean[rowSize][colSize];
        boolean[][] atlantic = new boolean[rowSize][colSize];
        Queue<Integer> queue = new LinkedList<>();

        addPacificStartPoints(matrix, queue, pacific); //mark visited，因为一开始已经遍历
        bfs(matrix, queue, pacific, atlantic, res);

        addAtlanticStartPoints(matrix, queue, atlantic);
        bfs(matrix, queue, atlantic, pacific, res);

        return res;
    }

    private void addPacificStartPoints(int[][] matrix, Queue<Integer> queue, boolean[][] visited) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        for (int i = 0; i < rowSize; i++) {
            queue.offer(i * colSize);
            visited[i][0] = true;
        }

        for (int j = 1; j < colSize; j++) {//从1开始，避免跟上面重复
            queue.offer(j);
            visited[0][j] = true;
        }
    }

    private void addAtlanticStartPoints(int[][] matrix, Queue<Integer> queue, boolean[][] visited) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        for (int i = 0; i < rowSize; i++) {
            queue.offer(i * colSize + colSize - 1);
            visited[i][colSize - 1] = true;
        }

        for (int j = 0; j < colSize - 1; j++) {//结尾处去掉一个，避免跟上面重复
            queue.offer((rowSize - 1) * colSize + j);
            visited[rowSize - 1][j] = true;
        }
    }

    private void bfs(int[][] matrix, Queue<Integer> queue, boolean[][] self, boolean[][] another,
                     List<List<Integer>> res) {
        //交叉验证是否能到达，将3 pass降低为2 pass
        int rowSize = matrix.length;
        int colSize = matrix[0].length;

        while (!queue.isEmpty()) {
            //int size = queue.size(); // 不用分层
            //while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / colSize;
                int j = cur % colSize;
                if (another[i][j]) res.add(Arrays.asList(i, j)); //别做边check另一个，需要把queue初始的也check
                for (int[] dir : DIRECTIONS) {
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < rowSize && jj >= 0 && jj < colSize
                        && matrix [ii][jj] >= matrix[i][j] && !self[ii][jj]) {
                        queue.offer(ii * colSize + jj);
                        self[ii][jj] = true;
                    }
                }
            //}
            //++minLen;
        }
    }
    public static void main(String args[]) {
//        List<List<Integer>> list = new ArrayList<>();
//        list.add(Arrays.asList(1,2,2,3,5));
//        list.add(Arrays.asList(3,2,3,4,4));
//        list.add(Arrays.asList(2,4,5,3,1));
//        list.add(Arrays.asList(6,7,1,4,5));
//        list.add(Arrays.asList(5,1,1,2,4));
        int[][] matrix = new int[][]{
            {1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
        LC0417 sol = new LC0417();
        List<List<Integer>> res = sol.pacificAtlantic(matrix);
        System.out.println(res);
    }
}
