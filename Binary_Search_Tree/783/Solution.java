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
    public int minDiffInBST(TreeNode root) {
        int pre = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
        TreeNode node = root;
        while (node != null) {
            if (node.left == null) {
                if (pre != Integer.MIN_VALUE)
                    res = res < node.val - pre ? res : node.val - pre;
                pre = node.val;
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
                    res = res < node.val - pre ? res : node.val - pre;
                    pre = node.val;
                    node = node.right;
                }
            }
        }
        return res;
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
    int pre = Integer.MIN_VALUE, res = Integer.MAX_VALUE;
    
    public int minDiffInBST(TreeNode root) {
        if (root == null)
            return Integer.MAX_VALUE;
        int left = minDiffInBST(root.left), self = Integer.MAX_VALUE;
        if (pre != Integer.MIN_VALUE)
            self = root.val - pre;
        pre = root.val;
        int right = minDiffInBST(root.right);
        return Math.min(Math.min(left, self), right);
    }
}
