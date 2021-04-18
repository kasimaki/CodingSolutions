//Closest Binary Search Tree Value
//Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
//
//Note:
//
//Given target value is a floating point.
//You are guaranteed to have only one unique value in the BST that is closest to the target.

package LeetCode;

import JavaBasics.TreeNode;

public class LC0270 {
    public int closestValue(TreeNode root, double target) {
        // cc
        if (root == null) throw new IllegalArgumentException();

        int closest = root.val;
        TreeNode cur = root;
        while (cur != null) {
            if (target < cur.val){
                if (Math.abs((double) cur.val - target) < Math.abs((double) closest - target)) {
                    closest = cur.val;
                }
                cur = cur.left;
            } else if (cur.val < target) {
                if (Math.abs((double) cur.val - target) < Math.abs((double) closest - target)) {
                    closest = cur.val;
                }
                cur = cur.right;
            } else {
                return cur.val;
            }

        }
        return closest;
    }

    public Integer lower(TreeNode root, double target) {
        // cc
        if (root == null) throw new IllegalArgumentException();

        TreeNode cur = root;
        Integer lowerLargest = null;
        while (cur != null) {
            if (target <= cur.val) { //等于的时候，也要向下探索。对于floor，等于的时候需要直接返回
                cur = cur.left;
            } else if (cur.val < target) {
                if (lowerLargest == null) {
                    lowerLargest = cur.val;
                } else {
                    if (Math.abs(cur.val - target) < Math.abs(lowerLargest - target)) {
                        lowerLargest = cur.val;
                    }
                }
                cur = cur.right;
            }
        }
        return lowerLargest;
    }

    public Integer higher(TreeNode root, double target) {
        // cc
        if (root == null) throw new IllegalArgumentException();

        TreeNode cur = root;
        Integer higherSmallest = null;
        while (cur != null) {
            if (target < cur.val) {
                if (higherSmallest == null) {
                    higherSmallest = cur.val;
                } else {
                    if (Math.abs(cur.val - target) < Math.abs(higherSmallest - target)) {
                        higherSmallest = cur.val;
                    }
                }
                cur = cur.left;
            } else if (target >= cur.val) {
                cur = cur.right;
            }
        }
        return higherSmallest;
    }
}
