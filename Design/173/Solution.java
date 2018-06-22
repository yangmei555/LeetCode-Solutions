/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    
    Stack<TreeNode> stack = new Stack<>();
    public BSTIterator(TreeNode root) {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return !stack.isEmpty();
    }

    /** @return the next smallest number */
    public int next() {
        TreeNode node = stack.pop();
        int res = node.val;
        node = node.right;
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    
    TreeNode node;
    public BSTIterator(TreeNode root) {
        node = root;
        if (node != null) {
            while (node.left != null) {
                TreeNode temp = node.left;
                while (temp.right != null)
                    temp = temp.right;
                temp.right = node;
                node = node.left;
            }
        }
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return node != null;
    }

    /** @return the next smallest number */
    public int next() {
        int res = node.val;
        node = node.right;
        if (node != null) {
            while (node.left != null) {
                TreeNode temp = node.left;
                while (temp.right != null && temp.right != node)
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                } else {
                    temp.right = null;
                    break;
                }
            }
        }
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */


/**
 * Definition for binary tree
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */

public class BSTIterator {
    
    TreeNode node;
    public BSTIterator(TreeNode root) {
        node = root;
    }

    /** @return whether we have a next smallest number */
    public boolean hasNext() {
        return node != null;
    }

    /** @return the next smallest number */
    public int next() {
        int res = -1;
        if (node != null) {
            while (node.left != null) {
                TreeNode temp = node.left;
                while (temp.right != null && temp.right != node)
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                } else {
                    temp.right = null;
                    break;
                }
            }
            res = node.val;
            node = node.right;
        }
        return res;
    }
}

/**
 * Your BSTIterator will be called like this:
 * BSTIterator i = new BSTIterator(root);
 * while (i.hasNext()) v[f()] = i.next();
 */
