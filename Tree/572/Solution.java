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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        if (s == null || t == null) 
            return t == null;
        if (s.val == t.val) {
            if (isIdentical(s.left, t.left) && isIdentical(s.right, t.right))
                return true;
        } 
        return isSubtree(s.left, t) || isSubtree(s.right, t);
    }
    
    public boolean isIdentical(TreeNode s, TreeNode t) {
        if (s == null || t == null)
            return s == null && t == null;
        return s.val == t.val && isIdentical(s.left, t.left) && isIdentical(s.right, t.right);
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
    public boolean isSubtree(TreeNode s, TreeNode t) {
        return helper(s, t, true);
    }
    
    public boolean helper(TreeNode s, TreeNode t, boolean flag) {
        if (s == null || t == null)
            return s == null && t == null;
        if (s.val == t.val) {
            if (helper(s.left, t.left, false) && helper(s.right, t.right, false))
                return true;
        } else if (!flag) {
            return false;
        }
        return helper(s.left, t, true) || helper(s.right, t, true);
    }
}
