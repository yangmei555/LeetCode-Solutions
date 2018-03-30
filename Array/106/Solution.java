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
        if (inorder == null || inorder.length == 0 || postorder == null ||
                postorder.length == 0)
            return null;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++) {
            hashMap.put(inorder[i], i);
        }
        return help(inorder, postorder, 0, inorder.length-1, 0,
                postorder.length-1, hashMap);
    }

    public TreeNode help(int[] in, int[] pos, int instart, int inend, int posstart, int posend, 
                         HashMap<Integer, Integer> hashMap) {
        if (instart > inend)
            return null;
        int k = pos[posend];
        int i = hashMap.get(k);
        TreeNode node = new TreeNode(k);
        node.left = help(in, pos, instart, i - 1, posstart, posstart+i-1-instart, 
                hashMap);
        node.right = help(in, pos, i+1, inend, posend-inend+i, posend - 1, 
                hashMap);
        return node;
        
    }
}
