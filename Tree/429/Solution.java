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
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        if (root != null) 
            queue.offer(root);
        // add elements when dequeue
        while (!queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                level.add(node.val);
                for (Node n : node.children) 
                    queue.offer(n);
            }
            res.add(level);
        }
        return res;
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
    public List<List<Integer>> levelOrder(Node root) {
        Queue<Node> queue = new LinkedList<>();
        List<List<Integer>> res = new LinkedList<>();
        // add elements when enqueue
        if (root != null) {
            queue.offer(root);
            res.add(Arrays.asList(root.val));
        }
        while (!queue.isEmpty()) {
            List<Integer> level = new LinkedList<>();
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                for (Node n : node.children) {
                    queue.offer(n);
                    level.add(n.val);
                }
            }
            if (!level.isEmpty())
                res.add(level);
        }
        return res;
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
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        helper(root, 0, res);
        return res;
    }
    
    public void helper(Node root, int depth, List<List<Integer>> res) {
        if (root == null)
            return;
        if (depth + 1 > res.size())
            res.add(new LinkedList<>());
        res.get(depth).add(root.val);
        for (Node n : root.children)
            helper(n, depth + 1, res);
    }
}
