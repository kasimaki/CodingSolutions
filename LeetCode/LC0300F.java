package LeetCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LC0300F {
    // follow up 1: non decreasing
    // should put value at smallest larger

    private int findSmallestLarger(ArrayList<Integer> arr, int target) {
        if (arr.size() == 1) return (arr.get(0) < target ? 1 : 0);

        int left = 0;
        int right = arr.size() - 1;

        while (left <= right)  {
            int mid = left + (right - left) / 2;
            if (target < arr.get(mid)) right = mid; // duplicate can exist in continuous positions of buffer
            else left = mid;
        }
        return left;
    }

    // follow up 2: return any path
    // store <to_node, from_node> to HashMap to build graph
    // traverse back to recover one path

    public List<Integer> onePathOfLIS(int[] nums) {
        //cc
        if (nums == null || nums.length == 0) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> graph = new HashMap<>();
        List<Integer> buffer = new ArrayList<>();

        for (int i = 0; i < nums.length; i++) {
            int idx = findSmallestLargerAndEqual(nums, buffer,nums[i]);
            if (idx == buffer.size()) {
                buffer.add(i);
            } else {
                buffer.set(idx, i);
            }
            graph.put(i,
                    (idx == 0) ? null : buffer.get(idx-1));
        }

        Integer curPos = buffer.get(buffer.size() - 1);
        while (curPos != null) {
            res.add(0, nums[curPos]);
            curPos = graph.get(curPos);
        }


        return res;

    }

    private int findSmallestLargerAndEqual(int[] nums, List<Integer> buffer, int target) {
        int left = 0;
        int right = buffer.size() -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[buffer.get(mid)] < target) { // <= increasing
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public static void main (String[] args) {
        int[] nums = new int[] {10,9,2,5,3,7,101,18};
        LC0300F sol = new LC0300F();
        List<Integer> res = sol.onePathOfLIS(nums);
        System.out.println(res);
    }
}
