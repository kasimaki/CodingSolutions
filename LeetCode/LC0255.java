// Verify Preorder Sequence in Binary Search Tree
//Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
//
//You may assume each number in the sequence is unique.

package LeetCode;

import java.util.Stack;

public class LC0255 {
    public boolean verifyPreorder(int[] preorder) {
        //cc
        if (preorder == null || preorder.length <= 1) return true;

        Stack<Integer> stack = new Stack<>();
        int lowerBound = Integer.MIN_VALUE;

        for (int n : preorder) {
            if (stack.isEmpty() || stack.peek() > n) {
                if (n < lowerBound) return false;
                stack.push(n);
            } else {
                while (!stack.isEmpty() && stack.peek() < n) {
                    int top = stack.pop();
                    lowerBound = Math.max(lowerBound, top);
                }
                stack.push(n); //更新了左边界，此时n必定在左边界右侧，不能出现比n小的值
            }
        }
        return true;
    }
}
