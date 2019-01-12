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
    public boolean isBalanced(TreeNode root) {
        if (helper(root) != -1)
            return true;
        else
            return false;
    }
    
    public int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = 0, right = -1;
        if ((left = helper(root.left)) == -1)
            return -1;
        if ((right = helper(root.right)) == -1)
            return -1;
        if (Math.abs(left - right) <= 1)
            return Math.max(left, right) + 1;
        else
            return -1;
    }
}
