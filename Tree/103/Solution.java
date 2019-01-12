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
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        int level = 0;
        Deque<TreeNode> deque = new ArrayDeque<>();
        if (root != null)
            deque.offerFirst(root);
        while (!deque.isEmpty()) {
            List<Integer> list = new LinkedList<>();
            for (int s = deque.size(); s > 0; s--) {
                TreeNode node = level % 2 == 0 ? deque.pollFirst() : deque.pollLast();
                list.add(node.val);
                if (level % 2 == 1) {
                    if (node.right != null)
                        deque.offerFirst(node.right);
                    if (node.left != null)
                        deque.offerFirst(node.left);
                } else {
                    if (node.left != null)
                        deque.offerLast(node.left);
                    if (node.right != null)
                        deque.offerLast(node.right);
                }
            }
            res.add(list);
            level++;
        }
        return res;
    }
}
