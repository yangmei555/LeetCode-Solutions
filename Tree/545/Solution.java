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
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        else
            res.add(root.val);
        helper(res, root.left, true, true);
        helper(res, root.right, true, false);
        return res;
    }
    
    public void helper(List<Integer> list, TreeNode root, boolean isBoundary, boolean isLeft) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
            return;
        }
        if (isBoundary && isLeft)
            list.add(root.val);
        
        if (root.right == null && isBoundary)
            helper(list, root.left, true, isLeft);
        else
            helper(list, root.left, isLeft ? isBoundary : false, isLeft);
        
        if (root.left == null && isBoundary)
            helper(list, root.right, true, isLeft);
        else
            helper(list, root.right, isLeft ? false : isBoundary, isLeft);
        
        if (isBoundary && !isLeft)
            list.add(root.val);
    }
}
