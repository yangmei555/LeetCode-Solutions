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
    int res = 0;
    public int longestConsecutive(TreeNode root) {
        helper(root);
        return res;
    }
    
    public Res helper(TreeNode root) {
        if (root == null)
            return new Res(0, 0);
        Res left = helper(root.left);
        Res right = helper(root.right);
        int dec1 = root.left != null && root.left.val+1 == root.val ? left.dec+1 : 1;
        int dec2 = root.right != null && root.right.val+1 == root.val ? right.dec+1 : 1;
        int inc1 = root.left != null && root.left.val == root.val+1 ? left.inc+1 : 1;
        int inc2 = root.right != null && root.right.val == root.val+1 ? right.inc+1 : 1;
        int cand = dec1 + inc2 > dec2 + inc1 ? dec1 + inc2 : dec2 + inc1;
        res = res > cand-1 ? res : cand-1;
        return new Res(inc1 > inc2 ? inc1 : inc2, dec1 > dec2 ? dec1 : dec2);
    }
    
    class Res {
        int inc;
        int dec;
        public Res(int inc, int dec) {
            this.inc = inc;
            this.dec = dec;
        }
    }
}
