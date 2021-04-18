package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC0542 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0] == null
                || matrix[0] .length == 0) throw new IllegalArgumentException();

        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int minLen = 2; // TODO
        int[][] result = new int[rowSize][colSize];

        Queue<Integer> queue = new LinkedList<>();
        addAllZeros(matrix, queue, result);
        while (!queue.isEmpty()){
            int size = queue.size();
            while (size-- > 0){
                int cur = queue.poll();
                int i = cur / colSize;
                int j = cur % colSize;
                for (int[] dir : DIRECTIONS){
                    int ii = i + dir[0];
                    int jj = j + dir[1];
                    if (ii >= 0 && ii < rowSize && jj >= 0 && jj < colSize
                            && matrix[ii][jj] == 1 && result[ii][jj] == 0) { //查重要看到底有没有查，这里result没有结果才是没做过的
                        queue.offer(ii * colSize + jj);
                        result[ii][jj] = minLen;
                    }
                }
            }
            ++minLen;
        }
        return result;
    }

    private void addAllZeros(int[][] matrix, Queue queue, int[][] result){
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        for (int i = 0; i < rowSize; i++){
            for (int j = 0; j < colSize; j++){
                if (matrix[i][j] == 1) {
                    for (int[] dir : DIRECTIONS){
                        int ii = i + dir[0];
                        int jj = j + dir[1];
                        if (ii >= 0 && ii < rowSize && jj >= 0 && jj < colSize
                                && matrix[ii][jj] == 0) {
                            queue.offer(i * colSize + j);
                            result[i][j] = 1;
                            break;
                        }
                    }

                }
            }
        }
    }

    public static void main(String[] args){
        int[][] nums = {{0,0,0},{0,1,0},{1,1,1}};

        LC0542 so = new LC0542();
        int[][] res = so.updateMatrix(nums);
        System.out.println(res);
    }
}
