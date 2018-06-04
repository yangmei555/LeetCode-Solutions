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
    int max = 0;
    
    public int widthOfBinaryTree(TreeNode root) {
        List<Integer> width = new LinkedList<>();
        helper(root, width, 0, 0);
        return max;
    }
    
    public void helper(TreeNode root, List<Integer> width, int x, int level) {
        if (root == null)
            return;
        if (width.size() < level + 1)
            width.add(x);
        max = max > x-width.get(level)+1 ? max : x-width.get(level)+1;
        helper(root.left, width, 2*x, level+1);
        helper(root.right, width, 2*x+1, level+1);
    }
}
