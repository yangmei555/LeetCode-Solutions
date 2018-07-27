/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        Node dummy = new Node();
        Node tail = helper(root, dummy);
        if (dummy.right != null) {
            dummy.right.left = tail;
            tail.right = dummy.right;
        }
        return dummy.right;
    }
    
    public Node helper(Node root, Node pre) {
        if (root == null)
            return pre;
        Node left = helper(root.left, pre);
        left.right = root;
        root.left = left;
        return helper(root.right, root);
    }
}


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        Stack<Node> stack = new Stack<>();
        Node node = root, head = null, pre = null;
        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node cur = stack.pop();
                if (head == null)
                    head = cur;
                if (pre != null) {
                    pre.right = cur;
                    cur.left = pre;
                }
                pre = cur;
                node = cur.right;
            }
        }
        if (head != null) {
            head.left = pre;
            pre.right = head;
        }
        return head;
    }
}


/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution {
    public Node treeToDoublyList(Node root) {
        Node node = root, pre = null, head = null, tail = null;
        while (node != null) {
            if (node.right == null)
                tail = node;
            if (node.left == null) {
                if (pre != null) {
                    pre.right = node;
                    node.left = pre;
                } else {
                    head = node;
                }
                pre = node;
                node = node.right;
            } else {
                Node temp = node.left;
                while (temp.right != null && temp.right != node) 
                    temp = temp.right;
                if (temp.right == null) {
                    temp.right = node;
                    node = node.left;
                } else {
                    node.left = temp;
                    pre = node;
                    node = node.right;
                }
            }
        }
        if (head != null && tail != null) {
            head.left = tail;
            tail.right = head;
        }
        return head;
    }
}
