// 因为可以比较下层返回的长度，所以不同于BFS返回所有路径，这里可以正向存储edge，还原时仍然可以还原

package LeetCode;

import java.util.*;

public class LC0329Path {
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public List<List<Integer>> longestIncreasingPath(int[][] matrix) {
        //cc
        if (matrix == null || matrix.length == 0 || matrix[0] == null || matrix[0].length == 0)
            throw new IllegalArgumentException();
        if (matrix.length == 1 && matrix[0].length == 1) return Arrays.asList(Arrays.asList(matrix[0][0]));

        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int max = -1;

        List<List<Integer>> res = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();//当只有一层mem，Graph仅对当前层有效，因为可能需要新加内容，其他点遍历，可能会重走。

        // memorize
        int[][] mem = new int[rowSize][colSize];//mem在单层内不能使用，不然放不进多个结果，但是可以总mem来prune，单层遍历单独存起来

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++){
                int[][] curMem = new int[rowSize][colSize];
                int ret = dfs(map, matrix, i, j, -1, mem, curMem);
                mergeMem(mem, curMem);
                List<Integer> path = new ArrayList<>();
                if (ret > max) {
                    max = ret;
                    res.clear(); //max更新，需要清空之前结果
                    dfsRstore(res, path, matrix, map, i * colSize + j);
                    } else if (ret == max) { //max相等，需要加入新结果
                    dfsRstore(res, path, matrix, map, i * colSize + j);
                }
            }
        }
        return res;
    }

    private int dfs(Map<Integer, List<Integer>> map, int[][] matrix, int i, int j, int prev,
                    int[][] mem, int[][] curMem) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        //bc fail to go deeper
        if (i < 0 || i >= rowSize || j < 0 || j >= colSize || matrix[i][j] <= prev)
            return -1; //BFS中是写前一层里

        //visited ? no need with increasing order

        // read or check
        if (mem[i][j] > 0) return mem[i][j]; // mem不能用，不然不能获得所有结果

        // branch
        int max = 0;
        for (int[] dir : DIRECTIONS) {
            int ii = i + dir[0];
            int jj = j + dir[1];
            int ret = dfs(map, matrix, ii, jj, matrix[i][j], mem, curMem);
            if (ret < 0) continue;
            if (ret > max) {
                max = ret;
                List<Integer> list = new ArrayList<>();
                list.add(colSize * ii + jj);
                map.put(colSize * i + j, list);
            } else if (ret == max) {
                List<Integer> list = map.get(colSize * i + j );
                list.add(colSize * ii + jj);
                map.put(colSize * i + j, list);
            }
        }
        curMem[i][j] = max + 1; // store result in memory
        return max+ 1; // 要返回当前坐标
    }

    private void dfsRstore(List<List<Integer>> res, List<Integer> path, int[][] matrix, Map<Integer, List<Integer>> map, int pos) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        int row = pos / colSize;
        int col = pos % colSize;
        int len = path.size();

        //bc
        if (!map.containsKey(pos)) {
            path.add(matrix[row][col]);
            res.add(new ArrayList<>(path));
            path.remove(len);
            return;
        }

        //标准DFS模板
        List<Integer> list = map.get(pos);
        for (Integer nextPos : list) {
            path.add(matrix[row][col]);
            dfsRstore(res, path, matrix, map, nextPos);
            path.remove(len);
        }
    }

    private void mergeMem(int[][] mem, int[][] curMem){
        int rowSize = mem.length;
        int colSize = mem[0].length;
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (curMem[i][j] > 0) mem[i][j] = curMem[i][j];
            }
        }
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][]{{7, 7, 9}, {3, 5, 8}, {1, 2, 8}};
        LC0329Path sol = new LC0329Path();
        List<List<Integer>> res = sol.longestIncreasingPath(matrix);
        System.out.println(res);
    }
}
