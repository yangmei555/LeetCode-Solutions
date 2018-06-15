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
    public int countNodes(TreeNode root) {
        TreeNode node1 = root, node2 = root;
        int lh = 0, rh = 0;
        while (node1 != null || node2 != null) {
            if (node1 != null) {
                node1 = node1.left; 
                lh++;
            }
            if (node2 != null) {
                node2 = node2.right;
                rh++;
            }
        }
        if (lh == rh) {
            return (1 << lh) - 1;
        } else {
            return 1 + countNodes(root.left) + countNodes(root.right);
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
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode node1 = root.left, node2 = root.left;
        int lh = 0, rh = 0;
        while (node1 != null || node2 != null) {
            if (node1 != null) {
                node1 = node1.left; 
                lh++;
            }
            if (node2 != null) {
                node2 = node2.right;
                rh++;
            }
        }
        if (lh == rh) {
            return (1 << lh) + countNodes(root.right);
        } else {
            return (1 << rh) + countNodes(root.left);
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
    public int countNodes(TreeNode root) {
        if (root == null)
            return 0;
        TreeNode node = root.right;
        int ref = 0, res = 0;
        while (node != null) {
            node = node.right;
            ref++;
        }
        while (root != null) {
            node = root.left;
            int h = 0;
            while (node != null) {
                node = node.right;
                h++;
            }
            if (h == ref) {
                res += (1 << ref);
                root = root.left;
            } else {
                res += (1 << h);
                root = root.right;
            }
            ref--;
        }
        return res;
    }
}
