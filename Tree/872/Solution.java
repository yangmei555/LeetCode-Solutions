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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list1 = new ArrayList<>(), list2 = new ArrayList<>();
        helper(root1, list1);
        helper(root2, list2);
        if (list1.size() != list2.size())
            return false;
        for (int i = 0; i < list1.size(); i++) {
            if (list1.get(i) != list2.get(i))
                return false;
        }
        return true;
    }
    
    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        helper(root.right, list);
        if (root.left == null && root.right == null)
            list.add(root.val);
    }
}


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
    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> list = new ArrayList<>();
        helper1(root1, list);
        int index = helper2(root2, list, 0);
        return index == list.size();
    }
    
    public void helper1(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        if (root.left == null && root.right == null) {
            list.add(root.val);
        } else {
            helper1(root.left, list);
            helper1(root.right, list);
        }
    }
    
    public int helper2(TreeNode root, List<Integer> list, int index) {
        if (root == null)
            return index;
        if (root.left == null && root.right == null) {
            if (index >= list.size() || root.val != list.get(index))
                return Integer.MAX_VALUE;
            else
                return index + 1;
        } else {
            int pos = helper2(root.left, list, index);
            return helper2(root.right, list, pos);
        }
    }
}
