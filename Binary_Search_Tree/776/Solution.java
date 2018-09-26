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
    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode[] res = new TreeNode[2];
        if (root == null)
            return res;
        if (root.val == V) {
            res[1] = root.right;
            root.right = null;
            res[0] = root;
        } else if (root.val < V) {
            TreeNode[] ret = splitBST(root.right, V);
            root.right = ret[0];
            res[0] = root;
            res[1] = ret[1];
        } else {
            TreeNode[] ret = splitBST(root.left, V);
            root.left = ret[1];
            res[0] = ret[0];
            res[1] = root;
        }
        return res;
    }
}


// can combine the '==' and '<' cases into one case 
class Solution {
    public TreeNode[] splitBST(TreeNode root, int V) {
        TreeNode[] res = new TreeNode[2];
        if (root == null)
            return res;
        if (root.val <= V) {
            TreeNode[] ret = splitBST(root.right, V);
            root.right = ret[0];
            res[0] = root;
            res[1] = ret[1];
        } else {
            TreeNode[] ret = splitBST(root.left, V);
            root.left = ret[1];
            res[0] = ret[0];
            res[1] = root;
        }
        return res;
    }
}
