// Longest Substring with At Most K Distinct Characters
//Given a string s and an integer k, return the length of the longest substring of s
// that contains at most k distinct characters.

package LeetCode;

import java.util.HashMap;

class MyNode {
    public MyNode prev;
    public MyNode next;
    public char ch;
    public int index;

    public MyNode(char ch, int index) {
        this.prev = null;
        this.next = null;
        this.ch = ch;
        this.index = index;
    }
}

class MyData {

    private HashMap<Character, MyNode> map;
    private MyNode head, tail;
    public int size, k, start;

    public MyData(int k) {
        this.map = new HashMap<>();
        this.head = null;
        this.tail = null;
        this.size = 0;
        this.k = k;
    }

    public void addOrUpdateNode(char ch, int idx) {
        MyNode node = map.get(ch);
        if (node != null) {
            remove(node);
        } else if (map.size() == k) {
            start = removeHead() + 1;
        }

        MyNode newNode = new MyNode(ch, idx);
        map.put(ch, newNode);
        addEnd(newNode);

    }

    private void remove(MyNode node) {
        MyNode prevNode = node.prev;
        MyNode nextNode = node.next;
        if (prevNode == null && nextNode == null) {
            head = null;
            tail = null;
        }
        if (prevNode != null && nextNode == null) {
            prevNode.next = null;
            tail = prevNode;
        }
        if (prevNode == null && nextNode != null) {
            nextNode.prev = null;
            head = nextNode;
        }

        if (prevNode != null && nextNode != null) {
            nextNode.prev = prevNode;
            prevNode.next = nextNode;
        }

        node.prev = null;
        node.next = null;
    }

    private int removeHead() {
        int newStart = head.index;
        map.remove(head.ch);
        remove(head);
        return newStart;
    }

    private void addEnd(MyNode node){
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
    }

    public int getSize(int end){
        return end - start + 1;
    }
}

public class LC0340 {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // cc
        if (s == null) throw new IllegalArgumentException();
        if (s.length() <= k) return s.length();
        if (k == 0) return 0;

        MyData pool = new MyData(k);
        int max = 0;

        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            pool.addOrUpdateNode(ch, i);

            max = Math.max(max, pool.getSize(i));
        }
        return max;
    }

    public static void main(String[] args) {
        String s = new String("aba");
        int k = 1;
        LC0340 sol = new LC0340();
        int res = sol.lengthOfLongestSubstringKDistinct(s, k);
        System.out.println(res);
    }
}
