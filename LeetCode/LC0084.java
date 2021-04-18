//Largest Rectangle in Histogram 高频
//Given an array of integers heights representing the histogram's bar height where the width of each bar is 1,
// return the area of the largest rectangle in the histogram.

package LeetCode;

import java.util.Stack;

public class LC0084 {
    public int largestRectangleArea(int[] heights) {
        //cc
        if (heights == null || heights.length == 0) return 0; //throw new IllegalArgumentException();
        if (heights.length == 1) return heights[0];

        Stack<Integer> stack = new Stack<>();
        int len = heights.length;
        int max = 0;
        for (int i = 0; i <= len; i++) {
            int h;
            if (i < len) {
                h = heights[i];
            } else {
                h = 0;
            }
            //if (i == len)
            if (stack.isEmpty() || heights[stack.peek()] <= h) { //TODO <=??
                stack.push(i);
            } else {
                while (!stack.isEmpty() && h < heights[stack.peek()]) {
                    int top = stack.pop();
                    int curHeight = heights[top] * (i - (stack.isEmpty() ? 0 : stack.peek()+1));
                    max = Math.max(max, curHeight);
                }
                stack.push(i);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        int[] heights = new int[] {2,1,5,6,2,3};
        LC0084 sol = new LC0084();
        int res = sol.largestRectangleArea(heights);
        System.out.println(res);
    }
}
