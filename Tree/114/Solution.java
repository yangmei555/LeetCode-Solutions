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
    public void flatten(TreeNode root) {
        helper(root);
    }
    
    public Ret helper(TreeNode root) {
        if (root == null)
            return new Ret(null, null);
        if (root.left == null && root.right == null)
            return new Ret(root, root);
        Ret left = root.left == null ? null : helper(root.left);
        Ret right = root.right == null ? null : helper(root.right);
        root.left = null;
        if (left != null) {
            root.left = null;
            root.right = left.head;
            left.tail.right = right == null ? null : right.head;
        }
        return right == null ? new Ret(root, left.tail) : new Ret(root, right.tail);
    }
    
    class Ret {
        TreeNode head, tail;
        public Ret(TreeNode head, TreeNode tail) {
            this.head = head;
            this.tail = tail;
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
    public void flatten(TreeNode root) {
        TreeNode node = root;
        while (node != null) {
            if (node.left != null) {
                TreeNode temp = node.left;
                while (temp.right != null)
                    temp = temp.right;
                temp.right = node.right;
                node.right = node.left;
                node.left = null;
                node = node.right;
            } else {
                node = node.right;
            }
        }
    }
}
