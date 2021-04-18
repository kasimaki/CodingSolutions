//sqrt(x)
//Given a non-negative integer x, compute and return the square root of x.
//
//Since the return type is an integer, the decimal digits are truncated,
// and only the integer part of the result is returned.

package LeetCode;

public class LC0069 {
    public int mySqrt(int x) {
        //cc
        if (x <= 1) return x;
        int start = 1;
        int end = Math.min(x / 2, (int)Math.pow(2, 16));
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (mid == x / mid) return mid;
            else if (mid < x / mid) {
                start = mid + 1;
            } else {
                end = end - 1;
            }
        }
        return end;
    }

    public static void main(String[] args) {
        int x = 2147395599;
        LC0069 sol = new LC0069();
        int res = sol.mySqrt(x);
        System.out.println(res);
    }
}
