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
    public boolean checkEqualTree(TreeNode root) {
        if (root == null)
            return false;
        helper1(root);
        return helper2(root.left, root.val) || helper2(root.right, root.val);
    }
    
    public int helper1(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper1(root.left), right = helper1(root.right);
        root.val += left + right;
        return root.val;
    }
    
    public boolean helper2(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.val * 2 == sum)
            return true;
        return helper2(root.left, sum) || helper2(root.right, sum);
    }
}
