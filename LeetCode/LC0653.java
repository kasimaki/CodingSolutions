// Two Sum IV - Input is a BST
//Given the root of a Binary Search Tree and a target number k, return true if there exist two elements
// in the BST such that their sum is equal to the given target.

// two stacks to run BST as Sorted Array

package LeetCode;

import JavaBasics.TreeNode;
import JavaBasics.TreeGenerator;
import java.util.Stack;

public class LC0653 {
    public boolean findTarget(TreeNode root, int k) {  // k is target
        //cc
        if (root == null) return false;

        Stack<TreeNode> leftStack = initializeLeftStack(root);
        Stack<TreeNode> rightStack = initializeRightStack(root);

        while (!leftStack.isEmpty() && !rightStack.isEmpty()) {
            TreeNode left = leftStack.peek();
            TreeNode right = rightStack.peek();

            // condition to stop running while
            if (left == right) break;

            if (left.val + right.val < k) { // smaller than k, need to move left pointer to right
                popStackLeft(leftStack);
            } else if (left.val + right.val > k) {// larger, move right to left
                popStackRight(rightStack);
            } else {
                return true;
            }

        }
        return false;
    }

    private Stack<TreeNode> initializeLeftStack(TreeNode root) {
        Stack<TreeNode> leftStack = new Stack<>();

        TreeNode cur = root;
        while (cur != null) {
            leftStack.push(cur);
            cur = cur.left;
        }
        return leftStack;
    }

    private Stack<TreeNode> initializeRightStack(TreeNode root) {
        Stack<TreeNode> rightStack = new Stack<>();

        TreeNode cur = root;
        while (cur != null) {
            rightStack.push(cur);
            cur = cur.right;
        }
        return rightStack;
    }

    private void popStackLeft(Stack<TreeNode> leftStack) {
        TreeNode cur = leftStack.pop();
        cur = cur.right;
        while (cur != null) {
            leftStack.push(cur);
            cur = cur.left;
        }
    }

    private void popStackRight(Stack<TreeNode> rightStack) {
        TreeNode cur = rightStack.pop();
        cur = cur.left;
        while (cur != null) {
            rightStack.push(cur);
            cur = cur.right;
        }
    }

    public static void main(String[] args) {
        TreeGenerator tg = new TreeGenerator();
        String s = new String("1,0,4,-2,#,3");
        int k = 7;
        TreeNode root = tg.deserialize(s);
        LC0653 sol = new LC0653();
        boolean res = sol.findTarget(root, k);
        System.out.println(res);
    }
}
