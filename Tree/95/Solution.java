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
    public List<TreeNode> generateTrees(int n) {
        List<TreeNode>[] res = new List[n+1];
        res[0] = new LinkedList<>();
        if (n == 0)
            return res[0];
        res[0].add(null);
        for (int i = 1; i <= n; i++) {
            res[i] = new LinkedList<>();
            for (int j = 1; j <= i; j++) {
                List<TreeNode> added = helper(res[i-j], j);
                for (TreeNode left : res[j-1]) {
                    for (TreeNode right : added) {
                        TreeNode root = new TreeNode(j);
                        root.left = left;
                        root.right = right;
                        res[i].add(root);
                    }
                }
            }
        }
        return res[n];
    }
    
    public List<TreeNode> helper(List<TreeNode> list, int add) {
        List<TreeNode> res = new LinkedList<>();
        for (TreeNode t : list) 
            res.add(process(t, add));
        return res;
    }
    
    public TreeNode process(TreeNode root, int add) {
        if (root == null)
            return null;
        TreeNode newroot = new TreeNode(root.val + add);
        newroot.left = process(root.left, add);
        newroot.right = process(root.right, add);
        return newroot;
    }
}
