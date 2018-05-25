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
    
    int result = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        helper(root);
        return result;
    }
    
    public Integer helper(TreeNode root) {
        Integer left;
        Integer right;
        if (root.left == null)
            left = root.val;
        else
            left = helper(root.left);
        if (root.right == null)
            right = root.val;
        else
            right = helper(root.right);
        if (left == null || right == null)
            return null;
        if (root.val == left && root.val == right) {
            result++;
            return root.val;
        } else {
            return null;
        }
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
    
    int result = 0;
    
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null)
            return 0;
        helper(root);
        return result;
    }
    
    public boolean helper(TreeNode root) {
        if (root == null)
            return true;
        boolean left = helper(root.left);
        boolean right = helper(root.right);
        if (left && right) {
            if ((root.left == null || root.left.val == root.val) && 
                (root.right == null || root.right.val == root.val)) {
                result++;
                return true;
            } else {
                return false;
            }
        }
        return false;
    }
}
