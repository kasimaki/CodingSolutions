// Insert Delete GetRandom O(1)
// Implement the RandomizedSet class:
//
//bool insert(int val) Inserts an item val into the set if not present. Returns true if the item was not present, false otherwise.
//bool remove(int val) Removes an item val from the set if present. Returns true if the item was present, false otherwise.
//int getRandom() Returns a random element from the current set of elements (it's guaranteed that at least one element exists when this method is called). Each element must have the same probability of being returned.
//Follow up: Could you implement the functions of the class with each function works in average O(1) time?
//

package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

class RandomizedSet {
    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> list;
    private Random rm;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        this.map = new HashMap<>();
        this.list = new ArrayList();
        this.rm = new Random();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val)) return false;
        int size = list.size();
        map.put(val, size);
        list.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        Integer idx = map.get(val);
        if (idx == null) return false;
        swap(idx, list.size()-1);
        map.put(list.get(idx), idx); //两个都要remove
        list.remove(list.size()-1);
        map.remove(val);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int idxRD = rm.nextInt(list.size());
        return list.get(idxRD);
    }

    private void swap(int i, int j) {
        if (i == j) return;
        int temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}

public class LC0380 {
    public static void main(String[] args) {
        RandomizedSet rdset = new RandomizedSet();
        rdset.insert(0);
        rdset.insert(1);
        rdset.remove(0);
        rdset.insert(2);
        rdset.remove(1);
        System.out.println(rdset.getRandom());

    }
}
