// O(2^k) to insert word and O(1) to search word
package LeetCode;


import java.util.HashSet;

public class LC0211HashMap {
}

class WordDictionaryHashMap {
    private HashSet<String> set;
    /**
     * Initialize your data structure here.
     */
    public WordDictionaryHashMap() {
        set = new HashSet<>();
    }

    public void addWord(String word) {
        if (word == null) return;
        if (search(word)) return;
        StringBuilder path = new StringBuilder();
        dfs(set, path, word, 0);
    }

    public boolean search(String word) {
        if (word == null) return false;
        return set.contains(word);
    }

    private void dfs(HashSet<String> set, StringBuilder path, String word, int idx) {
        //bc
        if (idx == word.length()) {
            set.add(path.toString());
            return;
        }

        // branch
        // add char
        path.append(word.charAt(idx));
        dfs(set, path, word, idx+1);
        path.setLength(idx);

        //add '.'
        path.append('.');
        dfs(set, path, word, idx+1);
        path.setLength(idx);
    }
}
