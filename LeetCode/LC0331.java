//Verify Preorder Serialization of a Binary Tree
//One way to serialize a binary tree is to use pre-order traversal.
// When we encounter a non-null node, we record the node's value. If it is a null node,
// we record using a sentinel value such as #.

package LeetCode;

public class LC0331 {
    public boolean isValidSerialization(String preorder) {
        if (preorder == null || preorder.length() == 0) return false;
        if (preorder.length() == 1 && preorder.equals("#")) return true;

        String[] ss = preorder.split(",");

        int len = ss.length;
        int delta = 1;
        for (int i = 0; i < len; i++) {
            String cur = removeSpace(ss[i]);
            if (cur.equals("#")) {
                delta --;
            } else {
                if (delta <= 0) return false; //在结束前不能<=0，如果是在#之后check，可能会刚好到达最后，产生错误。而在数字前，说明string并没结束，此时必定不应该出现delta <=0
                delta ++;
            }
        }
        return delta == 0;
    }

    private String removeSpace(String s) {
        StringBuilder sb = new StringBuilder(s);
        int i = sb.length()-1;
        while (i >=0) {
            if (sb.charAt(i) == ' ') {
                sb.deleteCharAt(i);
            }
            i--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = new String("1,#,#,1");
        LC0331 sol = new LC0331();
        boolean res = sol.isValidSerialization(s);
        System.out.println(res);
    }

}
