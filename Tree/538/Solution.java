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
    public TreeNode convertBST(TreeNode root) {
        helper(root, 0);
        return root;
    }
    
    public int helper(TreeNode root, int add) {
        if (root == null)
            return add;
        int ret = helper(root.right, add);
        root.val += ret;
        return helper(root.left, root.val);
    }
}
