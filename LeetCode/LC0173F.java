package LeetCode;

// Follow up: pre Order

import JavaBasics.TreeNode;

class TreeNodeL173F {
    public int val;
    public TreeNodeL173F parent, left, right;

    public TreeNodeL173F(int val) {
        this.val = val;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}

public class LC0173F {
}

class BSTIteratorPre {
    TreeNodeL173F cur;

    public BSTIteratorPre(TreeNodeL173F root) {
        this.cur = root;
    }

    public int next() {
        TreeNodeL173F ret = cur;
        if (cur == null) throw new NullPointerException();
        if (cur.left != null) {
            cur.left.parent = cur;
            cur = cur.left;
        } else if (cur.right != null) {
            cur.right.parent = cur;
            cur = cur.right;
        } else {
            while (cur.parent != null) {
                cur = cur.parent;
                if (cur.left == ret) {
                    cur = cur.right;
                    break;
                }
            }
        }
        return ret.val;
    }

    public boolean hasNext() {
        return cur == null;
    }
}
