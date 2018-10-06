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
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return root;
        if (root.val == p.val || root.val == q.val)
            return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null)
            return root;
        else
            return left == null ? right : left;
    }
}


// this solution can deal with the cases when p or q is not inside the given tree , 
// but the above solution can not 
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        Element ret = helper(root, p, q);
        return ret.found == 2 ? ret.node : null;
    }
    
    public Element helper(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null)
            return null;
        Element left = helper(root.left, p, q), right = helper(root.right, p, q);
        boolean flag = root.val == p.val || root.val == q.val;
        if (left != null && right != null) {
            return new Element(root, 2);
        } else if (left != null && right == null) {
            return flag ? new Element(root, 2) : left;
        } else if (left == null && right != null) {
            return flag ? new Element(root, 2) : right;
        } else {
            return flag ? new Element(root, 1) : null;
        }
    }
    
    class Element {
        TreeNode node;
        int found;
        public Element(TreeNode node, int found) {
            this.node = node;
            this.found = found;
        }
    }
}
