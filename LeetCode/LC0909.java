package LeetCode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC0909 {
    public int snakesAndLadders(int[][] board) {
        //cc
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0) return -1;

        int rowSize = board.length;
        int colSize = board[0].length;
        boolean[][] visited = new boolean[rowSize][colSize];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer((rowSize-1) * colSize);
        visited[rowSize-1][0] = true;
        int minLen = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                int cur = queue.poll();
                int i = cur / colSize;
                int j = cur % colSize;
                List<int[]> nexts = convert(rowSize, colSize, i, j);
                for (int[] next : nexts) {
                    int ii = next[0];
                    int jj = next[1];
                    if (!visited[ii][jj]) {
                        visited[ii][jj] = true;
                        if (ii == 0 && jj == ((rowSize % 2 == 0) ? 0 : colSize -1)) return minLen;
                        if (board[ii][jj] > 0) {
                            if (board[ii][jj] == rowSize * colSize) return minLen;
                            int[] nextInd = locToInd(board[ii][jj], rowSize, colSize);
                            int nextI = nextInd[0];
                            int nextJ = nextInd[1];
                            if (!visited[nextI][nextJ]) {
                                queue.offer(nextI * colSize + nextJ);
                                visited[nextI][nextJ] = true;
                            }
                        } else {
                            queue.offer(ii * colSize + jj);
                        }
                    }
                }
            }
            minLen++;
        }
        return -1;
    }

    private int[] locToInd(int next, int rowSize, int colSize) {
        int rowDiff = (next - 1) / colSize;
        int colDiff = (next - 1) % colSize;
        boolean leftToRight = rowDiff % 2 == 0;

        if (leftToRight) {
            return new int[] {rowSize - rowDiff - 1, colDiff};
        } else {
            return new int[] {rowSize - rowDiff - 1, colSize - colDiff -1};
        }
    }

    public List<int[]> convert(int rowSize, int colSize, int i, int j) {
        List<int[]> nexts = new ArrayList<>();
        boolean leftToRight = (rowSize - i) % 2 == 1;
        int curCol = leftToRight ? j + 1 : j - 1;
        int curRow = i;
        while (nexts.size() < 6 && curRow >= 0) {
            if (leftToRight) {
                if (curCol < colSize) {
                    nexts.add(new int[] {curRow, curCol});
                    curCol++;
                } else {
                    leftToRight = false;
                    curCol = colSize -1;
                    curRow--;
                }
            } else {
                if (curCol >= 0) {
                    nexts.add(new int[] {curRow, curCol});
                    curCol--;
                } else {
                    leftToRight = true;
                    curCol = 0;
                    curRow--;
                }
            }
        }

        return nexts;
    }

    public static void main(String[] args) {
//        int[][] board = new int[][] {
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 35, -1, -1, 13, -1},
//                {-1, -1, -1, -1, -1, -1},
//                {-1, 15, -1, -1, -1, -1}
//        };
        int[][] board = new int[][] {{-1,4,-1},{6,2,6},{-1,3,-1}};
        LC0909 sol = new LC0909();
        List<int[]>  rr = sol.convert(6,6,4,5);
        int res = sol.snakesAndLadders(board);
        System.out.println(res);
    }
}
