// Closest Binary Search Tree Value II 常用的解法
//Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
//
//Note:
//
//Given target value is a floating point.
//You may assume k is always valid, that is: k ≤ total nodes.
//You are guaranteed to have only one unique set of k values in the BST that are closest to the target.

// 先BST用binary search找边界，然后用++ --的方法移动"指针"

package LeetCode;

import JavaBasics.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LC0272 {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        // cc
        if (root == null) return new ArrayList<>();

        List<Integer> res = new ArrayList<>();

        // 2 stacks to denote the bounday
        Stack<TreeNode> leftStack = new Stack<>();
        Stack<TreeNode> rightStack = new Stack<>();
        initializeStacks(leftStack, rightStack, root, target);

        while (k-- > 0) {
            if (!leftStack.isEmpty() && !rightStack.isEmpty()) {
                TreeNode left = leftStack.peek();
                TreeNode right = rightStack.peek();

                if (Math.abs(target - left.val) < Math.abs(target - right.val)) {
                    res.add(left.val);
                    popRightToLeft(leftStack);
                } else {
                    res.add(right.val);
                    popLeftToRight(rightStack);
                }
            } else if (!leftStack.isEmpty()) {
                res.add(popRightToLeft(leftStack).val);
            } else if (!rightStack.isEmpty()) {
                res.add(popLeftToRight(rightStack).val);
            } else {
                throw new RuntimeException();
            }
        }
        return res;
    }

    private void initializeStacks(Stack<TreeNode> leftStack, Stack<TreeNode> rightStack,
                                  TreeNode root, double target) {
        TreeNode cur = root;
        while (cur != null) {
            if (target <= cur.val) {
                rightStack.push(cur);
                cur = cur.left;
            } else {
                leftStack.push(cur);
                cur = cur.right;
            }
        }
    }

    private TreeNode popLeftToRight(Stack<TreeNode> stack) {
        TreeNode ret = stack.pop();
        TreeNode cur = ret.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return ret;
    }

    private TreeNode popRightToLeft(Stack<TreeNode> stack) {
        TreeNode ret = stack.pop();
        TreeNode cur = ret.left;
        while (cur != null) {
            stack.push(cur);
            cur = cur.right;
        }
        return ret;
    }
}
