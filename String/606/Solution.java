/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public String tree2str(TreeNode t) {
        StringBuilder sb = new StringBuilder();
        helper(t, sb);
        return sb.toString();
    }
    
    void helper(TreeNode t, StringBuilder sb) {
        if (t == null)
            return;
        sb.append(t.val);
        if (t.left == null && t.right == null)
            return;
        else {
            sb.append('(');
            helper(t.left, sb);
            sb.append(')');
            if (t.right != null) {
                sb.append('(');
                helper(t.right, sb);
                sb.append(')');
            }
        }
    }
}
