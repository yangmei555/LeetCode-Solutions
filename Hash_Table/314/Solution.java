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
    
    int min = 0, max = 0;
    
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        List<List<Integer>> aux = new LinkedList<>();
        helper(root, res, aux, 0, 0);
        return res;
    }
    
    public void helper(TreeNode root, List<List<Integer>> res, List<List<Integer>> aux, 
                            int column, int level) {
        if (root == null)
            return;
        int len = res.size();
        min = min < column ? min : column;
        max = max > column ? max : column;
        if (len < max - min + 1) {
            if (column < 0) {
                res.add(0, new LinkedList<>());
                aux.add(0, new LinkedList<>());
            } else {
                res.add(new LinkedList<>());
                aux.add(new LinkedList<>());
            }
        }
        int listindex = column - min;
        List<Integer> list = res.get(listindex);
        List<Integer> auxlist = aux.get(listindex);
        int i = 0;
        while (i < list.size()) {
            if (auxlist.get(i) > level)
                break;
            i++;
        }
        list.add(i, root.val);
        auxlist.add(i, level);
        helper(root.left, res, aux, column - 1, level + 1);
        helper(root.right, res, aux, column + 1, level + 1);
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
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new LinkedList<>();
        if (root == null)
            return res;
        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> aux = new LinkedList<>();
        queue.offer(root);
        aux.offer(0);
        int min = 0;
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            int index = aux.poll();
            if (index < min) {
                res.add(0, new LinkedList<>());
                min = index;
            } else if (index - min + 1 > res.size()) {
                res.add(new LinkedList<>());
            }
            List<Integer> list = res.get(index - min);
            list.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
                aux.offer(index - 1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                aux.offer(index + 1);
            }
        }
        return res;
    }
}
