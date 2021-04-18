// Kth Largest Element in an Array
//Find the kth largest element in an unsorted array.
// Note that it is the kth largest element in the sorted order, not the kth distinct element. (may contain duplicate)


package LeetCode;


public class LC0215 {
    public int findKthLargest(int[] nums, int k) {
        //cc
        if (nums == null || nums.length < k) throw new IllegalArgumentException("Not exist");

        return quickSelectLargest(nums, k, 0, nums.length - 1);
    }

    private int quickSelectLargest(int[] nums, int k, int start, int end) {
        //bc
        if (start == end) return nums[start];

        int partitionIdx = findPartitionIdxLargest(nums, start, end);
        int rank = end - partitionIdx + 1;

        if (rank == k) {
            return nums[partitionIdx];
        } else if (rank < k) {
            return quickSelectLargest(nums, k - rank, start, partitionIdx - 1);
        } else {
            return quickSelectLargest(nums, k, partitionIdx + 1, end);
        }
    }

    private int findPartitionIdxLargest(int[] nums, int start, int end) {
        int pivot = nums[start];
        int right = end + 1;

        for (int i = end; i > start; i--) {
            if (nums[i] > pivot) {
                swap(nums, --right, i);
            }
        }
        swap(nums, right - 1, start);
        return right - 1;
    }

    public int findKthSmallest(int[] nums, int k) {
        //cc
        if (nums == null || nums.length < k) throw new IllegalArgumentException("Not exist");

        return quickSelect(nums, k, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k, int start, int end) {
        //bc
        if (start == end) return nums[start];

        int partitionIdx = findPartitionIdx(nums, start, end);
        int rank = partitionIdx - start + 1;

        if (rank == k) {
            return nums[partitionIdx];
        } else if (rank < k) {
            return quickSelect(nums, k - rank, partitionIdx + 1, end);
        } else {
            return quickSelect(nums, k, start, partitionIdx - 1);
        }
    }

    private int findPartitionIdx(int[] nums, int start, int end) {
        int pivot = nums[end];
        int left = start - 1;

        for (int i = start; i < end; i++) {
            if (nums[i] < pivot) {
                swap(nums, left + 1, i);
                left ++;
            }
        }
        swap(nums, left + 1, end);
        return left + 1;
    }

    private void swap(int[] nums, int i, int end) {
        if (i == end) return;
        int temp = nums[i];
        nums[i] = nums[end];
        nums[end] = temp;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{3,2,1,5,6,4};
        int k = 2;
        LC0215 sol = new LC0215();
        int res = sol.findKthLargest(nums, k);
        System.out.println(res);
    }
}
