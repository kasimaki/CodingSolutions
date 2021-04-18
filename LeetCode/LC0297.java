//Serialize and Deserialize Binary Tree
//Serialization is the process of converting a data structure or object into a sequence of bits
// so that it can be stored in a file or memory buffer, or transmitted across a network connection link
// to be reconstructed later in the same or another computer environment.
//
//Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your
// serialization/deserialization algorithm should work. You just need to ensure that a binary tree
// can be serialized to a string and this string can be deserialized to the original tree structure.
//
//Clarification: The input/output format is the same as how LeetCode serializes a binary tree.
// You do not necessarily need to follow this format, so please be creative and come up with
// different approaches yourself.
//


package LeetCode;

import JavaBasics.TreeGenerator;
import JavaBasics.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LC0297 {
    public static void main(String[] args) {
        TreeGenerator tg = new TreeGenerator();
        String s = new String("1,2,3,#,#,4,5,6,7");
        TreeNode root = tg.deserialize(s);
        Codec sol = new Codec();
        String res = sol.serialize(root);
        TreeNode res2 = sol.deserialize(res);
        System.out.println(res2);
    }
}

class Codec {

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        //cc
        if (root == null) return new String("#");

        StringBuilder path = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        // Tree不用查重

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur == null) {
                    //if (queue.size() == 0) break;
                    if (path.length() == 0) path.append("#");
                    else path.append(",#");
                    continue;
                }
                if (path.length() == 0) path.append(cur.val);
                else path.append("," + cur.val);
                // convert
                queue.offer(cur.left);
                queue.offer(cur.right);
            }
        }

        //cut trailing #
        while (path.charAt(path.length() -1) == '#') {
            path.deleteCharAt(path.length() -1); //remove#
            path.deleteCharAt(path.length() -1); //remove ,
        }


        return path.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        // cc
        if (data == null || data.length() == 0) throw new IllegalArgumentException();
        if (data.equals("#")) return null;

        String[] splitData = data.split(",");
        TreeNode[] arr = new TreeNode[splitData.length];

        for (int i = 0; i < arr.length; i++) {
            String sub = splitData[i];
            if (sub.equals("#")) arr[i] = null;
            else arr[i] = new TreeNode(Integer.valueOf(sub));
        }


        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(arr[0]);

        int idx = 1; //TODO
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode cur = queue.poll();
                if (cur != null) {
                    if (idx < arr.length) {
                        TreeNode left = arr[idx++];
                        cur.left = left;
                        queue.offer(left);
                    }
                    if (idx < arr.length) {
                        TreeNode right = arr[idx++];
                        cur.right = right;
                        queue.offer(right);
                    }
                }
            }
        }


        return arr[0];
    }

    // Encodes a tree to a single string.
    public String serializeDFS(TreeNode root) {
        // cc
        if (root == null) return new String("#");

        StringBuilder path = new StringBuilder();
        //path.append(root.val);
        dfs(root, path);
        //path.deleteCharAt(path.length()-1);
        return path.toString();
    }
    private void dfs(TreeNode cur, StringBuilder path) {
        // bc
        if (cur == null) {
            if (path.length() == 0) path.append("#");
            else path.append(",#");
            return; //别忘了return
        }

        // branches
        if (path.length() == 0) path.append(cur.val);
        else path.append("," + cur.val);

        dfs(cur.left, path);
        dfs(cur.right, path);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserializeDFS(String data) {
        //cc
        if (data == null || data.length() == 0) throw new IllegalArgumentException();
        if (data.equals("#")) return null;

        int len = data.length();

        List<TreeNode> seq = new ArrayList<>();

        int i = 0;
        while (i < len) {
            for (int j = i+1; j <= len; j++) {
                if (j == len || data.charAt(j) == ',') {
                    String patch = data.substring(i, j);

                    if (patch.equals("#")) seq.add(null);
                    else seq.add(new TreeNode(Integer.valueOf(patch)));

                    i = j+1;
                    break;
                }
            }

        }

        TreeNode[] arr = new TreeNode[seq.size()];
        arr = seq.toArray(arr);

        return dfs(arr, new int[]{0});
    }

    private TreeNode dfs(TreeNode[] arr, int[] idx) {
        TreeNode cur = arr[idx[0]];
        idx[0]++; //global变量推动指针向右
        if (cur == null) return null;

        cur.left = dfs(arr, idx);
        cur.right = dfs(arr, idx);

        return cur;
    }

}