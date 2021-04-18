//Course Schedule
//There are a total of numCourses courses you have to take, labeled from 0 to numCourses - 1.
// You are given an array prerequisites where prerequisites[i] = [ai, bi] indicates that
// you must take course bi first if you want to take course ai.
//
//For example, the pair [0, 1], indicates that to take course 0 you have to first take course 1.
//Return true if you can finish all courses. Otherwise, return false.
//

package LeetCode;

import java.util.*;
import JavaBasics.Status;
public class LC0207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        //cc
        if (numCourses <= 0 || prerequisites == null) throw new IllegalArgumentException();
        if (prerequisites.length == 0) return true;

        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : prerequisites) {
            List<Integer> neighbors = graph.getOrDefault(pair[1], new ArrayList<>());
            neighbors.add(pair[0]);
            graph.put(pair[1], neighbors);
        }

        HashMap<Integer, Status> visited = new HashMap<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfs(i, graph, visited)) return false;
        }
        return true;
    }

    private boolean dfs(int courseNum, HashMap<Integer, List<Integer>> graph, HashMap<Integer, Status> visited) {
        // bc
        Status status = visited.get(courseNum);

        if (status == Status.VISITING) return false;
        if (status == Status.VISITED) return true;


        visited.put(courseNum, Status.VISITING);

        List<Integer> neighbors = graph.get(courseNum);
        if (neighbors != null) {
            for (int next : neighbors) {
                if (!dfs(next, graph, visited)) return false;
            }

        }
        visited.put(courseNum, Status.VISITED);
        return true;
    }

    public boolean canFinishNonPruning(int numCourses, int[][] prerequisites) {
        //cc
        if (numCourses <= 0 || prerequisites == null) throw new IllegalArgumentException();
        if (prerequisites.length == 0) return true;

        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] pair : prerequisites) {
            List<Integer> neighbors = graph.getOrDefault(pair[0], new ArrayList<>());
            neighbors.add(pair[1]);
            graph.put(pair[0], neighbors);
        }

        HashSet<Integer> visited = new HashSet<>();

        for (int i = 0; i < numCourses; i++) {
            if (!dfsNonPruning(i, graph, visited)) return false;
        }
        return true;
    }

    private boolean dfsNonPruning(int courseNum, HashMap<Integer, List<Integer>> graph, HashSet<Integer> visited) {
        // bc
        if (visited.contains(courseNum)) return false;

        // set visited
        visited.add(courseNum);

        List<Integer> neighbors = graph.get(courseNum);
        if (neighbors != null) {
            for (int next : neighbors) {
                if (!dfsNonPruning(next, graph, visited)) return false;
            }

        }
        visited.remove(courseNum);
        return true;
    }

    public static void main(String[] args) {
        int[][] prerequisites = new int[][]{{1,0}}; //{{1,4},{2,4},{3,1},{3,2}};
        int numCourse = 5;
        LC0207 sol = new LC0207();
        boolean res = sol.canFinish(numCourse, prerequisites);
        System.out.println(res);
    }
}
