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
    int res = 0;
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return res;
        helper(root, root.val-1, 0);
        return res;
    }
    
    public void helper(TreeNode root, int preval, int consecutive) {
        if (root == null)
            return;
        if (root.val == preval + 1) {
            consecutive++;
            res = res > consecutive ? res : consecutive;
            helper(root.left, root.val, consecutive);
            helper(root.right, root.val, consecutive);
        } else {
            helper(root.left, root.val, 1);
            helper(root.right, root.val, 1);
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
    public int longestConsecutive(TreeNode root) {
        if (root == null)
            return 0;
        return helper(root, root.val-1, 0);
    }
    
    public int helper(TreeNode root, int preval, int consec) {
        if (root == null)
            return 0;
        consec = root.val == preval + 1 ? consec + 1 : 1;
        int left = helper(root.left, root.val, consec);
        int right = helper(root.right, root.val, consec);
        int ret = left > right ? left : right;
        return consec > ret ? consec : ret;
    }
}
