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
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return helper(root, 0).root;
    }
    
    public Res helper(TreeNode root, int depth) {
        if (root == null) {
            return new Res(null, depth - 1);
        } else {
            Res left = helper(root.left, depth + 1);
            Res right = helper(root.right, depth + 1);
            if (left.depth == right.depth)
                return new Res(root, left.depth);
            else
                return left.depth > right.depth ? left : right;
        }
    }
    
    class Res {
        int depth;
        TreeNode root;
        public Res(TreeNode root, int depth) {
            this.depth = depth;
            this.root = root;
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
    TreeNode res;
    int maxdepth;
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        helper(root, 0);
        return res;
    }
    
    public int helper(TreeNode root, int depth) {
        if (root == null) {
            maxdepth = maxdepth > depth - 1 ? maxdepth : depth  - 1;
            return depth - 1;
        } else {
            int left = helper(root.left, depth + 1);
            int right = helper(root.right, depth + 1);
            if (left == right && left == maxdepth)
                res = root;
            return left > right ? left : right;
        }
    }
}
