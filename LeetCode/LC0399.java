//Evaluation Division → Google汇率 / 减法
//You are given an array of variable pairs equations and an array of real numbers values,
// where equations[i] = [Ai, Bi] and values[i] represent the equation Ai / Bi = values[i].
// Each Ai or Bi is a string that represents a single variable.
//
//You are also given some queries, where queries[j] = [Cj, Dj] represents the jth query
// where you must find the answer for Cj / Dj = ?.
//
//Return the answers to all queries. If a single answer cannot be determined, return -1.0.
//
//Note: The input is always valid. You may assume that evaluating the queries will not
// result in division by zero and that there is no contradiction.


package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class UFNodeLC399 {
    public String str;
    public UFNodeLC399 parent;
    public double val;
    public int size;

    public UFNodeLC399(String str) {
        this.str = str;
        this.parent = this;
        this.val = 1.0;
        this.size = 1;
    }
}

class UnionFindLC399 {
    private HashMap<String, UFNodeLC399> map;
    public UnionFindLC399(){
        this.map = new HashMap<>();
    }

    public UFNodeLC399 getRoot(UFNodeLC399 p) {
        UFNodeLC399 cur = p;
        double accumVal = 1.0;
        while (cur != cur.parent) {
            cur.val *= cur.parent.val;//每2个点累乘
            cur.parent= cur.parent.parent;
            accumVal *= cur.val; //每次把累乘后的值再累乘，方便p指向root时，能把全图的累乘结果获取
            cur = cur.parent;
        }
        p.parent = cur;
        p.val *= accumVal;
        return cur;
    }

    public boolean find(UFNodeLC399 p, UFNodeLC399 q) {
        return getRoot(p) == getRoot(q);
    }

    public void union(UFNodeLC399 p, UFNodeLC399 q, double d) {
        UFNodeLC399 rootP = getRoot(p);
        UFNodeLC399 rootQ = getRoot(q);

        if (rootP.size < rootQ.size) {
            rootP.parent = rootQ;
            rootQ.size += rootP.size;
            rootP.val = q.val / (p.val * d);
            //rootP.val = rootQ.true / rootP.true = q.val * q.true / (p.val * p.true) = q.val / (p.val * d)
        } else {
            rootQ.parent = rootP;
            rootP.size += rootQ.size;
            rootQ.val = p.val * d / q.val;
        }
    }

    public UFNodeLC399 getOrDefaultAndPut(String str) {
        UFNodeLC399 node = map.get(str);
        if (node == null) {
            node = new UFNodeLC399(str);
            map.put(str, node);
        }
        return node;
    }

    public UFNodeLC399 get(String str) {
        return map.get(str);
    }

    public double divide(UFNodeLC399 p, UFNodeLC399 q) {
        getRoot(p);
        getRoot(q);
        return q.val / p.val;
    }
}

public class LC0399 {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        //cc
        if (equations == null || queries == null) throw new IllegalArgumentException();

        UnionFindLC399 uf = new UnionFindLC399();
        double[] res = new double[queries.size()];

        for (int i = 0; i < equations.size(); i++) {
            UFNodeLC399 node1 = uf.getOrDefaultAndPut(equations.get(i).get(0));
            UFNodeLC399 node2 = uf.getOrDefaultAndPut(equations.get(i).get(1));

            if (!uf.find(node1, node2)) {
                uf.union(node1, node2, values[i]);
            }
        }

        for (int i = 0; i < queries.size(); i++) {
            UFNodeLC399 node1 = uf.get(queries.get(i).get(0));
            UFNodeLC399 node2 = uf.get(queries.get(i).get(1));

            if (node1 == null || node2 == null || !uf.find(node1, node2)) {
                res[i] = - 1.0; // throw new RuntimeException();
            } else {
                res[i] = uf.divide(node1, node2);
            }
        }
        return res;
    }
}
