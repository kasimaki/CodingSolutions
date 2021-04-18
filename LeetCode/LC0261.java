// Graph Valid Tree
//Given n nodes labeled from 0 to n-1 and a list of undirected edges (each edge is a pair of nodes),
// write a function to check whether these edges make up a valid tree.

package LeetCode;

class UnionFindLC261 {
    private int[] parents;
    private int[] size;
    public int numOfUnions;

    public UnionFindLC261(int n) {
        this.parents = new int[n];
        this.size = new int[n];
        numOfUnions = 0;
    }

    private int getRoot(int p) {
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
            parents[rootP] = rootQ;
            size[rootQ] += size[rootP];
        } else {
            parents[rootQ] = rootP;
            size[rootP] += size[rootQ];
        }
        numOfUnions--;
    }

    public void addNode(int p) {
        parents[p] = p;
        size[p] = 1;
        numOfUnions++;
    }

    public boolean isAdded(int p) {
        return size[p] > 0;
    }
}

public class LC0261 {
    public boolean validTree(int n, int[][] edges) {
        //cc
        if (n <= 0 || edges == null || n <= edges.length)
            return false;
        if (n == 1 && edges.length == 0) return true;
        if (edges.length > 0 && (edges[0] == null || edges[0].length == 0)) return false;

        UnionFindLC261 uf = new UnionFindLC261(n);
        for (int i = 0; i < n; i++) {
            uf.addNode(i);
        }

        for (int[] edge : edges) {
            if (!uf.find(edge[0], edge[1])) {
                uf.union(edge[0], edge[1]);
            }
        }

        return uf.numOfUnions == 1;
    }
}
