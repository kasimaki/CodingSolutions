package LeetCode;

public class LC0153 {
    public int findMin (int[] nums){
        // cc
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[0] <= nums [mid] && nums[mid] > nums[nums.length - 1]) left = mid; //去掉左边单调区间
            else right = mid; // 当mid小于最左，或者右边也大于等于mid，右半边必然单调
        }
        return Math.min(nums[left], nums[right]); //通常是right，但是如果全局单调，则是左
    }
}

// 也可以recursion，返回最小值，当nums[left] < nums[right]时，返回nums[left]