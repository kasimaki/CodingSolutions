//Sliding Window Maximum
//You are given an array of integers nums, there is a sliding window of size k which is moving
// from the very left of the array to the very right. You can only see the k numbers in the window.
// Each time the sliding window moves right by one position.

package LeetCode;

import java.awt.*;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class LC0239 {
    public int[] maxSlidingWindow(int[] nums, int k) {
        //cc
        if (nums == null || k < 0) throw new IllegalArgumentException();
        if (nums.length < k) return new int[]{};

        Deque<Integer> deque = new ArrayDeque<>();
        int len = nums.length;
        int[] res = new int[len - k + 1];
        for (int i = 0; i < len; i++) {
            int cur = nums[i];
            if (!deque.isEmpty() && deque.peekFirst() + k <= i) {
                deque.pollFirst();
            }

            while (!deque.isEmpty() && nums[deque.peekLast()] < cur) {
                deque.pollLast();
            }
            deque.offerLast(i);

            if (i-k+1 >= 0) res[i-k+1] = nums[deque.peekFirst()];
        }
        return res;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {1,3,-1,-3,5,3,6,7};
        int k = 3;
        LC0239 sol = new LC0239();
        int[] res = sol.maxSlidingWindow(nums, k);
        System.out.println(Arrays.toString(res));
    }
}
