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
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        helper(root, 0, res);
        return res;
    }
    
    public void helper(TreeNode root, int depth, List<Integer> res) {
        if (root == null)
            return;
        if (res.size() < depth + 1)
            res.add(root.val);
        helper(root.right, depth + 1, res);
        helper(root.left, depth + 1, res);
    }
}
