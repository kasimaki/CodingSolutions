// Follow Up: 可以在某个root拐一个弯，即增序和降序相接

package LeetCode;

import JavaBasics.TreeNode;

public class LC0298F {
    public int longestConsecutive(TreeNode root) {
        // cc
        if (root == null) return 0;
        int[] globalMax = new int[] {0};
        dfs(root, globalMax);
        return globalMax[0];
    }

    private int[] dfs(TreeNode root, int[] globalMax) {
        // bc
        if (root == null) {
            return new int[] {0, 0};
        }

        int[] left = dfs(root.left, globalMax);
        int[] right = dfs(root.right, globalMax);

        // check bottom-up increase
        int increaseMax = 0;
        if (root.left != null && root.val - 1 == root.left.val) {
            increaseMax = Math.max(increaseMax, left[0]);
        }
        if (root.right != null && root.val - 1 == root.right.val) {
            increaseMax = Math.max(increaseMax, right[0]);
        }

        // check bottom-up decrease
        int decreaseMax = 0;
        if (root.left != null && root.val + 1 == root.left.val) {
            decreaseMax = Math.max(decreaseMax, left[1]);
        }
        if (root.right != null && root.val + 1 == root.right.val) {
            decreaseMax = Math.max(decreaseMax, right[1]);
        }

        globalMax[0] = Math.max(globalMax[0], increaseMax + decreaseMax + 1);
        return new int[]{increaseMax+1, decreaseMax+1};
    }
}
