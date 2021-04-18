//Merge Intervals
//Given an array of intervals where intervals[i] = [starti, endi], merge all overlapping intervals,
// and return an array of the non-overlapping intervals that cover all the intervals in the input.

package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LC0056 {
    public int[][] merge(int[][] intervals) {
        //cc
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0)
            throw new IllegalArgumentException();
        if (intervals.length == 1) return intervals;

        List<int[]> res = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        int[] prev = new int[] {intervals[0][0], intervals[0][1]};
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            if (cur[0] <= prev[1]) { //overlap
                prev[1] = Math.max(prev[1], cur[1]);
            } else {
                res.add(new int[] {prev[0], prev[1]});
                prev = cur;
            }

        }
        res.add(new int[] {prev[0], prev[1]});
        return res.toArray(new int[0][0]);
    }
}
