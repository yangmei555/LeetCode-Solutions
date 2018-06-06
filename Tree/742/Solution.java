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
    boolean found = false;
    public int findClosestLeaf(TreeNode root, int k) {
        Ret res = helper(root, k);
        return res.val;
    }
    
    public Ret helper(TreeNode root, int k) {
        if (root == null)
            return null;
        if (root.left == null && root.right == null) {
            if (root.val == k)
                found = true;
            return new Ret(root.val, 0, 0, root.val == k ? true : false);
        } else if (root.right == null) {
            Ret left = helper(root.left, k);
            if (!left.found)
                left.len1++;
            else
                left.len2++;
            if (root.val == k) 
                left.found = found = true;
            return left;
        } else if (root.left == null) {
            Ret right = helper(root.right, k);
            if (!right.found)
                right.len1++;
            else
                right.len2++;
            if (root.val == k)
                right.found = found = true;
            return right;
        }
        boolean flag1 = found;
        Ret left = helper(root.left, k);
        boolean flag2 = found;
        Ret right = helper(root.right, k);
        boolean flag3 = found;
        if (root.val == k)
            found = true;
        if (flag1 == flag3) {
            if (!left.found)
                left.len1++;
            else
                left.len2++;
            if (!right.found)
                right.len1++;
            else
                right.len2++;
            return left.len1 < right.len1 ? left : right;
        } else {
            if (flag2) {
                left.len2++;
                right.len1++;
                int cand1 = left.len1, cand2 = left.len2 + right.len1;
                return new Ret(cand1 < cand2 ? left.val : right.val, 
                                cand1 < cand2 ? cand1 : cand2, left.len2, flag3);
            } else {
                left.len1++;
                right.len2++;
                int cand1 = right.len2 + left.len1, cand2 = right.len1;
                return new Ret(cand1 < cand2 ? left.val : right.val, 
                                cand1 < cand2 ? cand1 : cand2, right.len2, flag3);
            }
        }
    }
    
    class Ret {
        // len1 : when k is not founded, the distance between a leaf and the current root
        //        when k is founded, the distance between a leaf and k
        // len2 : when k is not founded, 0
        //        when k is founded, the distance between k and the current root
        int val, len1, len2;
        boolean found;
        public Ret(int val, int len1, int len2, boolean found) {
            this.val = val;
            this.len1 = len1;
            this.len2 = len2;
            this.found = found;
        }
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
    public int findClosestLeaf(TreeNode root, int k) {
        Ret res = helper(root, k);
        return res.val;
    }
    
    public Ret helper(TreeNode root, int k) {
        if (root == null)
            return new Ret(0, 10000, 0, false);   // because at most 1000 nodes
        if (root.left == null && root.right == null) 
            return new Ret(root.val, 0, 0, root.val == k ? true : false);
        Ret left = helper(root.left, k), right = helper(root.right, k);
        if (left.found)
            left.len2++;
        else
            left.len1++;
        if (right.found)
            right.len2++;
        else 
            right.len1++;
        if (!left.found && !right.found) {
            left.found = root.val == k ? true : false;
            right.found = left.found;
            return left.len1 < right.len1 ? left : right;
        } else if (left.found) {
            right.len1 += left.len2;
            left.len1 = left.len1 < right.len1 ? left.len1 : right.len1;
            left.val = left.len1 == right.len1 ? right.val : left.val;
            return left;
        } else if (right.found) {
            left.len1 += right.len2;
            right.len1 = left.len1 < right.len1 ? left.len1 : right.len1;
            right.val = left.len1 == right.len1 ? left.val : right.val;
            return right;
        } else {
            // found k in two places
            return null;
        }
    }
    
    class Ret {
        // len1 : when k is not founded, the distance between a leaf and the current root
        //        when k is founded, the distance between a leaf and k
        // len2 : when k is not founded, 0
        //        when k is founded, the distance between k and the current root
        int val, len1, len2;
        boolean found;
        public Ret(int val, int len1, int len2, boolean found) {
            this.val = val;
            this.len1 = len1;
            this.len2 = len2;
            this.found = found;
        }
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
    public int findClosestLeaf(TreeNode root, int k) {
        Map<TreeNode, TreeNode> map = new HashMap<>();
        TreeNode target = helper(root, k, map);
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(target);
        visited.add(target);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null)
                return node.val;
            if (node.left != null && visited.add(node.left))
                queue.offer(node.left);
            if (node.right != null && visited.add(node.right))
                queue.offer(node.right);
            if (map.containsKey(node) && visited.add(map.get(node)))
                queue.offer(map.get(node));
        }
        return 0;
    }
    
    public TreeNode helper(TreeNode root, int k, Map<TreeNode, TreeNode> map) {
        if (root == null)
            return null;
        if (root.left != null) 
            map.put(root.left, root);
        if (root.right != null)
            map.put(root.right, root);
        TreeNode left = helper(root.left, k, map);
        TreeNode right = helper(root.right, k, map);
        if (root.val == k)
            return root;
        return left == null ? right : left;
    }
}
