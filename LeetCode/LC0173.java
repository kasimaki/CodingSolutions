// Binary Search Tree Iterator
//Implement the BSTIterator class that represents an iterator over the in-order traversal of a binary search tree (BST):
//
//BSTIterator(TreeNode root) Initializes an object of the BSTIterator class.
// The root of the BST is given as part of the constructor.
// The pointer should be initialized to a non-existent number smaller than any element in the BST.
//boolean hasNext() Returns true if there exists a number in the traversal to the right of the pointer,
// otherwise returns false.
//int next() Moves the pointer to the right, then returns the number at the pointer.
//Notice that by initializing the pointer to a non-existent smallest number,
// the first call to next() will return the smallest element in the BST.
//
//You may assume that next() calls will always be valid.
// That is, there will be at least a next number in the in-order traversal when next() is called.

package LeetCode;

import JavaBasics.TreeNode;

import java.util.Stack;

public class LC0173 {
}

class BSTIterator {
    private Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        initialStack(stack, root);
    }

    private void initialStack(Stack<TreeNode> stack, TreeNode root) {

        TreeNode cur = root;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
    }

    public int next() {
        TreeNode ret = stack.pop();
        TreeNode cur = ret.right;
        while (cur != null) {
            stack.push(cur);
            cur = cur.left;
        }
        return ret.val;
    }

    public boolean hasNext() {
        return !stack.isEmpty();
    }
}