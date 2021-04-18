// Design Add and Search Words Data Structure
//Design a data structure that supports adding new words and finding if a string matches any previously added string.
//
//Implement the WordDictionary class:
//
//WordDictionary() Initializes the object.
//void addWord(word) Adds word to the data structure, it can be matched later.
//bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise.
// word may contain dots '.' where dots can be matched with any letter.

// O(k) to insert word and O(kn) to search word

package LeetCode;

import JavaBasics.TrieNode;

public class LC0211 {
}

class WordDictionary {
    public TrieNode root;

    /**
     * Initialize your data structure here.
     */
    public WordDictionary() {
        this.root = new TrieNode('\0');
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (char c : word.toCharArray()) {
            TrieNode next = cur.children[c - 'a'];
            if (next == null) {
                cur.children[c - 'a'] = new TrieNode(c);
            }
            cur = cur.children[c - 'a']; //不能用next，它指向null，无法把新建的node地址传递给cur
        }
        cur.isWord = true; // isWord
    }

    public boolean search(String word) {
        //TrieNode cur = root;
        return dfs(root, word, 0);
    }

    private boolean dfs(TrieNode cur, String word, int idx) {
        // bc
        if (cur == null) return false;
        if (idx == word.length()) return cur.isWord; //isWord IS THE MOST IMPROTANT

        char c = word.charAt(idx);
        if (c != '.') {
            return dfs(cur.children[c - 'a'], word, idx + 1);
        } else {
            for (int i = 0; i < cur.children.length; i++) {
                if (dfs(cur.children[i], word, idx + 1))
                    return true;
            }
            return false;
        }

    }
}