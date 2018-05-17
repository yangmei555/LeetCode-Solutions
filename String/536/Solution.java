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
    
    int i = 0;
    public TreeNode str2tree(String s) {
        if (s.length() == 0)
            return null;
        char[] ch = s.toCharArray();
        return helper(ch);
    }
    
    public TreeNode helper(char[] ch) {
        int num = 0, sign = 1;
        TreeNode root = null;
        while (i < ch.length) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
                i++;
            } else if (ch[i] == '-') {
                i++;
                sign = -1;
            } else if (ch[i] == '(') {
                i++;
                if (root == null) {
                    root = new TreeNode(sign * num);
                    root.left = helper(ch);
                } else {
                    root.right = helper(ch);
                }
            } else if (ch[i] == ')') {
                i++;
                return root == null ? new TreeNode(sign * num) : root;
            }
        }
        return root == null ? new TreeNode(sign * num) : root;
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
    public TreeNode str2tree(String s) {
        if (s.length() == 0)
            return null;
        char[] ch = s.toCharArray();
        return helper(ch, 0).node;
    }
    
    public Res helper(char[] ch, int i) {
        int num = 0, sign = 1;
        TreeNode root = null;
        while (i < ch.length) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
            } else if (ch[i] == '-') {
                sign = -1;
            } else if (ch[i] == '(') {
                Res res = helper(ch, i+1);
                if (root == null) {
                    root = new TreeNode(sign * num);
                    root.left = res.node;
                } else {
                    root.right = res.node;
                }
                i = res.index;
            } else if (ch[i] == ')') {
                return root == null ? new Res(new TreeNode(sign * num), i) : new Res(root, i);
            }
            i++;
        }
        return root == null ? new Res(new TreeNode(sign * num), i) : new Res(root, i);
    }
    
    class Res {
        TreeNode node;
        int index;
        public Res(TreeNode node, int index) {
            this.node = node;
            this.index = index;
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
    public TreeNode str2tree(String s) {
        if (s.length() == 0)
            return null;
        char[] ch = s.toCharArray();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = new TreeNode(0);
        int num = 0, sign = 1;
        for (int i = 0; i < ch.length; i++) {
            if (ch[i] >= '0' && ch[i] <= '9') {
                num = num * 10 + ch[i] - '0';
            } else if (ch[i] == '-') {
                sign = -1;
            } else if (ch[i] == '(') {
                stack.push(node);
                node = new TreeNode(0);
                if (stack.peek().left == null) {
                    stack.peek().left = node;
                    stack.peek().val = num * sign;
                    sign = 1;
                    num = 0;
                } else {
                    stack.peek().right = node;
                }
            } else if (ch[i] == ')') {
                if (node.left == null) {
                    node.val = num * sign;
                    sign = 1;
                    num = 0;
                }
                node = stack.pop();
            }
        }
        if (node.left == null)
            node.val = num * sign;
        return node;
    }
}
