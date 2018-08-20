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
    int res = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        helper(root);
        return res;
    }
    
    public int helper(TreeNode root) {
        if (root == null)
            return Integer.MIN_VALUE;
        int left = helper(root.left), right = helper(root.right);
        int local = root.val + (left > 0 ? left : 0) + (right > 0 ? right : 0);
        res = res > local ? res : local;
        return Math.max(left, Math.max(right, 0)) + root.val;
    }
}
