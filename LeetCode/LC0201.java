// Bitwise AND of Numbers Range
//Given a range [m, n] where 0 <= m <= n <= 2147483647,
// return the bitwise AND of all numbers in this range, inclusive.

package LeetCode;

public class LC0201 {
    public int rangeBitwiseAnd(int m, int n) {
        //cc
        if (m < 0 || n < 0 || m > n) throw new IllegalArgumentException();
        if (m == n) return m;

        int count = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            count ++;
        }

        return m << count;
    }
}
