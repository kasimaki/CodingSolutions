package LeetCode;


import java.util.*;

class EndPointLC218 implements Comparable<EndPointLC218> {
    int val;
    boolean isStart;
    int height;

    public EndPointLC218(int val, boolean isStart, int height) {
        this.val = val;
        this.isStart = isStart;
        this.height = height;
    }

    @Override
    public int compareTo(EndPointLC218 that) {
        if (this.val != that.val) {
            return this.val - that.val;
        } else {
            if (this.isStart && that.isStart) {
                return that.height - this.height;
            } else if (!this.isStart && !that.isStart) {
                return this.height - that.height;
            } else {
                return this.isStart ? -1 : 1;
            }
        }
    }
}

public class LC0218 {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        //cc
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0)
            throw new IllegalArgumentException();

        List<List<Integer>> res = new ArrayList<>();

        List<EndPointLC218> eps = new ArrayList<>();
        for (int[] b : buildings) {
            eps.add(new EndPointLC218(b[0], true, b[2]));
            eps.add(new EndPointLC218(b[1], false, b[2]));
        }

        Collections.sort(eps);

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((e1, e2) -> e2 - e1);
        //(e1, e2) -> Integer.compare(e2, e1)
        //Collections.reverseOrder()

        for (EndPointLC218 ep : eps) {
            if (ep.isStart) {
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    res.add(Arrays.asList(ep.val, ep.height));
                }
                maxHeap.offer(ep.height);
            } else {
                maxHeap.remove(ep.height);
                if (maxHeap.isEmpty() || ep.height > maxHeap.peek()) {
                    res.add(Arrays.asList(ep.val, maxHeap.isEmpty() ? 0 : maxHeap.peek()));
                }
            }
        }
        return res;
    }
}
