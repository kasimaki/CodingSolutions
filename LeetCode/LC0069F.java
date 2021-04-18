package LeetCode;

public class LC0069F {
    public String mySqrt(int x) {
        //cc

        if (x <= 1) return String.valueOf(x + ".00");
        long newX = (long) x * 10000;
        long start = 1;
        long end = Math.min(newX / 2, (long)Math.pow(2, 23));
        while (start <= end) {
            long mid = start + (end - start) / 2;
            if (mid == newX / mid) {
                String res = String.valueOf(mid);
                return res.substring(0, res.length()-2) + "." + res.substring(res.length()-2);
            }
            else if (mid < newX / mid) {
                start = mid + 1;
            } else {
                end = end - 1;
            }
        }
        String res = String.valueOf(end);
        return res.substring(0, res.length()-2) + "." + res.substring(res.length()-2);
    }

    public static void main(String[] args) {
        int x = 2147395599;
        LC0069F sol = new LC0069F();
        String res = sol.mySqrt(x);
        System.out.println(res);
    }
}
