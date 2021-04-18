//Flip Game II
//You are playing the following Flip Game with your friend: Given a string that contains
// only these two characters: + and -, you and your friend take turns to flip two consecutive
// "++" into "--". The game ends when a person can no longer make a move and therefore
// the other person will be the winner.
//
//Write a function to determine if the starting player can guarantee a win.

// false代表必败，两者博弈，努力逼迫对手必败

// DP? 不可以，状态转移非连续

package LeetCode;

import java.util.HashMap;
import java.util.Map;

public class LC0294 {
    public boolean canWinBit(String s) {
        //cc
        if (s == null || s.length() < 2) return false;

        // char array
        char[] board = s.toCharArray();
        Map<Integer, Boolean> map = new HashMap<>();
        return dfsBit(board, map);

    }

    private boolean dfsBit(char[] board, Map<Integer, Boolean> map) {
        //bc
        //if (isOver(board)) return false; // no further manipulation, lose
        Integer status = strToBit(board);
        //check if checked to prune
        Boolean ifWin = map.get(status);  //Google 推荐check方法
        if (ifWin != null) return ifWin;

        // visited?
        for (int i = 0; i < board.length -1; i++) {
            if (board[i] == '+' && board[i+1] == '+') {
                board[i] = '-';
                board[i+1] = '-';
                boolean ret = dfsBit(board, map);
                board[i] = '+';
                board[i+1] = '+';
                if (!ret) { //如果对手输了，那么自己赢
                    map.put(status, true);
                    return true; //break效果
                }
            }
        }
        map.put(status, false);
        return false;

        //visited back?

    }
    private int strToBit(char[] board) {
        int status = 0;
        for (int i = 0; i < board.length; i++) {
            if (board[i] == '+') status = status | (1 << i);
        }
        return status;
    }



    public boolean canWin(String s) {
        //cc
        if (s == null || s.length() < 2) return false;

        // char array
        char[] board = s.toCharArray();
        Map<String, Boolean> map = new HashMap<>();
        return dfs(board, map);

    }

    private boolean dfs(char[] board, Map<String, Boolean> map) {
        //bc
        //if (isOver(board)) return false; // no further manipulation, lose
        String str = String.valueOf(board);
        //check if checked to prune
        Boolean ifWin = map.get(str);  //Google 推荐check方法
        if (ifWin != null) return ifWin;

        // visited?
        for (int i = 0; i < board.length -1; i++) {
            if (board[i] == '+' && board[i+1] == '+') {
                board[i] = '-';
                board[i+1] = '-';
                boolean ret = dfs(board, map);
                board[i] = '+';
                board[i+1] = '+';
                if (!ret) { //如果对手输了，那么自己赢
                    map.put(str, true);
                    return true; //break效果
                }
            }
        }
        map.put(str, false);
        return false;

        //visited back?

    }

    private boolean isOver(char[] board) {
        for (int i = 0; i < board.length - 1; i++) {
            if (board[i] == '+' && board[i+1] == '+') return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String s = new String("+++++");
        LC0294 sol = new LC0294();
        boolean res = sol.canWinBit(s);
        System.out.println(res);
    }
}
