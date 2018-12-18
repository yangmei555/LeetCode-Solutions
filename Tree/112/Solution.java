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
    public boolean hasPathSum(TreeNode root, int sum) {
        if (root == null)
            return false;
        if (root.left == null && root.right == null)
            return root.val == sum;
        return hasPathSum(root.left, sum-root.val) || hasPathSum(root.right, sum-root.val);
    }
}


class Solution {
    public boolean hasPathSum(TreeNode root, int sum) {
        return helper(root, 0, sum);
    }
    
    public boolean helper(TreeNode root, int addup, int sum) {
        if (root == null)
            return false;
        addup += root.val;
        if (addup == sum && root.left == null && root.right == null)
            return true;
        return helper(root.left, addup, sum) || helper(root.right, addup, sum);
    }
}
