//Course Schedule II
//There are a total of n courses you have to take labelled from 0 to n - 1.
//
//Some courses may have prerequisites, for example, if prerequisites[i] = [ai, bi]
// this means you must take the course bi before the course ai.
//
//Given the total number of courses numCourses and a list of the prerequisite pairs,
// return the ordering of courses you should take to finish all courses.
//
//If there are many valid answers, return any of them. If it is impossible to finish
// all courses, return an empty array.


package LeetCode;

import java.util.*;
import JavaBasics.Status;

public class LC0210 {
    private int curIdx;
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        //cc

        HashMap<Integer, List<Integer>> graph = buildGraph(prerequisites);
        HashMap<Integer, Status> statusMap = new HashMap<>();
        int[] res = new int[numCourses];
        curIdx = numCourses-1;
        for (int i = 0; i < numCourses; i++) {
            if (!dfs(res, i, graph, statusMap)) return new int[]{};
        }

        return res;
    }

    private HashMap<Integer, List<Integer>> buildGraph(int[][] prereq) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : prereq) {
            List<Integer> nexts = graph.getOrDefault(pair[1], new ArrayList<>());
            nexts.add(pair[0]);
            graph.put(pair[1], nexts);
        }
        return graph;
    }


    private boolean dfs(int[] res, int cur, HashMap<Integer, List<Integer>> graph, HashMap<Integer, Status> statusMap) {
        Status status = statusMap.get(cur);
        if (status == Status.VISITED) return true;
        if (status == Status.VISITING) return false;

        statusMap.put(cur, Status.VISITING);
        List<Integer> nexts = graph.get(cur);
        if (nexts != null) {
            for (Integer next : nexts) {
                if (!dfs(res,next, graph, statusMap)) {
                    return false;
                }
            }
        }

        statusMap.put(cur, Status.VISITED);
        res[curIdx--] = cur;
        return true;
    }
}
