//Largest BST Subtree
//Given the root of a binary tree, find the largest subtree, which is also a Binary Search Tree (BST),
// where the largest means subtree has the largest number of nodes.
//
//A Binary Search Tree (BST) is a tree in which all the nodes follow the below-mentioned properties:
//
//The left subtree values are less than the value of their parent (root) node's value.
//The right subtree values are greater than the value of their parent (root) node's value.
//Note: A subtree must include all of its descendants.
//
//Follow up: Can you figure out ways to solve it with O(n) time complexity?

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

// return min, max, number of nodes, ifBST

package LeetCode;

import JavaBasics.TreeNode;
import com.sun.source.tree.Tree;

class Result {
    int min, max, size;
    boolean isBST;

    public Result(int min, int max, int size, boolean ifBST){
        this.min = min;
        this.max = max;
        this.size = size;
        this.isBST = ifBST;
    }
}

public class LC0333 {
    public int largestBSTSubtree(TreeNode root) {
        //cc
        if (root == null) return 0;
        int[] globalMax = new int[1];
        Result res = dfs(root, globalMax);
        return globalMax[0];
    }

    private Result dfs(TreeNode root, int[] globalMax) {
        // bc true
        if (root == null) {
            return new Result(0,0,0,true);
        }

        Result left = dfs(root.left, globalMax);
        Result right = dfs(root.right, globalMax);

        if (left.isBST && right.isBST) {
            int treeMin, treeMax, treeSize;
            boolean leftValid, rightValid;
            int max = 0;

            if (root.left == null) {
                treeMin = root.val;
                leftValid = true;
            } else {
                treeMin = left.min;
                leftValid = left.max < root.val;
            }

            if (root.right == null) {
                treeMax = root.val;
                rightValid = true;
            } else {
                treeMax = right.max;
                rightValid = root.val < right.min;
            }

            if (leftValid && rightValid) {
                treeSize = left.size + right.size + 1;
                globalMax[0] = Math.max(globalMax[0], treeSize);
                return new Result(treeMin, treeMax, treeSize, true);
            }
        }
        return new Result(root.val, root.val, 1, false);
    }
}
