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
    public int minDepth(TreeNode root) {
        if (root == null)
            return 0;
        if (root.left == null && root.right == null)
            return 1;
        return Math.min(root.left == null ? Integer.MAX_VALUE : minDepth(root.left), 
                        root.right == null ? Integer.MAX_VALUE : minDepth(root.right)) + 1;
    }
}
