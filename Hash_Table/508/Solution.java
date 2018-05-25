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
    public int[] findFrequentTreeSum(TreeNode root) {
        if (root == null)
            return new int[0];
        Map<Integer, Integer> map = new HashMap<>();
        helper(root, map);
        int[] res = new int[map.size()];
        int index = 0, freq = Integer.MIN_VALUE;
        for (int i : map.keySet()) {
            if (map.get(i) > freq) {
                res[0] = i;
                index = 1;
                freq = map.get(i);
            } else if (map.get(i) == freq) {
                res[index++] = i;
            }
        }
        return Arrays.copyOf(res, index);
    }
    public int helper(TreeNode root, Map<Integer, Integer> map) {
        int left = root.left == null ? 0 : helper(root.left, map);
        int right = root.right == null ? 0 : helper(root.right, map);
        int sum = left + right + root.val;
        map.put(sum, map.getOrDefault(sum, 0) + 1);
        return sum;
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
    int max = 0;
    public int[] findFrequentTreeSum(TreeNode root) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> list = new LinkedList<>();
        helper(root, map, list);
        int[] res = new int[list.size()];
        int index = 0;
        for (int i : list) 
            res[index++] = i;
        return res;
    }
    public int helper(TreeNode root, Map<Integer, Integer> map, List<Integer> list) {
        if (root == null)
            return 0;
        int sum = helper(root.left, map, list) + helper(root.right, map, list) + root.val;
        int freq = map.getOrDefault(sum, 0);
        map.put(sum, freq + 1);
        if (freq + 1 > max) {
            list.clear();
            list.add(sum);
            max = freq + 1;
        } else if (freq + 1 == max) {
            list.add(sum);
        }
        return sum;
    }
}
