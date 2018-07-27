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
    public List<Integer> postorder(Node root) {
        List<Integer> res = new LinkedList<>();
        helper(root, res);
        return res;
    }
    
    public void helper(Node root, List<Integer> res) {
        if (root == null)
            return;
        for (Node n : root.children)
            helper(n, res);
        res.add(root.val);
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
    public List<Integer> postorder(Node root) {
        Stack<Node> stack = new Stack<>();
        List<Integer> res = new LinkedList<>();
        if (root != null)
            stack.push(root);
        while (!stack.isEmpty()) {
            Node node = stack.pop();
            res.add(0, node.val);
            for (Node n : node.children)
                stack.push(n);
        }
        // Collections.reverse(res);
        return res;
    }
}
