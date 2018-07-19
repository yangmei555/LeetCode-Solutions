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
    public int rob(TreeNode root) {
        Map<TreeNode, Integer> map = new HashMap<>();
        return helper(root, map);
    }
    
    public int helper(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null)
            return 0;
        if (map.containsKey(root))
            return map.get(root);
        if (root.left == null && root.right == null)
            return root.val;

        int sum1 = root.val + 
        (root.left == null ? 0 : helper(root.left.left, map) + helper(root.left.right, map)) + 
        (root.right == null ? 0 : helper(root.right.left, map) + helper(root.right.right, map));

        int sum2 = helper(root.left, map) + helper(root.right, map);
        int res = sum1 > sum2 ? sum1 : sum2;
        map.put(root, res);
        return res;
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
    public int rob(TreeNode root) {
        int[] res = helper(root);
        return res[0] > res[1] ? res[0] : res[1];
    }
    
    public int[] helper(TreeNode root) {
        if (root == null)
            return new int[]{0, 0};
        if (root.left == null && root.right == null)
            return new int[]{root.val, 0};
        int[] left = helper(root.left), right = helper(root.right);
        int[] res = new int[2];
        res[0] = root.val + left[1] + right[1];
        res[1] = (left[0] > left[1] ? left[0] : left[1]) + 
                    (right[0] > right[1] ? right[0] : right[1]);
        return res;
    }
}
