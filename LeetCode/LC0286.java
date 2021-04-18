package LeetCode;

import java.util.LinkedList;
import java.util.Queue;

public class LC0286 {
    private static final int[][] DIRECTIONS = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}}; // Java常量用static final CAPTIALIZEDNAME
    // 北起顺时针
    public void wallsAndGates(int[][] rooms) {
        // void 需要in-place操作，如果int[][]就是输出新结果，如果自己选，尽量选择不修改原数据
        // cc 先写
        if (rooms == null || rooms.length == 0
                || rooms[0] == null || rooms[0].length == 0){
            throw new IllegalArgumentException();// unchecked exception，所以不用加try catch
        }
        int row = rooms.length, col = rooms[0].length;
        // 模板
        Queue<Integer> queue = new LinkedList<>(); // 用单个index来表示2D matrix位置
        // i = int / col   j = int % col 弊端: 拉直成一维，可能超出Integer表示范围 → Long？
        addAllZeros(rooms, queue); // 跟面试官说等会儿再实现，写得好可能不用写
        int minLen = 1; // 初始不一定为0，可以举例来看什么值合适
        // BFS
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) { // wall
                int cur = queue.poll();
                int i = cur / col; // restore row index
                int j = cur % col; // restore col index

                // 一个推荐的写法，cur到neighbors的index写法
                for (int[] dir : DIRECTIONS) {  //别忘了单个元素名dir
                    int ii = i + dir[0]; // 等效[i-1, j] [i, j+1] [i+1, j] [i, j-1]
                    int jj = j + dir[1]; // 加减需要在matrix范围内
                    if (ii >= 0 && ii < row && jj >= 0 && jj < col
                            && rooms[ii][jj] == Integer.MAX_VALUE){ //边界不能等于Size
                        //查重，初始状态INF可以用来查重，如果不是INF，就遍历过了
                        queue.offer(ii * col + jj);
                        rooms[ii][jj] = minLen; // +1在后面++方便
                    }
                }

            }
            minLen ++;
        }
    }
    private void addAllZeros(int[][] rooms, Queue queue){
        int rowSize = rooms.length;
        int colSize = rooms[0].length;

        for (int i = 0; i < rowSize; i++){
            for (int j = 0; j < colSize; j++){
                if (rooms[i][j] == 0) queue.offer(i * colSize + j);
            }
        }
    }
}
