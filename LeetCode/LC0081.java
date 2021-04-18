package LeetCode;

public class LC0081 {
    public boolean search(int[] nums, int target){
        // cc target might throw NullPointerException
        if (nums == null || nums.length == 0) return false;

        int left = 0, right = nums.length - 1;
        int mid = 0;
        while (left <= right){ //越过 +1 -1
            mid = left + (right - left) / 2;
            if (nums[mid] == target) return true;
            if (nums[left] == nums[mid]){
                ++left; //当不确定在单调区间内或单调区间外，e.g. 1 2 1 1 1 t=2，移动left or right
                continue; //一定跳过当前loop
            }
            if (nums[left] < nums[mid]){
                if (nums[left] <= target && target < nums[mid]) right = mid - 1; // 在左边单调区间内，缩短搜索范围
                else left = mid + 1; //不在左边单调区间内，肯定在右边
            } else if (nums[mid] < nums[right]) {
                if (nums[mid] < target && target <= nums[right]) left = mid + 1;
                else right = mid - 1; //不在右边单调区间内，肯定在左边，砍掉右边
            }
        }
        return false; //左右越过也找不到
    }
}
