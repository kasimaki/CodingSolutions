package LeetCode;

import java.util.*;

public class LC0126A {
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0
                || beginWord.length() != endWord.length()) throw new IllegalArgumentException("Please check input");
        Set<String> set = new HashSet<>(wordList);
        if (set.contains(beginWord)) set.remove(beginWord);

        List<List<String>> result = new LinkedList<>();

        Map<String, List<List<String>>> map = new HashMap<>();

//        map.put();

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);
        int len = 1;
        int minLen = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {

            }

            len++;
        }

        return result;
    }

    public static void main(String args[]) {
        String beginWord = new String("hit");
        String endWord = new String("cog");
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog");

        LC0126 sol = new LC0126();
        List<List<String>> res = sol.findLadders(beginWord, endWord, wordList);
        System.out.print(res);
    }
}
