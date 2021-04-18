package LeetCode;

import java.util.ArrayList;
import java.util.List;

public class LC0051 {
    public List<List<String>> solveNQueens(int n) {
        if (n <= 0) return new ArrayList<>();

        List<Integer> path = new ArrayList<>();
        List<List<String>> res = new ArrayList<>();

        searchAllCombinationsDFS(res, path, n, 0);

        return res;
    }

    private void searchAllCombinationsDFS(List<List<String>> res, List<Integer> path, int n, int level) {
        if (level == n) {
            convertPathToRes(res, path, n);
            return;
        }

        int len = path.size();

        for (int i = 0; i < n; i++) {
            if (isValid(path, level, i)) {
                path.add(i);
                searchAllCombinationsDFS(res, path, n, level+1);
                path.remove(len);
            }
        }
    }

    private boolean isValid(List<Integer> path, int level, int cur) {
        for (int i = 0; i < path.size(); i++) {
            int idx = path.get(i);
            if (idx == cur) return false;
            if (Math.abs(cur - idx) == Math.abs(level - i)) return false;
        }
        return true;
    }

    private void convertPathToRes(List<List<String>> res, List<Integer> path, int n) {
        List<String> oneComb = new ArrayList<>();
        for (int idx : path) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (idx == j) sb.append('Q');
                else sb.append('.');
            }
            oneComb.add(sb.toString());
        }
        res.add(oneComb);

    }


}
