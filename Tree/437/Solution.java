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
    int ans = 0;
    public int pathSum(TreeNode root, int sum) {
        if (root == null)
            return 0;
        if (root.val == sum)
            ans++;
        if (root.left != null) {
            pathSum(root.left, sum);
            helper(root.left, sum - root.val);
        }
        if (root.right != null) {
            pathSum(root.right, sum);
            helper(root.right, sum - root.val);
        }
        return ans;
    }
    
    public void helper(TreeNode root, int sum) {
        if (root == null)
            return;
        if (root.val == sum)
            ans++;
        if (root.left != null)
            helper(root.left, sum - root.val);
        if (root.right != null)
            helper(root.right, sum - root.val);
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
    int ans = 0;
    public int pathSum(TreeNode root, int sum) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        helper(root, sum, 0, map);
        return ans;
    }
    
    public void helper(TreeNode root, int sum, int addup, Map<Integer, Integer> map) {
        if (root == null)
            return;
        addup += root.val;
        ans += map.getOrDefault(addup - sum, 0);
        map.put(addup, map.getOrDefault(addup, 0) + 1);
        helper(root.left, sum, addup, map);
        helper(root.right, sum, addup, map);
        map.put(addup, map.get(addup) - 1);        
    }
}
