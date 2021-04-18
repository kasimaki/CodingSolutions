//Given an m x n board and a word, find if the word exists in the grid.
//
//        The word can be constructed from letters of sequentially adjacent cells, where "adjacent" cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.


//TC = O(v+e) = (m*n * min(m*n, 4^k))
//        分叉，搜索树结构有k层，每层4叉，所以最大4^k，如果matrix的size比较小，则取小值
//        SC = O(m*n + min(m*n, k)) = O(m*n + k)
//        DFS压栈弹栈会归零，并列关系，所以看for loop中的一次栈的最大深度，就足够了
//        压栈数量k层，且不能超过m*n，两者取最小值

// Worst case: 3*3全是a,找aaaaaaaab，会全部遍历
// 因此，主函数的base case可以先check board是否有word全部的element

package LeetCode;

public class LC0079 {
    public boolean exist(char[][] board, String word) {
        //cc
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || word == null)
            throw new IllegalArgumentException("Invalid Input");

        int rowSize = board.length;
        int colSize = board[0].length;
        boolean[][] visited = new boolean[rowSize][colSize];
        //StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (dfs(board, i, j, 0, word, visited)) return true; //初始状态要double check
            }
        }
        return false;
    }
    private boolean dfs(char[][] matrix, int i, int j, int index, String word, boolean[][] visited) {
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        // base case - success
        if (index == word.length()) {//TODO check condition
            return true;
        }
        if (i < 0 || i >= rowSize || j < 0 || j >= colSize
                || visited[i][j] || word.charAt(index) != matrix[i][j]) //别忘了查重
            return false;

        visited[i][j] = true;

        boolean res = dfs(matrix, i-1, j, index + 1, word, visited)
                || dfs(matrix, i, j+1, index + 1, word, visited)
                || dfs(matrix, i+1, j, index + 1, word, visited)
                || dfs(matrix, i, j-1, index + 1, word, visited);


        visited[i][j] = false;
        return res;
    }

    public static void main(String[] args) {
        char[][] board = new char[][] {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        };
        String word = new String("SEE");
        LC0079 sol = new LC0079();
        boolean res = sol.exist(board, word);
        System.out.print(res);
    }
}
