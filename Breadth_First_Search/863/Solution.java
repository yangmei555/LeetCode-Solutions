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
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        Set<Integer> visited = new HashSet<>();
        Map<TreeNode, TreeNode> map = new HashMap<>();
        helper(root, map, null);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target.val);
        int dist = 0;
        while (!queue.isEmpty()) {
            if (dist++ == K)
                break;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null && visited.add(node.left.val))
                    queue.offer(node.left);
                if (node.right != null && visited.add(node.right.val))
                    queue.offer(node.right);
                if (map.containsKey(node) && visited.add(map.get(node).val))
                    queue.offer(map.get(node));
            }
        }
        List<Integer> res = new LinkedList<>();
        for (TreeNode t : queue)
            res.add(t.val);
        return res;
    }
    
    public void helper(TreeNode root, Map<TreeNode, TreeNode> map, TreeNode parent) {
        if (root != null) { 
            if (parent != null)
                map.put(root, parent);
            helper(root.left, map, root);
            helper(root.right, map, root);
        }
    }
}


// much faster than the above solution 
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
    List<Integer> res = new LinkedList<>();
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        helper1(root, target, K);
        return res;
    }
    
    public int helper1(TreeNode root, TreeNode target, int K) {
        if (root == null) {
            return -1;
        } else if (root == target) {
            helper2(target, K);
            return 0;
        } else {
            int left = helper1(root.left, target, K), right = helper1(root.right, target, K);
            if (left != -1) {
                if (K == left + 1)
                    res.add(root.val);
                else if (K > left + 1)
                    helper2(root.right, K-left-2);
                return left + 1;
            } else if (right != -1) {
                if (K == right + 1)
                    res.add(root.val);
                else if (K > right + 1)
                    helper2(root.left, K-right-2);
                return right + 1;
            } else {
                return -1;
            }
        }
    }
    
    public void helper2(TreeNode root, int K) {
        if (K < 0)
            return;
        if (root != null) {
            if (K == 0) {
                res.add(root.val);
            } else {
                helper2(root.left, K-1);
                helper2(root.right, K-1);
            }
        }
    }
}
