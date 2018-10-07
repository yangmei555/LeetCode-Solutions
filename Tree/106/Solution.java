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
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            map.put(inorder[i], i);
        return helper(inorder, postorder, postorder.length-1, 0, inorder.length-1, map);
    }
    
    public TreeNode helper(int[] in, int[] post, int index, int instart, int inend, 
                                                                Map<Integer, Integer> map) {
        if (instart > inend)
            return null;
        TreeNode root = new TreeNode(post[index]);
        int pos = map.get(post[index]);
        root.left = helper(in, post, index-1-inend+pos, instart, pos-1, map);
        root.right = helper(in, post, index-1, pos+1, inend, map);
        return root;
    }
}


// 遇 stop 则返 
class Solution {
    int inindex, postindex;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        inindex = inorder.length-1;
        postindex = postorder.length-1;
        return helper(inorder, postorder, Integer.MAX_VALUE);
    }
    
    public TreeNode helper(int[] inorder, int[] postorder, int stop) {
        if (inindex < 0 || inorder[inindex] == stop) {
            inindex--;
            return null;
        }
        TreeNode root = new TreeNode(postorder[postindex]);
        postindex--;
        root.right = helper(inorder, postorder, root.val);
        root.left = helper(inorder, postorder, stop);
        return root;
    }
}


// iterative version using stack 
class Solution {
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        TreeNode[] stack = new TreeNode[inorder.length];
        int index = 0;
        TreeNode pre = null, root = null;
        for (int i = postorder.length-1, j = inorder.length-1; i >= 0; i--) {
            while (index != 0 && stack[index-1].val == inorder[j]) {
                pre = stack[--index];
                j--;
            }
            TreeNode node = new TreeNode(postorder[i]);
            if (pre == null) {
                if (root == null)
                    root = node;
                if (index != 0)
                    stack[index-1].right = node;
            } else {
                pre.left = node;
                pre = null;
            }
            stack[index++] = node;
        }
        return root;
    }
}
