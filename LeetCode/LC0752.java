//You have a lock in front of you with 4 circular wheels. Each wheel has 10 slots: '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'. The wheels can rotate freely and wrap around: for example we can turn '9' to be '0', or '0' to be '9'. Each move consists of turning one wheel one slot.
//
//        The lock initially starts at '0000', a string representing the state of the 4 wheels.
//
//        You are given a list of deadends dead ends, meaning if the lock displays any of these codes, the wheels of the lock will stop turning and you will be unable to open it.
//
//        Given a target representing the value of the wheels that will unlock the lock, return the minimum total number of turns required to open the lock, or -1 if it is impossible.

package LeetCode;

import java.util.*;

public class LC0752 {
    public int openLock(String[] deadends, String target) {
        if (deadends == null || target == null) throw new IllegalArgumentException();
        String initial = new String("0000");
        Set<String> set = new HashSet<>();
        set.add(initial);
        Queue<String> queue = new LinkedList<>();
        queue.offer(initial);
        int minLen = 1;// TODO initial value

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                String cur = queue.poll();
                List<String> neighbors = convert(cur);
            }
            minLen++;
        }
        return -1;
    }
    private List<String> convert(String cur) {
        List<String> neighbors = new LinkedList<>();

        return neighbors;
    }
}
