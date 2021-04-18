//Count Complete Tree Nodes
//Given the root of a complete binary tree, return the number of the nodes in the tree.
//
//According to Wikipedia, every level, except possibly the last, is completely filled in a complete binary tree,
// and all nodes in the last level are as far left as possible. It can have between 1 and 2h nodes inclusive
// at the last level h.

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


package LeetCode;
import JavaBasics.TreeNode;

public class LC0222 {
    public int countNodes(TreeNode root) {
        //cc
        if (root == null) return 0;

        return dfs(root);
    }

    private int dfs(TreeNode root) {
        // bc
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        int rightHeight = getHeight(root.right);

        if (leftHeight == rightHeight) { //left must be full tree
            return (int) (Math.pow(2, leftHeight)-1 + 1 + dfs(root.right));
        }
        if (rightHeight + 1 == leftHeight) {
            return (int) (dfs(root.left) + 1 + Math.pow(2, rightHeight)-1);
        }
        throw new RuntimeException("not a complete tree");
    }

    private int getHeight(TreeNode root) {
        // only used for complete tree
        if (root == null) return 0;

        int leftHeight = getHeight(root.left);
        //int rightHeight = getHeight(root.right);

        return leftHeight+1;
    }
}
