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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)
            return list;
        TreeNode cur = root, pre = null;
        while (cur != null) {
            if (cur.left != null) {
                pre = cur.left;
                while (pre.right != null && pre.right != cur)
                    pre = pre.right;
                if (pre.right == null) {
                    pre.right = cur;
                    list.add(cur.val);
                    cur = cur.left;
                } else {
                    pre.right = null;
                    cur = cur.right;
                }
            } else {
                list.add(cur.val);
                cur = cur.right;
            }
        }
        return list;
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)
            return list;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (stack.isEmpty() == false) {
            TreeNode node = stack.pop();
            if (node != null) {
                list.add(node.val);
                stack.add(node.right);
                stack.add(node.left);
            }
        }
        return list;
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
    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        if (root == null)
            return list;
        TreeNode cur = root, pre = null;
        while (cur != null) {
            list.add(cur.val);
            if (cur.left != null) {
                pre = cur.left;
                while (pre.right != null)
                    pre = pre.right;
                pre.right = cur.right;
                cur = cur.left;
            } else {
                cur = cur.right;
            }
        }
        return list;
    }
}
