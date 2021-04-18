//Binary Tree Longest Consecutive Sequence 高频题
//Given a binary tree, find the length of the longest consecutive sequence path.
//
//The path refers to any sequence of nodes from some starting node to any node
// in the tree along the parent-child connections. The longest consecutive path
// need to be from parent to child (cannot be the reverse).

package LeetCode;
import JavaBasics.TreeNode;
public class LC0298 {
    //private int globalMax;
    //public LC0298() {
        //globalMax = 0;
    //}
    public int longestConsecutive(TreeNode root) {
        // cc
        if (root == null) return 0;
        int[] globalMax = new int[] {0};
        dfs(root, globalMax);
        return globalMax[0];
    }

    private int dfs(TreeNode root, int[] globalMax) { //传入的global变量不是object时，要变为array
        //bc
        if (root == null) return 0;

        int left = dfs(root.left, globalMax);
        int right = dfs(root.right, globalMax);

        int max = 0;

        if (root.left != null && (root.left.val == root.val + 1)) {
            max = Math.max(max, left);
        }
        if (root.right != null && (root.right.val == root.val + 1)) {
            max = Math.max(max, right);
        }
        globalMax[0] = Math.max(globalMax[0], max+1);
        return max+1;
    }
}
