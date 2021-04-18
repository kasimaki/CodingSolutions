// Zigzag Iterator
//Given two 1d vectors, implement an iterator to return their elements alternately.
//
//What if you are given k 1d vectors? How well can your code be extended to such cases?

package LeetCode;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC0281 {
}

class ZigzagIterator implements Iterator<Integer> {
    private Queue<Iterator<Integer>> queue;

    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        this.queue = new LinkedList<>();
        if (v1.iterator().hasNext()) queue.add(v1.iterator());
        if (v2.iterator().hasNext()) queue.add(v2.iterator());
    }

    public Integer next() {
        Integer ret = null;
        if (!queue.isEmpty()){
            Iterator<Integer> iter = queue.poll();
            ret = iter.next();
            if (iter.hasNext()) {
                queue.offer(iter);
            }
        }
        return ret;
    }

    public boolean hasNext() {
        return !queue.isEmpty();
    }
}
