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
    public int findSecondMinimumValue(TreeNode root) {
        return helper(root, root.val);
    }
    
    public int helper(TreeNode root, int val) {
        if (root.val != val)
            return root.val;
        if (root.left == null && root.right == null) 
            return -1;
        int left = helper(root.left, val);
        int right = helper(root.right, val);
        if (left != -1 && right != -1) {
            return left < right ? left : right;
        } else {
            return left == -1 ? right : left;
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
    public int findSecondMinimumValue(TreeNode root) {
        if (root.left == null && root.right == null)
            return -1;
        int left = root.left.val == root.val ? findSecondMinimumValue(root.left) : root.left.val;
        int right = root.right.val == root.val ? findSecondMinimumValue(root.right) : root.right.val;
        if (left == -1 || right == -1) {
            return left > right ? left : right;
        } else {
            return left < right ? left : right;
        }
    }
}
