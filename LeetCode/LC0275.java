//Given an array of citations sorted in ascending order (each citation is a non-negative integer)
// of a researcher, write a function to compute the researcher's h-index.
//
//According to the definition of h-index on Wikipedia: "A scientist has index h if h of his/her
// N papers have at least h citations each, and the other N − h papers have no more than h citations each."
package LeetCode;

public class LC0275 {
    public int hIndex(int[] citations) {
        // cc
        if (citations == null || citations.length == 0) return 0;
        int len = citations.length;

        int left = 0;
        int right = len -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (citations[mid] == len - mid) return len - mid;
            else if (citations[mid] < len - mid) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }
        return len - left;
    }
}
