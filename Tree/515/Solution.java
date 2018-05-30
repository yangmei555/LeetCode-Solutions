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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null)
            return res;
        else
            queue.offer(root);
        TreeNode node = null;
        while (!queue.isEmpty()) {
            int cand = Integer.MIN_VALUE, size = queue.size();
            for (int i = 0; i < size; i++) {
                node = queue.poll();
                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);
                cand = cand > node.val ? cand : node.val;
            }
            res.add(cand);
        }
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
    public List<Integer> largestValues(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        helper(res, root, 0);
        return res;
    }
    
    public void helper(List<Integer> res, TreeNode root, int level) {
        if (root == null)
            return;
        if (res.size() < level + 1)
            res.add(root.val);
        else if (root.val > res.get(level))
            res.set(level, root.val);
        helper(res, root.left, level + 1);
        helper(res, root.right, level + 1);
    }
}
