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
    public int closestValue(TreeNode root, double target) {
        if (root.left == null && root.right == null)
            return root.val;
        if (Math.abs(root.val - target) <= 1e-6)
            return root.val;
        int left = root.left == null ? 0 : closestValue(root.left, target);
        int right = root.right == null ? 0 : closestValue(root.right, target);
        double cand1 = Math.abs(root.val - target), 
                cand2 = root.left == null ? Double.MAX_VALUE : Math.abs(left - target), 
                cand3 = root.right == null ? Double.MAX_VALUE : Math.abs(right - target);
        if (cand1 < cand2) {
            return cand1 < cand3 ? root.val : right;
        } else {
            return cand2 < cand3 ? left : right;
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
    public int closestValue(TreeNode root, double target) {
        if (root.left == null && root.right == null)
            return root.val;
        if (Math.abs(root.val - target) <= 1e-6 || 
                root.val < target && root.right == null || 
                root.val > target && root.left == null)
            return root.val;
        int cand = root.val < target ? closestValue(root.right, target) : 
                                        closestValue(root.left, target);
        double dist1 = Math.abs(root.val - target), dist2 = Math.abs(cand - target);
        return dist1 < dist2 ? root.val : cand;
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
    public int closestValue(TreeNode root, double target) {
        int res = root.val;
        TreeNode node = root;
        while (node != null) {
            double dist = Math.abs(node.val - target);
            if (dist <= 1e-6)
                return node.val;
            res = dist < Math.abs(res - target) ? node.val : res;
            if (target > node.val)
                node = node.right;
            else
                node = node.left;
        }
        return res;
    }
}
