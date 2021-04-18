package LeetCode;

public class LC0154 {
    public int findMin(int[] nums){
        // cc
        if (nums == null || nums.length == 0) return -1;
        if (nums.length == 1) return nums[0];
        if (nums.length == 2) return Math.min(nums[0], nums[1]);

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left + 1 < right) {
            mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) left = mid; // 必然在右半边
            else if (nums[mid] < nums[right]) right = mid; //必然在左半边
            else right--;
        }
        return Math.min(nums[left], nums[right]);
    }
}
//不能忽略全局单调递增
//主要看 left or right与mid的关系，< > ==，相等时需要移动left or right一位
//先check左边，使得要考虑是否为全局升序，而要再check右边
//如果只做右边，则可以简便很多，因为不用剔除升序情况
