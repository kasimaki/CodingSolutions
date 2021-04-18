//Alien Dictionary
//There is a new alien language that uses the English alphabet. However,
// the order among letters are unknown to you.
//
//You are given a list of strings words from the dictionary, where words
// are sorted lexicographically by the rules of this new language.
//
//Derive the order of letters in this language, and return it. If the given
// input is invalid, return "". If there are multiple valid solutions,
// return any of them.


package LeetCode;

import java.util.ArrayList;

import JavaBasics.Status;

import java.util.HashMap;
import java.util.List;

public class LC0269 {
    public String alienOrder(String[] words) {
        //cc
        if (words == null || words.length == 0) return new String("");

        HashMap<Character, List<Character>> graph = buildGraph(words);

        HashMap<Character, Status> statusMap = new HashMap<>();
        StringBuilder res = new StringBuilder();
        for (Character c : graph.keySet()) {
            if (!dfs(res, c, graph, statusMap)) return new String("");
        }

        return res.reverse().toString();
    }

    private HashMap<Character, List<Character>> buildGraph(String[] words) {
        HashMap<Character, List<Character>> graph = new HashMap<>();
        int idxCur = 0;
        int idxNext = 1;
        while (idxNext < words.length) {
            char[] cur = words[idxCur].toCharArray();
            char[] next = words[idxNext].toCharArray();
            if (cur.length > next.length && words[idxCur].startsWith(words[idxNext])) return new HashMap<>();

            if (cur.length > 0 && next.length == 0) {
                idxCur++;
                idxNext++;
                continue;
            }


            boolean flag = false;

            for (int i = 0; i < next.length; i++) {
                if (!flag) {
                    if (i >= cur.length || cur[i] == next[i]) {
                        if (!graph.containsKey(next[i])) graph.put(next[i], new ArrayList<>());
                    } else {
                        List<Character> nextChars = graph.getOrDefault(cur[i], new ArrayList<>());
                        nextChars.add(next[i]);
                        graph.put(cur[i], nextChars);
                        flag = true;
                    }

                } else {
                    if (i < cur.length && !graph.containsKey(cur[i])) graph.put(cur[i], new ArrayList<>());
                    if (!graph.containsKey(next[i])) graph.put(next[i], new ArrayList<>());
                }
            }


            idxCur++;
            idxNext++;
        }
        return graph;
    }

    private boolean dfs(StringBuilder res, Character c, HashMap<Character,
            List<Character>> graph, HashMap<Character, Status> statusMap) {
        Status status = statusMap.get(c);
        if (status == Status.VISITED) return true;
        if (status == Status.VISITING) return false;

        statusMap.put(c, Status.VISITING);

        List<Character> nexts = graph.get(c);

        if (nexts != null) {
            for (Character next : nexts) {
                if (!dfs(res, next, graph, statusMap)) return false;
            }
        }

        statusMap.put(c, Status.VISITED);

        res.append(c);

        return true;
    }

    public static void main(String[] args) {
        String[] words = new String[]{"ac","ab","zc","zb"};
        LC0269 sol = new LC0269();
        String res = sol.alienOrder(words);
        System.out.println(res);
    }
}
