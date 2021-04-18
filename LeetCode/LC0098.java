//Validate Binary Search Tree
//Given the root of a binary tree, determine if it is a valid binary search tree (BST).
//
//A valid BST is defined as follows:
//
//The left subtree of a node contains only nodes with keys less than the node's key.
//The right subtree of a node contains only nodes with keys greater than the node's key.
//Both the left and right subtrees must also be binary search trees.

/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

//本方法是in order遍历过程中，通过prev记录前一个TreeNode来完成。

package LeetCode;

import JavaBasics.TreeNode;

public class LC0098 {
    private TreeNode prev;

    public LC0098() {
        prev = null;
    }

    public boolean isValidBST(TreeNode root) {
        // cc  covered by helper

        return inOrder(root);
    }

    private boolean inOrder(TreeNode root) {
        //bc
        if (root == null) {
            return true;
        }

        // go left, if false all false
        if (!inOrder(root.left)) {
            return false;
        }

        // check if root is valid (larger than most right in left sub tree)
        if (prev != null && prev.val >= root.val) {
            return false;
        }

        // set new prev
        prev = root;

        // go right
        return inOrder(root.right);

    }

}
