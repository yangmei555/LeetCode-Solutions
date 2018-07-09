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
    Integer last = null; 
    public int getMinimumDifference(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int left = getMinimumDifference(root.left), self = Integer.MAX_VALUE;
        if (last != null) 
            self = root.val < last ? last - root.val : root.val - last;
        last = root.val;
        int right = getMinimumDifference(root.right);
        return Math.min(Math.min(left, right), self);
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
    public int getMinimumDifference(TreeNode root) {
        int last = Integer.MAX_VALUE, res = Integer.MAX_VALUE;
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                if (last != Integer.MAX_VALUE) 
                    res = Math.min(res, node.val - last); 
                last = node.val;
                node = node.right;
            } else {
                TreeNode left = node.left;
                while (left.right != null && left.right != node)
                    left = left.right;
                if (left.right == null) {
                    left.right = node;
                    node = node.left;
                } else {
                    left.right = null;
                    res = Math.min(res, node.val - last); 
                    last = node.val;
                    node = node.right;
                }
            }
        }
        return res;
    }
}
