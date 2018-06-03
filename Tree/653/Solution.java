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
    public boolean findTarget(TreeNode root, int k) {
        List<Integer> nums = new ArrayList<>();
        helper(root, nums);
        int left = 0, right = nums.size()-1;
        while (left < right) {
            int l = nums.get(left), r = nums.get(right);
            if (l + r == k)
                return true;
            else if (l + r < k)
                left++;
            else
                right--;
        }
        return false;
    }
    
    public void helper(TreeNode root, List<Integer> nums) {
        if (root == null)
            return;
        helper(root.left, nums);
        nums.add(root.val);
        helper(root.right, nums);
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
    public boolean findTarget(TreeNode root, int k) {
        Set<Integer> set = new HashSet<>();
        return helper(set, root, k);
    }
    
    public boolean helper(Set<Integer> set, TreeNode root, int k) {
        if (root == null)
            return false;
        if (set.contains(k-root.val))
            return true;
        set.add(root.val);
        return helper(set, root.left, k) || helper(set, root.right, k);
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
    public boolean findTarget(TreeNode root, int k) {
        return helper(root, root, k);
    }
    
    public boolean helper(TreeNode root, TreeNode node, int target) {
        if (node == null)
            return false;
        return contains(root, node, target-node.val) || helper(root, node.left, target) || 
                helper(root, node.right, target);
    }
    
    public boolean contains(TreeNode root, TreeNode except, int num) {
        if (root == null)
            return false;
        if (root.val == num)
            return root != except;
        else if (root.val < num)
            return contains(root.right, except, num);
        else
            return contains(root.left, except, num);
    }
}
