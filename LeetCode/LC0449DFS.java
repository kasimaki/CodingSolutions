package LeetCode;

import JavaBasics.TreeGenerator;
import JavaBasics.TreeNode;

import java.util.Random;

public class LC0449DFS {
    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //cc
        if (root == null) return new String("");

        StringBuilder path = new StringBuilder();
        dfs(root, path);
        return path.toString();
    }

    private void dfs(TreeNode cur, StringBuilder path) {
        //bc
        if (cur == null) {
            return;
        }

        if (path.length() == 0) path.append(cur.val);
        else path.append("," + cur.val);

        dfs(cur.left, path);
        dfs(cur.right, path);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        //cc
        if (data == null) throw new IllegalArgumentException();
        if (data.length() == 0) return null;

        String[] subs = data.split(",");
        int[] nums = new int[subs.length];
        for (int i = 0; i < subs.length; i++) {
            nums[i] = Integer.parseInt(subs[i]);
        }
        int[] numsSorted = nums.clone();
        quickSort(numsSorted);

        return assemblyDFS(nums, numsSorted, 0, nums.length-1, 0, numsSorted.length-1);
    }

    private TreeNode assemblyDFS(int[] preOrder, int[] inOrder, int preStart, int preEnd,
                                 int inStart, int inEnd) {
        int curVal = preOrder[preStart];
        TreeNode cur = new TreeNode(curVal);
        //bc
        if (preStart == preEnd) return cur;

        int curInOrderIdx = biSearch(inOrder, curVal, inStart, inEnd);

        int leftLen = curInOrderIdx - inStart;
        int rightLen = inEnd - curInOrderIdx;

        if (leftLen == 0) cur.left = null;
        else cur.left = assemblyDFS(preOrder, inOrder, preStart+1, preStart + leftLen,
                inStart, curInOrderIdx-1);

        if (rightLen == 0) cur.right = null;
        else cur.right = assemblyDFS(preOrder, inOrder, preStart + leftLen + 1, preEnd,
                curInOrderIdx + 1, inEnd);

        return cur;
    }

    private int biSearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = right -1;
        }
        return -1;
    }


    private void quickSort(int[] nums) {
        int len = nums.length;
        Random rm = new Random();
        dfs(nums, 0, len-1, rm);
    }

    private void dfs(int[] nums, int start, int end, Random rm) {
        //bc
        if (start == end) return;

        int pivotIdx = rm.nextInt(end - start + 1);//start + (end - start) / 2;
        swap(nums, start + pivotIdx, end);
        int pivotVal = nums[end];

        int left = start;
        int right = end-1;

        while (left <= right) { //TODO
            if (nums[left] >= pivotVal && nums[right] < pivotVal) {
                swap(nums, left, right);
                left++;
                right--;
            } else if (nums[right] >= pivotVal) {
                right--;
            } else if (nums[left] < pivotVal) {
                left++;
            } else {
                left++;
                right--;
            }
        }
        swap(nums, left, end);

        if (right >= start) dfs(nums, start, right, rm);
        if (left <= end) dfs(nums, left, end, rm);
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) return;
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void main(String[] args) {
        TreeGenerator tg = new TreeGenerator();
        String s = new String("3,2,4,1");
        TreeNode root = tg.deserialize(s);
        LC0449DFS sol = new LC0449DFS();
        String data = sol.serialize(root);
        TreeNode res = sol.deserialize(data);
        System.out.println(res);
    }
}
