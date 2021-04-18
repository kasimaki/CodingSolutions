// Implement Trie (Prefix Tree)
//Implement a trie with insert, search, and startsWith methods.

package LeetCode;

import JavaBasics.TrieNode;

public class LC0208 {
    public static void main(String[] args) {

    }
}


class Trie {
    public TrieNode root;// TODO

    /** Initialize your data structure here. */
    public Trie() {
        this.root = new TrieNode('\0');
    }

    /** Inserts a word into the trie. */
    public void insert(String word) {
        TrieNode cur = root;
        //cc
        if (word == null || word.length() == 0) return;
        for (char c : word.toCharArray()) {
            TrieNode next = cur.children[c - 'a'];
            if (next == null) {
                cur.children[c - 'a'] = new TrieNode(c);//非常重要
            }
            cur = cur.children[c - 'a'];//重要，next指向错误，不等价于cur.children[c - 'a']，此时next是null
        }
        cur.isWord = true;
    }

    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        TrieNode cur = root;
        //cc
        if (word == null || word.length() == 0) return false;
        for (char c : word.toCharArray()) {
            TrieNode next = cur.children[c - 'a'];
            if (next == null){
                return false;
            }
            cur = next;
        }
        return cur.isWord;  //最后确认是否标记为word
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNode cur = root;
        //cc
        if (prefix == null || prefix.length() == 0) return false;
        for (char c : prefix.toCharArray()) {
            TrieNode next = cur.children[c - 'a'];
            if (next == null) {
                return false;
            }
            cur = next;
        }
        return true;
    }
}

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */