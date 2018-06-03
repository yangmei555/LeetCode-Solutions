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
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, List<TreeNode>> map = new HashMap<>();
        helper(root, map);
        List<TreeNode> res = new LinkedList<>();
        for (String str : map.keySet()) {
            if (map.get(str).size() != 1)
                res.add(map.get(str).get(0));
        }
        return res;
    }
    
    public String helper(TreeNode root, Map<String, List<TreeNode>> map) {
        if (root == null)
            return "";
        String left = helper(root.left, map), right = helper(root.right, map);
        String str = root.val + "," + left + "," + right;
        map.putIfAbsent(str, new LinkedList<>());
        map.get(str).add(root);
        return str;
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
    int iden = 0;
    public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
        Map<String, Integer> map1 = new HashMap<>();
        Map<Integer, Integer> map2 = new HashMap<>();
        List<TreeNode> res = new LinkedList<>();
        helper(root, map1, map2, res);
        return res;
    }
    
    public int helper(TreeNode root, Map<String, Integer> map1, Map<Integer, Integer> map2, 
                        List<TreeNode> res) {
        if (root == null)
            return 0;
        int left = helper(root.left, map1, map2, res), right = helper(root.right, map1, map2, res);
        String str = root.val + "," + left + "," + right;
        int n = 0;
        if (map1.containsKey(str)) {
            n = map1.get(str);
        } else {
            n = ++iden;
            map1.put(str, n);
        }
        map2.put(n, map2.getOrDefault(n, 0) + 1);
        if (map2.get(n) == 2)
            res.add(root);
        return n;
    }
}
