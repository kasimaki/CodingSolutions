package LeetCode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class TrieNodeLC212 {
    public char ch;
    public TrieNodeLC212[] children;
    public boolean isWord;
    public String word;

    public TrieNodeLC212(char ch) {
        this.ch = ch;
        this.children = new TrieNodeLC212[26];
        this.isWord = false;
        this.word = null;
    }
}

class TrieLC212 {
    public TrieNodeLC212 root;

    public TrieLC212() {
        root = new TrieNodeLC212('\0');
    }

    public void addWord (String word) {
        TrieNodeLC212 cur = root;
        for (char ch : word.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                cur.children[ch - 'a'] = new TrieNodeLC212(ch);
            }
            cur = cur.children[ch - 'a'];
        }
        cur.isWord = true;
        cur.word = word;
    }

    public boolean searchWord(String word) {
        TrieNodeLC212 cur = root;
        for (char ch : word.toCharArray()) {
            TrieNodeLC212 next = cur.children[ch-'a'];
            if (next == null) return false;
            cur = next;
        }
        return cur.isWord;
    }

    public boolean searchByPrefix(String prefix) {
        TrieNodeLC212 cur = root;
        for (char ch : prefix.toCharArray()) {
            TrieNodeLC212 next = cur.children[ch-'a'];
            if (next == null) return false;
            cur = next;
        }
        return true;
    }
}

public class LC0212 {

    public List<String> findWords(char[][] board, String[] words) {
        //bc
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || words == null || words.length == 0) return new ArrayList<>();

        TrieLC212 trie = new TrieLC212();
        int maxLen = 0;
        for (String word : words) {
            trie.addWord(word);
            maxLen = Math.max(maxLen, word.length());
        }

        int rowSize = board.length;
        int colSize = board[0].length;

        boolean[][] visited = new boolean[rowSize][colSize];
        Set<String> set = new HashSet<>();

        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                dfs(set, board, trie.root, i, j, 0, maxLen, visited);
            }
        }
        return new ArrayList<>(set);
    }

    private void dfs(Set<String> set, char[][] board, TrieNodeLC212 cur, int i, int j, int level, int maxLen, boolean[][] visited) {
        int rowSize = board.length;
        int colSize = board[0].length;
        //bc
        if (maxLen <= level) return;
        if (i < 0 || i >= rowSize || j < 0 || j >= colSize || visited[i][j]) return;

        cur = cur.children[board[i][j] - 'a'];
        if (cur == null) return;

        if (cur.isWord) {
            set.add(cur.word);
        }

        visited[i][j] = true;

        dfs(set, board, cur, i-1, j, level +1, maxLen, visited);
        dfs(set, board, cur, i, j+1, level +1, maxLen, visited);
        dfs(set, board, cur, i+1, j, level +1, maxLen, visited);
        dfs(set, board, cur, i, j-1, level +1, maxLen, visited);

        visited[i][j] = false;
    }



    public static void main(String[] args) {
        char[][] board = new char[][] {
                {'o','a','a','n'},
                {'e','t','a','e'},
                {'i','h','k','r'},
                {'i','f','l','v'}
        };
        String[] words = new String[] {"oath","pea","eat","rain"};
        LC0212 sol = new LC0212();
        List<String> res = sol.findWords(board, words);
        System.out.println(res);
    }
}
