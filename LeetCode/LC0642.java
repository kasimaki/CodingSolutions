// Design Search Autocomplete System BOSS难度
//结构很重要，LRU Cache， HashMap get put random
//Design a search autocomplete system for a search engine. Users may input a sentence
// (at least one word and end with a special character '#').
// For each character they type except '#', you need to return the top 3 historical hot sentences
// that have prefix the same as the part of sentence already typed. Here are the specific rules:
//
//The hot degree for a sentence is defined as the number of times a user typed the exactly same sentence before.
//The returned top 3 hot sentences should be sorted by hot degree (The first is the hottest one).
// If several sentences have the same degree of hot, you need to use ASCII-code order (smaller one appears first).
//If less than 3 hot sentences exist, then just return as many as you can.
//When the input is a special character, it means the sentence ends, and in this case,
// you need to return an empty list.
//Your job is to implement the following functions:
//
//The constructor function:
//
//AutocompleteSystem(String[] sentences, int[] times): This is the constructor.
// The input is historical data. Sentences is a string array consists of previously typed sentences.
// Times is the corresponding times a sentence has been typed. Your system should record these historical data.
//
//Now, the user wants to input a new sentence. The following function will provide the next character
// the user types:
//
//List<String> input(char c): The input c is the next character typed by the user.
// The character will only be lower-case letters ('a' to 'z'), blank space (' ') or
// a special character ('#'). Also, the previously typed sentence should be recorded i
// n your system. The output will be the top 3 historical hot sentences that have prefix
// the same as the part of sentence already typed.


package LeetCode;

import java.util.*;
import java.util.stream.Collectors;

public class LC0642 {
}

class Pair {
    public String sentence;
    public int count;

    public Pair(String str, int count) {
        this.sentence = str;
        this.count = count;
    }
}

class TrieNode {
    public char ch;
    public boolean isWord;
    public TrieNode[] children;
    public HashMap<String, Integer> top3;

    public TrieNode(char ch) {
        this.ch = ch;
        this.isWord = false;
        children = new TrieNode[27]; //alphabet + space
        top3 = new HashMap<>(3); //限制大小，用array更好，但是更麻烦
    }
}

class AutocompleteSystem {
    private TrieNode root;
    private TrieNode curSearchNode;
    private HashMap<String, Integer> countBook;
    private StringBuilder path;

    public AutocompleteSystem(String[] sentences, int[] times) {
        this.root = new TrieNode('\0');
        this.curSearchNode = this.root;
        this.countBook = new HashMap<>();
        for (int i = 0; i < times.length; i++) {
            countBook.put(sentences[i], times[i]);
            insertSentence(sentences[i], times[i]); // TODO
        }
        this.path = new StringBuilder();
    }

    public List<String> input(char c) {
        if (c == '#') {
            curSearchNode = root;

            String newString = path.toString();
            path = new StringBuilder();

            Integer newStringCount = countBook.get(newString);

            if (newStringCount == null) countBook.put(newString, 1);
            else countBook.put(newString, newStringCount + 1);

            insertSentence(newString, countBook.get(newString));

            return new ArrayList<>();
        }
        // normal case
        path.append(c);

        if (curSearchNode == null) return new ArrayList<>();

        int nextCharIdx = (c <= 'z' && c >= 'a') ? (c - 'a') : 26;

        curSearchNode = curSearchNode.children[nextCharIdx];
        if (curSearchNode == null) return new ArrayList<>();

        return getTop3String(curSearchNode); //fast to get top3, might not be the actual top3
    }

    private void insertSentence(String sentence, int count) {
        //insert and update hashmap
        TrieNode cur = root;
        for (char c : sentence.toCharArray()) {
            int nextCharIdx = (c <= 'z' && c >= 'a') ? (c - 'a') : 26;
            if (cur.children[nextCharIdx] == null) {
                cur.children[nextCharIdx] = new TrieNode(c);
            }

            TrieNode next = cur.children[nextCharIdx];
            next.top3.put(sentence, count);
            List<Pair> top3Pairs = getCountMap(next.top3);
            udpateCountMap(next.top3, top3Pairs);

            cur = next;
        }
        cur.isWord = true;//the most updated cur should be set as visited (word ends here)
    }

    private List<String> getTop3String(TrieNode cur) {
        HashMap<String, Integer> top3 = cur.top3;
        return getCountMap(top3)
                .stream()
                .map(p -> p.sentence)
                .collect(Collectors.toList());

    }

    private void udpateCountMap(HashMap<String, Integer> top3, List<Pair> pairs) {
        top3.clear();;
        for (Pair p : pairs) {
            top3.put(p.sentence, p.count);
        }
    }

    private List<Pair> getCountMap(HashMap<String, Integer> top3) {
        PriorityQueue<Pair> maxHeap = new PriorityQueue<Pair>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                if (o1.count == o2.count) {
                    return o2.sentence.compareTo(o1.sentence);
                } else {
                    return o2.count - o1.count;
                }
            }
        });

        for (Map.Entry<String, Integer> entry : top3.entrySet()) { //hashmap.entrySet(); to iterate
            maxHeap.offer(new Pair(entry.getKey(), entry.getValue()));
        }

        List<Pair> ret = new ArrayList<>();
        for (int i = 0; i < 3 && !maxHeap.isEmpty(); i++) {
            ret.add(maxHeap.poll());
        }
        return ret;
    }

}

/**
 * Your AutocompleteSystem object will be instantiated and called as such:
 * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
 * List<String> param_1 = obj.input(c);
 */