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
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean meetsNull = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                if (meetsNull)
                    return false;
                else
                    queue.offer(node.left);
            } else {
                meetsNull = true;
            }
            if (node.right != null) {
                if (meetsNull)
                    return false;
                else
                    queue.offer(node.right);
            } else {
                meetsNull = true;
            }
        }
        return true;
    }
}


// more concise version 
class Solution {
    public boolean isCompleteTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean meetsNull = false;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node != null) {
                if (meetsNull)
                    return false;
                queue.offer(node.left);
                queue.offer(node.right);
            } else {
                meetsNull = true;
            }
        }
        return true;
    }
}
