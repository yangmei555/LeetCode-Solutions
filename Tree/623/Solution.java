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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newnode = new TreeNode(v);
            newnode.left = root;
            return newnode;
        }
        helper(root, v, d, 1);
        return root;
    }
    
    public void helper(TreeNode root, int v, int d, int level) {
        if (root == null)
            return;
        if (level == d-1) {
            TreeNode newl = new TreeNode(v), newr = new TreeNode(v);
            newl.left = root.left;
            newr.right = root.right;
            root.left = newl;
            root.right = newr;
        } else {
            helper(root.left, v, d, level + 1);
            helper(root.right, v, d, level + 1);
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
    public TreeNode addOneRow(TreeNode root, int v, int d) {
        if (d == 1) {
            TreeNode newnode = new TreeNode(v);
            newnode.left = root;
            return newnode;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        int level = 1;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (level == d - 1) {
                    TreeNode newl = new TreeNode(v), newr = new TreeNode(v);
                    newl.left = node.left;
                    newr.right = node.right;
                    node.left = newl;
                    node.right = newr;
                } else {
                    if (node.left != null)
                        queue.offer(node.left);
                    if (node.right != null)
                        queue.offer(node.right);
                }
            }
            level++;
        }
        return root;
    }
}
