/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val,List<Node> _children) {
        val = _val;
        children = _children;
    }
};
*/
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Codec {

    // Encodes an n-ary tree to a binary tree.
    public TreeNode encode(Node root) {
        if (root == null)
            return null;
        TreeNode tree = new TreeNode(root.val);
        if (root.children.isEmpty())
            return tree;
        TreeNode cur = encode(root.children.get(0));
        tree.left = cur;
        for (int i = 1; i < root.children.size(); i++) {
            cur.right = encode(root.children.get(i));
            cur = cur.right;
        }
        return tree;
    }

    // Decodes your binary tree to an n-ary tree.
    public Node decode(TreeNode root) {
        if (root == null)
            return null;
        List<Node> children = new LinkedList<>();
        Node tree = new Node(root.val, children);
        TreeNode cur = root.left;
        while (cur != null) {
            tree.children.add(decode(cur));
            cur = cur.right;
        }
        return tree;
    }
}

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.decode(codec.encode(root));
