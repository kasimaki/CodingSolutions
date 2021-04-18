package LeetCode;

import java.util.*;

public class LC0694 {
    private static final int[][] DIRECTIONS = new int[][] {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    public int numDistinctIslands(int[][] grid) {
        //cc
        if (grid == null || grid.length == 0 || grid[0] == null || grid[0].length == 0) {
            return 0;
        }

        int rowSize = grid.length;
        int colSize = grid[0].length;

        Queue<Integer> queue = new LinkedList<>();
        HashSet<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (grid[i][j] == 1) {
                    queue.offer(i * colSize + j);
                    List<Integer> curShape = new ArrayList<>();
                    curShape.add(i * colSize + j);//elementPos - (i * colSize + j)
                    int curMinI = i;
                    int curMinJ = j;
                    while (!queue.isEmpty()) {
                        int size = queue.size();
                        while (size-- > 0) {
                            int cur = queue.poll();
                            int curI = cur / colSize;
                            int curJ = cur % colSize;
                            for (int[] dir : DIRECTIONS) {
                                int ii = curI + dir[0];
                                int jj = curJ + dir[1];
                                if(ii >= 0 & ii < rowSize && jj >= 0 && jj < colSize && grid[ii][jj] == 1) {
                                    grid[ii][jj] = 2;
                                    queue.offer(ii * colSize + jj);
                                    curShape.add(ii * colSize + jj);
                                    curMinI = Math.min(curMinI, ii);
                                    curMinJ = Math.min(curMinJ, jj);
                                }
                            }
                        }
                    }
                    int upperLeft = curMinI * colSize + curMinJ;
                    for (int k = 0; k < curShape.size(); k++) {
                        curShape.set(k, curShape.get(k) - upperLeft);
                    }
                    //Collections.sort(curShape);
                    set.add(curShape);
                }
            }
        }
        return set.size();
    }

    public static void main(String[] args) {
        List<Integer> list1 = new LinkedList<>() {{
            add(1);
            add(2);
            add(3);
        }};
        List<Integer> list2 = new LinkedList<>() {{
            add(3);
            add(1);
            add(2);
        }};
        List<Integer> list3 = new LinkedList<>();
        list3.add(1);
        list3.add(2);
        list3.add(3);


        HashSet<List<Integer>> set = new HashSet<>();
        set.add(list1);
        set.add(list3);

        System.out.println(set);


    }
}
