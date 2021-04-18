//Number of Islands II
//A 2d grid map of m rows and n columns is initially filled with water. We may perform an addLand
// operation which turns the water at position (row, col) into a land. Given a list of positions to
// operate, count the number of islands after each addLand operation. An island is surrounded by water
// and is formed by connecting adjacent lands horizontally or vertically. You may assume all four edges
// of the grid are all surrounded by water.


package LeetCode;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedTransferQueue;

class UFNodeLC305 {
    public String id;
    public UFNodeLC305 parent;
    public int size;

    public UFNodeLC305() {
        this.id = null;
        this.parent = this;
        this.size = 0;
    }
}

class UnionFindLC305 {
    private int[] parents;
    private int[] size;
    public int numOfIslands;

    public UnionFindLC305(int m, int n) {
        this.parents = new int[m * n];
        this.size = new int[m * n];
        this.numOfIslands = 0;
    }

    public int getRoot(int p) {
        int cur = p;
        while (parents[cur] != cur) {
            parents[cur] = parents[parents[cur]];
            cur = parents[cur];
        }
        parents[p] = cur;
        return cur;
    }

    public boolean find(int p, int q) {
        return getRoot(p) == getRoot(q);
    }

    public void union(int p, int q) {
        int rootP = getRoot(p);
        int rootQ = getRoot(q);
        if (size[rootP] < size[rootQ]) {
            parents[rootP] = parents[rootQ];
            size[rootQ] += size[rootP];
        } else {
            parents[rootQ] = parents[rootP];
            size[rootP] += size[rootQ];
        }
        numOfIslands--;
    }

    public void addLand(int p) {
        parents[p] = p;
        size[p] = 1;
        numOfIslands++;
    }

    public boolean isIsland(int p) {
        return size[p] > 0;
    }
}

public class LC0305 {
    private static final int[][] DIRECTIONS = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        //cc
        if (m <= 0 || n <= 0 || positions == null ||
                positions.length == 0 || positions[0] == null || positions[0].length == 0) return new ArrayList<>();

        UnionFindLC305 uf = new UnionFindLC305(m, n);
        List<Integer> res = new ArrayList<>();

        for (int[] pos : positions) {
            int p = pos[0] * n + pos[1];
            if (!uf.isIsland(p)) {
                uf.addLand(p);

                for (int[] dir : DIRECTIONS) {
                    int ii = pos[0] + dir[0];
                    int jj = pos[1] + dir[1];
                    if (ii >= 0 && ii < m && jj >= 0 && jj < n && uf.isIsland(ii * n + jj) && !uf.find(p, ii * n + jj)) {
                        uf.union(p, ii * n + jj);
                    }
                }
            }
            res.add(uf.numOfIslands);
        }
        return res;
    }

    public static void main(String[] args) {
        int[][] pos = new int[][]{{0,0},{0,1},{1,2},{2,1},{1,0},{0,0},{2,2},{1,2},{1,1},{0,1}};
        int m = 3;
        int n = 3;
        LC0305 sol = new LC0305();
        List<Integer> res = sol.numIslands2(m, n, pos);
        System.out.println(res);
    }
}
