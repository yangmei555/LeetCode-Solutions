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
    public TreeNode pruneTree(TreeNode root) {
        return helper(root) ? root : null;
    }
    
    public boolean helper(TreeNode root) {
        if (root == null)
            return false;
        boolean left = helper(root.left), right = helper(root.right);
        if (!left)
            root.left = null;
        if (!right)
            root.right = null;
        return !left && !right && root.val == 0 ? false : true;
    }
}


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
    public TreeNode pruneTree(TreeNode root) {
        if (root == null)
            return null;
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        return root.val == 0 && root.left == null && root.right == null ? null : root;
    }
}
