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
    public List<String> binaryTreePaths(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        List<String> res = new LinkedList<>();
        helper(root, sb, res);
        return res;
    }
    
    public void helper(TreeNode root, StringBuilder sb, List<String> res) {
        if (root == null)
            return;
        int len = sb.length();
        sb.append(root.val);
        if (root.left == null && root.right == null) {
            res.add(sb.toString());
        } else {
            sb.append("->");
            helper(root.left, sb, res);
            helper(root.right, sb, res);
        }
        sb.setLength(len);
    }
}
