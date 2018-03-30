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
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < inorder.length; i++)
            hashMap.put(inorder[i], i);
        return help(preorder, inorder, 0, 0, inorder.length - 1, hashMap);
    }

    public TreeNode help(int[] preorder, int[] inorder, int prestart, int instart, int inend, HashMap<Integer, Integer> hashMap)      {
        if (preorder.length == prestart || instart > inend)
            return null;
        TreeNode treeNode = new TreeNode(preorder[prestart]);
        int index = hashMap.get(treeNode.val);
        treeNode.left = help(preorder, inorder, prestart + 1, instart, index - 1, hashMap);
        treeNode.right = help(preorder, inorder, index + 1 + prestart - instart, index + 1, inend, hashMap);
        return treeNode;
    }
}
