// Inorder Successor in BST 类似于L270
//Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//
//The successor of a node p is the node with the smallest key greater than p.val.
package LeetCode;

import JavaBasics.TreeGenerator;
import JavaBasics.TreeNode;

public class LC0285 {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        //cc
        if (root == null || p == null) throw new IllegalArgumentException();

        TreeNode cur = root;
        TreeNode ret = null;
        while (cur != null) {
            if (cur.val <= p.val) {
                cur = cur.right;
            } else {
                ret = cur;
                cur = cur.left;
            }
        }
        return ret;
    }

    public TreeNode inorderSuccessorAllTree(TreeNode root, TreeNode p) {
        //cc
        if (root == null || p == null) throw new IllegalArgumentException();

        TreeNode[] ret = new TreeNode[1];
        ret[0] = null;
        TreeNode[] prev = new TreeNode[1];
        prev[0] = null;
        dfs(root, p, prev, ret);
        return ret[0];
    }
    private void dfs(TreeNode cur, TreeNode p, TreeNode[] prev, TreeNode[] ret) {
        // bc
        if (cur == null) {
            return;
        }
        dfs(cur.left, p, prev, ret);

        if (prev[0] != null && prev[0].val == p.val && ret[0] == null) { // && prev[0] == p
            ret[0] = cur;
            prev[0] = cur;
            return;
        }
        prev[0] = cur;
        dfs(cur.right, p, prev, ret);
    }

    public static void main(String[] args) {
        TreeGenerator tg = new TreeGenerator();
        String s1 = new String("5,3,6,2,4,#,#,1");
        String s2 = new String("1");
        TreeNode root = tg.deserialize(s1);
        TreeNode p = tg.deserialize(s2);
        LC0285 sol = new LC0285();
        TreeNode res = sol.inorderSuccessorAllTree(root, p);
        System.out.println(res.val);
    }
}
