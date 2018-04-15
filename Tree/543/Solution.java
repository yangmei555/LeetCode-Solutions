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
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        helper(root);
        return max;
    }
    
    public int helper(TreeNode root) {
        if (root == null)
            return -1;
        int left = helper(root.left);
        int right = helper(root.right);
        int len = left + right + 2;
        if (len > max)
            max = len;
        return left > right ? left + 1 : right + 1;
    }
}
