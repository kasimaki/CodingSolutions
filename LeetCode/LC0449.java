//Serialization is converting a data structure or object into a sequence of bits so that
// it can be stored in a file or memory buffer, or transmitted across a network connection link
// to be reconstructed later in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize a binary search tree. There is no restriction
// on how your serialization/deserialization algorithm should work. You need to ensure that
// a binary search tree can be serialized to a string, and this string can be deserialized
// to the original tree structure.
//
//The encoded string should be as compact as possible.
//

// too slow
package LeetCode;

import JavaBasics.TreeGenerator;
import JavaBasics.TreeNode;

import java.util.*;

public class LC0449 {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //cc
        if (root == null) return new String("");

        StringBuilder path = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur == null) continue;
                if (path.length() == 0) path.append(cur.val);
                else path.append("," + cur.val);
                // convert
                TreeNode left = cur.left;
                queue.offer(left);
                TreeNode right = cur.right;
                queue.offer(right);
            }
        }

        return path.toString();
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

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < numsSorted.length; i++) {
            map.put(numsSorted[i], i);
        }

        return assembly(nums, numsSorted, null, 0, nums.length - 1, map);
    }

    private TreeNode assembly(int[] levelOrder, int[] inOrder, TreeNode startNode, int inStart, int inEnd, Map<Integer, Integer> map) {
        // bc
        if (inStart > inEnd) return null;
        if (inStart == inEnd) return new TreeNode(inOrder[inStart]);

        boolean isFound = false;
        int idx = 0;

        for (int cur : levelOrder) {
            idx = map.get(cur);
            if (inStart <= idx && idx <= inEnd) {
                startNode = new TreeNode(cur);
                break;
            }
        }

        startNode.left = assembly(levelOrder, inOrder, startNode, inStart, idx - 1, map);
        startNode.right = assembly(levelOrder, inOrder, startNode, idx + 1, inEnd, map);
        return startNode;
    }

    private int biSearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) return mid;
            else if (nums[mid] < target) left = mid + 1;
            else right = right - 1;
        }
        return -1;
    }

    private void quickSort(int[] nums) {
        int len = nums.length;
        Random rm = new Random();
        dfs(nums, 0, len - 1, rm);
    }

    private void dfs(int[] nums, int start, int end, Random rm) {
        //bc
        if (start == end) return;

        int pivotIdx = rm.nextInt(end - start + 1);//start + (end - start) / 2;
        swap(nums, start + pivotIdx, end);
        int pivotVal = nums[end];

        int left = start;
        int right = end - 1;

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
        LC0449 sol = new LC0449();
        String data = sol.serialize(root);
        TreeNode res = sol.deserialize(data);
        System.out.println(res);
    }
}

