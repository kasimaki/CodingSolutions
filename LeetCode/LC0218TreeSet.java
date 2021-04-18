package LeetCode;



import java.util.*;

class EndPointLC218TreeSet implements Comparable<EndPointLC218TreeSet> {
    int val;
    boolean isStart;
    int height;
    int index;

    public EndPointLC218TreeSet(int val, boolean isStart, int height, int index) {
        this.val = val;
        this.isStart = isStart;
        this.height = height;
        this.index = index;
    }

    @Override
    public int compareTo(EndPointLC218TreeSet that) {
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

public class LC0218TreeSet {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        //cc
        if (buildings == null || buildings.length == 0 || buildings[0] == null || buildings[0].length == 0)
            throw new IllegalArgumentException();

        List<List<Integer>> res = new ArrayList<>();

        List<EndPointLC218TreeSet> eps = new ArrayList<>();
        List<EndPointLC218TreeSet> lefts = new ArrayList<>();

        for (int i  = 0; i < buildings.length; i++) {
            eps.add(new EndPointLC218TreeSet(buildings[i][0], true, buildings[i][2], i));
            eps.add(new EndPointLC218TreeSet(buildings[i][1], false, buildings[i][2], i));
            lefts.add(new EndPointLC218TreeSet(buildings[i][0], true, buildings[i][2], i));
        }

        Collections.sort(eps);

        Comparator<Integer> comp = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if (lefts.get(o1).height != lefts.get(o2).height) {
                    return lefts.get(o1).height - lefts.get(o2).height;
                } else {
                    return o1 - o2;
                }
            }
        };

        TreeSet<Integer> set = new TreeSet<>(comp);

        for (EndPointLC218TreeSet ep : eps) {
            if (ep.isStart) {
                if (set.isEmpty() || ep.height > lefts.get(set.last()).height) {
                    res.add(Arrays.asList(ep.val, ep.height));
                }
                set.add(ep.index);
            } else {
                set.remove(ep.index);
                if (set.isEmpty() || ep.height > lefts.get(set.last()).height) {
                    res.add(Arrays.asList(ep.val, set.isEmpty() ? 0 : lefts.get(set.last()).height));
                }
            }
        }
        return res;
    }
}
