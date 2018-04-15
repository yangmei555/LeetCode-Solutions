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
    public int[] findMode(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        int[] res = new int[map.keySet().size()];
        int index = 0, max = 0;
        for (int i : map.keySet()) {
            if (map.get(i) > max) {
                max = map.get(i);
                index = 0;
                res[index] = i;
                index++;
            } else if (map.get(i) == max) {
                res[index] = i;
                index++;
            }
        }
        return Arrays.copyOf(res, index);
    }
    public void helper(TreeNode root, Map<Integer, Integer> map) {
        if (root == null)
            return;
        map.put(root.val, map.getOrDefault(root.val, 0) + 1);
        helper(root.left, map);
        helper(root.right, map);
    }
}


class Solution {
    Integer var = null;
    Integer count = 1, max = 1;
    public int[] findMode(TreeNode root) {
        List<Integer> list = new LinkedList<>();
        helper(root, list);
        int[] res = new int[list.size()];
        int index = 0;
        for (int i : list) {
            res[index] = i;
            index++;
        }
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        if (var == null) {
            var = root.val;
            count = 1;
            max = 1;
            list.add(var);
        } else {
            if (var == root.val) {
                count++;
            } else {
                var = root.val;
                count = 1;
            }
            if (count == max){
                list.add(var);
            } else if (count > max) {
                max = count;
                var = root.val;
                list.clear();
                list.add(var);
            }
        }
        helper(root.right, list);
    }
}
