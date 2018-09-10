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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(root, res);
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> res) {
        if (root == null)
            return;
        helper(root.left, res);
        helper(root.right, res);
        res.add(root.val);
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
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                if (stack.peek().right != null) {
                    node = stack.peek().right;
                } else {
                    while (!stack.isEmpty()) {
                        node = stack.pop();
                        res.add(node.val);
                        if (stack.isEmpty() || stack.peek().right != node)
                            break;
                    }
                    node = null;
                }
            }
        }
        return res;
    }
}


// another way of using stack 
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (!stack.isEmpty() || node != null) {
            if (node != null) {
                stack.push(node);
                res.add(0, node.val);
                node = node.right;
            } else {
                node = stack.pop().left;
            }
        }
        return res;
    }
}


// another way of using stack 
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Stack<TreeNode> stack = new Stack<>();
        if (root != null)
            stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            res.add(0, node.val);
            if (node.left != null)
                stack.push(node.left);
            if (node.right != null)
                stack.push(node.right);
        }
        return res;
    }
}


// Morris traversal 
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        TreeNode node = root;
        while (node != null) {
            if (node.right == null) {
                res.add(0, node.val);
                node = node.left;
            } else {
                TreeNode temp = node.right;
                while (temp.left != null && temp.left != node)
                    temp = temp.left;
                if (temp.left == null) {
                    res.add(0, node.val);
                    temp.left = node;
                    node = node.right;
                } else {
                    temp.left = null;
                    node = node.left;
                }
            }
        }
        return res;
    }
}


// another way of Morris traversal 
class Solution {
    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        TreeNode node = root;
        while (node != null) {
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
                    res.addAll(helper(node.left));
                    node = node.right;
                }
            }
        }
        res.addAll(helper(root));
        return res;
    }
    
    // reverse the encountered values 
    public List<Integer> helper(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        while (root != null) {
            res.add(0, root.val);
            root = root.right;
        }
        return res;
    }
}
