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
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return helper(preorder, inorder, 0, 0, inorder.length-1, map);
    }
    
    public TreeNode helper(int[] pre, int[] in, int index, int instart, int inend, 
                                                                Map<Integer, Integer> map) {
        if (instart > inend)
            return null;
        TreeNode root = new TreeNode(pre[index]);
        int pos = map.get(pre[index]);
        root.left = helper(pre, in, index+1, instart, pos-1, map);
        root.right = helper(pre, in, index+1+pos-instart, pos+1, inend, map);
        return root;
    }
}


// 遇 stop 则返 
class Solution {
    int preindex, inindex;
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        preindex = 0;
        inindex = 0;
        return helper(preorder, inorder, Integer.MAX_VALUE);
    }
    
    public TreeNode helper(int[] preorder, int[] inorder, int stop) {
        if (inindex >= inorder.length || inorder[inindex] == stop) {
            inindex++;
            return null;
        }
        TreeNode root = new TreeNode(preorder[preindex]);
        preindex++;
        root.left = helper(preorder, inorder,  root.val);
        root.right = helper(preorder, inorder, stop);
        return root;
    }
}


// iterative version using stack 
class Solution {
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        TreeNode[] stack = new TreeNode[preorder.length];
        int index = 0;
        TreeNode pre = null, root = null;
        for (int i = 0, j = 0; i < preorder.length; i++) {
            while (index > 0 && stack[index-1].val == inorder[j]) {
                pre = stack[--index];
                j++;
            }
            TreeNode node = new TreeNode(preorder[i]);
            if (pre == null) {
                if (root == null)
                    root = node;
                if (index != 0)
                    stack[index-1].left = node;
            } else {
                pre.right = node;
                pre = null;
            }
            stack[index++] = node;
        }
        return root;
    }
}
