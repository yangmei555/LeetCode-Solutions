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
    int order = 1;
    public int kthSmallest(TreeNode root, int k) {
        return helper(root, k);
    }
    
    public Integer helper(TreeNode root, int k) {
        if (root == null)
            return null;
        Integer i = helper(root.left, k);
        if (i != null)
            return i;
        if (order == k)
            return root.val;
        order++;
        return helper(root.right, k);
    }
}
