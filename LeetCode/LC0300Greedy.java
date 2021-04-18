package LeetCode;

import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LC0300Greedy {
    public int lengthOfLIS(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) return 0;
        if (nums.length == 1) return 1;

        int len = nums.length;

        ArrayList<Integer> minAtLength = new ArrayList<>();
        minAtLength.add(nums[0]);

        for (int i = 1; i < len; i ++){
            int cur = nums[i];
            int idx = findSmallestLargerAndEqual(minAtLength, cur);
            if (idx < minAtLength.size()) minAtLength.set(idx, cur);
            else minAtLength.add(cur);
        }

        return minAtLength.size();
    }

    private int findSmallestLargerAndEqual(ArrayList<Integer> arr, int target) {
        if (arr.size() == 1) return (arr.get(0) < target ? 1 : 0);

        int left = 0;
        int right = arr.size() - 1;

        while (left +1 < right)  {
            int mid = left + (right - left) / 2;
            if (target < arr.get(mid)) right = mid;  // < guarantee increasing
            else left = mid;
        }
        if (arr.get(right) < target) return right+1;
        if (arr.get(left) >= target) return left;
        return right;
    }

    public static void main(String[] args) {
        int[] nums = new int[] {0, 1, 0 , 3, 2, 3};
        LC0300Greedy sol = new LC0300Greedy();
        int res = sol.lengthOfLIS(nums);
        System.out.println(res);
    }
}
