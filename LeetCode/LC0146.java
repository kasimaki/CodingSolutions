// LRU Cache
//Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.
//
//Implement the LRUCache class:
//
//LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
//int get(int key) Return the value of the key if the key exists, otherwise return -1.
//void put(int key, int value) Update the value of the key if the key exists. Otherwise,
// add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation,
// evict the least recently used key.
//
//

package LeetCode;

import java.util.HashMap;

class LRUCacheNode {
    public int key;
    public int val;
    public LRUCacheNode prev, next;

    public LRUCacheNode(int key, int val) {
        this.key = key;
        this.val = val;
    }

    public void setVal(int value) {
        val = value;
    }
}

class LRUCache {
    private HashMap<Integer, LRUCacheNode> map;
    private int capacity;
    private int size;
    LRUCacheNode head, tail;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.size = 0;
        this.head = null;
        this.tail = null;
    }

    public int get(int key) {
        LRUCacheNode node = map.get(key);
        if (node != null) {
            remove(node);
            addToEnd(node);
            return node.val;
        } else return -1;
    }

    public void put(int key, int value) {
        LRUCacheNode node = map.get(key);
        if (node != null) {
            remove(node);
            node.setVal(value);
        } else {
            node = new LRUCacheNode(key, value);
            if (size == capacity) {
                removeHead();
            } else {
                size++;
            }
        }
        addToEnd(node);
        map.put(key, node);
    }

    private void remove(LRUCacheNode node) {
        LRUCacheNode prevNode = node.prev;
        LRUCacheNode nextNode = node.next;

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

    private void removeHead() {
        map.remove(head.key);
        remove(head);
    }

    private void addToEnd(LRUCacheNode node) {
        if (tail == null) {
            head = node;
        } else {
            tail.next = node;
            node.prev = tail;
        }
        tail = node;
    }
}

public class LC0146 {
    public static void main(String[] args) {
        int capacity = 3;
        LRUCache lru = new LRUCache(capacity);
        lru.put(1, 1);
        lru.put(2, 2);
        lru.put(3, 3);
        lru.put(4, 4);
        lru.get(4);
        lru.get(3);
        lru.get(2);
        lru.get(1);
        lru.put(5, 5);
        lru.get(1);
        lru.get(2);
        lru.get(2);
        lru.get(4);
        lru.get(4);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
