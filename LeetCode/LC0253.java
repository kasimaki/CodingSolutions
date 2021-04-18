// Meeting Rooms II
//Given an array of meeting time intervals intervals where intervals[i] = [starti, endi],
// return the minimum number of conference rooms required.

package LeetCode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

class EndPointLC253 implements Comparable<EndPointLC253>{
    int val;
    boolean isStart;

    public EndPointLC253(int val, boolean isStart) {
        this.val = val;
        this.isStart = isStart;
    }
    @Override
    public int compareTo(EndPointLC253 that) {
        if (this. val != that.val) {
            return this.val - that.val;
        }
        else {
            return this.isStart ? 1 : -1; //时间相同，先算结束的
        }
    }
}

public class LC0253 {
    public int minMeetingRooms(int[][] intervals) {
        //cc
        if (intervals == null || intervals.length == 0 || intervals[0] == null || intervals[0].length == 0)
            return 0; // throw new IllegalArgumentException();
        if (intervals.length == 1) return 1;

        List<EndPointLC253> eps = new ArrayList<>();
        for (int[] intvl : intervals) {
            eps.add(new EndPointLC253(intvl[0], true));
            eps.add(new EndPointLC253(intvl[1], false));
        }

        Collections.sort(eps); //sort

        int max = 0;
        int curMeetings = 0;

        for (EndPointLC253 ep : eps) {
            if (ep.isStart) curMeetings++;
            else curMeetings--;
            max = Math.max(max, curMeetings);
        }
        return max;
    }
}
