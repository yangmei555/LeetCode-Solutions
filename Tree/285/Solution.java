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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p.right != null) {
            p = p.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        return helper(root, p);
    }
    boolean found = false;
    public TreeNode helper(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (root == p) {
            found = true;
            return null;
        }
        TreeNode left = helper(root.left, p);
        if (left != null)
            return left;
        if (left == null && found)
            return root;
        return helper(root.right, p);
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null)
            return null;
        if (p.val >= root.val) {
            return inorderSuccessor(root.right, p);
        } else {
            TreeNode node = inorderSuccessor(root.left, p);
            return node == null ? root : node;
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode node = root;
        if (p.right != null) {
            p = p.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        Stack<TreeNode> stack = new Stack<>();
        while (node != null || !stack.isEmpty()) {
            if (node == p)
                break;
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                node = stack.pop();
                node = node.right;
            }
        }
        return stack.isEmpty() ? null : stack.pop();
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode node = root;
        if (p.right != null) {
            p = p.right;
            while (p.left != null)
                p = p.left;
            return p;
        }
        while (node != null) {
            if (node == p)
                break;
            if (node.left == null) {
                node = node.right;
            } else {
                TreeNode temp = node.left;
                while (temp.right != null && temp.right != node)
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                } else {
                    temp.right = null;
                    node = node.right;
                }
            }
        }
        return node.right;
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
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        TreeNode res = null;
        while (root != null) {
            if (root.val <= p.val) {
                root = root.right;
            } else {
                res = root;
                root = root.left;
            }
        }
        return res;
    }
}
