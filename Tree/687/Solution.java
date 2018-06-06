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
    
    public int longestUnivaluePath(TreeNode root) {
        helper(root);
        return max;
    }
    
    public int helper(TreeNode root) {
        if (root == null)
            return 0;
        int left = helper(root.left), right = helper(root.right);
        int len = 0, ret = 0;
        if (root.left != null && root.val == root.left.val) {
            len += left + 1;
            ret = ret > left + 1 ? ret : left + 1;
        }
        if (root.right != null && root.val == root.right.val) {
            len += right + 1;
            ret = ret > right + 1 ? ret : right + 1;
        }
        max = max > len ? max : len;
        return ret;
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
    int max = 0;
    
    public int longestUnivaluePath(TreeNode root) {
        helper(root, 0);
        return max;
    }
    
    public int helper(TreeNode root, int val) {
        if (root == null)
            return 0;
        int left = helper(root.left, root.val), right = helper(root.right, root.val);
        max = max > left + right ? max : left + right;
        if (root.val == val) 
            return left > right ? left + 1 : right + 1;
        else
            return 0;
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
    public int longestUnivaluePath(TreeNode root) {
        if (root == null)
            return 0;
        int sub1 = longestUnivaluePath(root.left);
        int sub2 = longestUnivaluePath(root.right);
        int sub = sub1 > sub2 ? sub1 : sub2;
        int cand = helper(root.left, root.val) + helper(root.right, root.val);
        return sub > cand ? sub : cand;
    }
    
    public int helper(TreeNode root, int val) {
        if (root == null || root.val != val)
            return 0;
        int left = helper(root.left, root.val), right = helper(root.right, root.val);
        return left > right ? left + 1 : right + 1;
    }
}
