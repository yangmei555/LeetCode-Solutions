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
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        helper(res, root);
        return res;
    }
    
    public void helper(List<Integer> res, Node root) {
        if (root == null)
            return;
        res.add(root.val);
        if (root.children != null) {
            for (Node n : root.children)
                helper(res, n);
        }
    }
}


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
class Solution {
    public List<Integer> preorder(Node root) {
        List<Integer> res = new LinkedList<>();
        if (root == null)
            return res;
        Stack<Node> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(node.val);
            if (node.children != null) {
                for (int i = node.children.size()-1; i >= 0; i--)
                    stack.push(node.children.get(i));
            }
        }
        return res;
    }
}
