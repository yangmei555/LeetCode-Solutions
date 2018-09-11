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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> list = new LinkedList<>(), res = new LinkedList<>();
        helper(root, list);
        int left = -1, right = -1;
        for (int i = 0; i < list.size()-1; i++) {
            if (target >= list.get(i) && target < list.get(i+1)) {
                left = i;
                break;
            }
        }
        if (left == -1 && target >= list.get(list.size()-1)) 
            left = list.size()-1;
        right = left + 1;
        while (res.size() < k) {
            if (left >= 0 && right < list.size()) {
                if (Math.abs(list.get(left)-target) < Math.abs(list.get(right)-target))
                    res.add(0, list.get(left--));
                else
                    res.add(list.get(right++));
            } else if (left >= 0) {
                res.add(0, list.get(left--));
            } else {
                res.add(list.get(right++));
            }
        }
        return res;
    }
    
    public void helper(TreeNode root, List<Integer> list) {
        if (root == null)
            return;
        helper(root.left, list);
        list.add(root.val);
        helper(root.right, list);
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
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> res = new LinkedList<>();
        helper(root, res, target, k);
        return res;
    }
    
    public boolean helper(TreeNode root, List<Integer> res, double target, int k) {
        if (root == null)
            return false;
        if (helper(root.left, res, target, k))
            return true;
        if (res.size() == k) {
            if (Math.abs(res.get(0)-target) <= Math.abs(root.val-target))
                return true;
            else 
                res.remove(0);
        } 
        res.add(root.val);
        return helper(root.right, res, target, k);
    }
}


// use predecessor and successor to achieve O(k*lgN) 
class Solution {
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        Stack<TreeNode> pre = new Stack<>(), suc = new Stack<>();
        TreeNode node = root;
        while (node != null) {
            if (node.val <= target) {
                pre.push(node);
                node = node.right;
            } else {
                suc.push(node);
                node = node.left;
            }
        }
        List<Integer> res = new LinkedList<>();
        while (res.size() != k) {
            if (pre.isEmpty()) {
                res.add(nextSuc(suc));
            } else if (suc.isEmpty()) {
                res.add(0, nextPre(pre));
            } else {
                double cand1 = target - pre.peek().val;
                double cand2 = suc.peek().val - target;
                if (cand1 < cand2) 
                    res.add(0, nextPre(pre));
                else 
                    res.add(nextSuc(suc));
            }
        }
        return res;
    }
    
    public int nextPre(Stack<TreeNode> pre) {
        TreeNode node = pre.pop();
        int res = node.val;
        node = node.left;
        while (node != null) {
            pre.push(node);
            node = node.right;
        }
        return res;
    }
    
    public int nextSuc(Stack<TreeNode> suc) {
        TreeNode node = suc.pop();
        int res = node.val;
        node = node.right;
        while (node != null) {
            suc.push(node);
            node = node.left;
        }
        return res;
    }
}
