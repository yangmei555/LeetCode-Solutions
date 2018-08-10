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
    public void recoverTree(TreeNode root) {
        TreeNode node = root, pre = null, target1 = null, target2 = null;
        while (node != null) {
            if (node.left == null) {
                if (pre != null) {
                    if (pre.val > node.val) {
                        if (target1 == null) 
                            target1 = pre;
                        target2 = node;
                    }
                }
                pre = node;
                node = node.right;
            } else {
                TreeNode run = node.left;
                while (run.right != null && run.right != node)
                    run = run.right;
                if (run.right == null) {
                    run.right = node;
                    node = node.left;
                } else {
                    run.right = null;
                    if (pre.val > node.val) {
                        if (target1 == null) 
                            target1 = pre;
                        target2 = node;
                    }
                    pre = node;
                    node = node.right;
                }
            }
        }
        int temp = target1.val;
        target1.val = target2.val;
        target2.val = temp;
        return;
    }
}
