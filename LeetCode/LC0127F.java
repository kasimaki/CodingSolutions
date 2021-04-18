//A transformation sequence from word beginWord to word endWord using a dictionary wordList is a sequence of words such that:
//
//        The first word in the sequence is beginWord.
//        The last word in the sequence is endWord.
//        Only one letter is different between each adjacent pair of words in the sequence.
//        Every word in the sequence is in wordList.
//        Given two words, beginWord and endWord, and a dictionary wordList,
//        return the number of words in the shortest transformation sequence from beginWord to endWord,
//        or 0 if no such sequence exists.

package LeetCode;

import java.util.*;

// L127 头尾相接
public class LC0127F {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (beginWord == null || endWord == null || wordList == null || wordList.size() == 0
            || beginWord.length() != endWord.length()) throw new IllegalArgumentException("Input error");

        Map<Character, List<String>> map = new HashMap<>();
        boolean endExist = false;
        for (String word : wordList) {
            if (word.equals(beginWord)) continue;
            if (word.equals(endWord)) endExist = true;
            List<String> list;
            if (!map.containsKey(word.charAt(0))) {
                list = new LinkedList<>();
            } else {
                list = map.get(word.charAt(0));
            }
            list.add(word);
            map.put(word.charAt(0), list);
        }

        if (!endExist) return 0;

        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        int minLen = 1; // TODO

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                if (!map.containsKey(cur.charAt(cur.length() - 1))) continue; // check if map contains to avoid null
                List<String> neighbors = map.get(cur.charAt(cur.length() - 1)); // str.length()
                map.remove(cur.charAt(cur.length() - 1));
                for (String neighbor : neighbors) {
                    if (neighbor.equals(endWord)) return minLen + 1; // check
                    queue.offer(neighbor);
                }
            }
            minLen++;
        }

        return 0; // not found
    }
    public static void main(String args[]) {
        String beginWord = new String("hit");
        String endWord = new String("cog");
        List<String> wordList = Arrays.asList("hot","dot","dog","lot","log","cog","tag","gus","sec","gsc");

        LC0127F sol = new LC0127F();
        int res = sol.ladderLength(beginWord, endWord, wordList);
        System.out.print(res);
    }
}
