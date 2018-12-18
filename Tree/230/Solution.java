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
    int order = 1;
    public int kthSmallest(TreeNode root, int k) {
        return helper(root, k);
    }
    
    public Integer helper(TreeNode root, int k) {
        if (root == null)
            return null;
        Integer i = helper(root.left, k);
        if (i != null)
            return i;
        if (order == k)
            return root.val;
        order++;
        return helper(root.right, k);
    }
}


// Morris traversal 
class Solution {
    public int kthSmallest(TreeNode root, int k) {
        int order = 0;
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) {
                order++;
                if (order == k)
                    return cur.val;
                cur = cur.right;
            } else {
                TreeNode temp = cur.left;
                while (temp.right != null && temp.right != cur)
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = cur;
                    cur = cur.left;
                } else {
                    temp.right = null;
                    order++;
                    if (order == k)
                        return cur.val;
                    cur = cur.right;
                }
            }
        }
        return -1;
    }
}
