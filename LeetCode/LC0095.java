//Unique Binary Search Tree II
//Given an integer n, return all the structurally unique BST's (binary search trees),
// which has exactly n nodes of unique values from 1 to n. Return the answer in any order.

//TC = O(n^n)

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

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import JavaBasics.TreeNode;

public class LC0095 {
    public List<TreeNode> generateTrees(int n) {
        // cc
        if (n < 0) throw new IllegalArgumentException();
        if (n == 0) return new ArrayList<>();
        if (n == 1) return Arrays.asList(new TreeNode(1));

        return dfs(1,n);
    }

    private List<TreeNode> dfs(int start, int end) {
        List<TreeNode> res = new ArrayList<>();

        // bc
        if (start > end) {
            res.add(null);  //不能直接asList(null)，因为null不是一个Object，会NullPointedException
            return res;
        } //null
        //if (start == end) return Arrays.asList(new TreeNode(1)); //node


        // branch
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = dfs(start, i-1);
            List<TreeNode> rightList = dfs(i+1, end);

            // combination
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i); //每个combination都是新的root
                    root.left = left;
                    root.right = right;
                    res.add(root);
                }
            }
        }
        return res;
    }
}
