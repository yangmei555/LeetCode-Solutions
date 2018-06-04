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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        int len = nums.length-1;
        return helper(nums, 0, len);
    }
    
    public TreeNode helper(int[] nums, int start, int end) {
        if (start > end)
            return null;
        int max = start;
        for (int i = start+1; i <= end; i++)
            if (nums[i] > nums[max])
                max = i;
        TreeNode root = new TreeNode(nums[max]);
        root.left = helper(nums, start, max-1);
        root.right = helper(nums, max+1, end);
        return root;
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
    public TreeNode constructMaximumBinaryTree(int[] nums) {
        TreeNode[] stack = new TreeNode[nums.length];
        int index = 0;
        TreeNode root = null;
        for (int i = 0; i < nums.length; i++) {
            int pop = nums.length-1;
            while (index > 0 && stack[index-1].val < nums[i])
                pop = --index;
            TreeNode node = new TreeNode(nums[i]);
            if (index == 0)
                root = node;
            else
                stack[index-1].right = node;
            node.left = stack[pop];
            stack[index++] = node;
        }
        return root;
    }
}
