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
    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> res = new LinkedList<>();
        List<Integer> path = new ArrayList<>();
        helper(root, sum, 0, res, path);
        return res;
    }
    
    public void helper(TreeNode root, int sum, int addup, List<List<Integer>> res, 
                        List<Integer> path) {
        if (root == null)
            return;
        path.add(root.val);
        addup += root.val;
        if (root.left == null && root.right == null) {
            if (addup == sum)
                res.add(new LinkedList<>(path));
        } else {
            helper(root.left, sum, addup, res, path);
            helper(root.right, sum, addup, res, path);
        }
        path.remove(path.size()-1);
    }
}
