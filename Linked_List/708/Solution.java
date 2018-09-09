/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newhead = new Node(insertVal, null);
            newhead.next = newhead;
            return newhead;
        }
        Node node = head, max = head;
        while (true) {
            if (node.val > max.val)
                max = node;
            if (node.val <= insertVal && insertVal < node.next.val) {
                node.next = new Node(insertVal, node.next);
                return head;
            }
            node = node.next;
            if (node == head)
                break;
        }
        max.next = new Node(insertVal, max.next);
        return head;
    }
}


/*
// Definition for a Node.
class Node {
    public int val;
    public Node next;

    public Node() {}

    public Node(int _val,Node _next) {
        val = _val;
        next = _next;
    }
};
*/
class Solution {
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node newhead = new Node(insertVal, null);
            newhead.next = newhead;
            return newhead;
        }
        Node node = head;
        while (node.next != head) {
            // a more complicated judging condition 
            if (node.val <= insertVal && insertVal < node.next.val || 
                (node.val > node.next.val && (node.val <= insertVal || insertVal <= node.next.val))) {

                node.next = new Node(insertVal, node.next);
                return head;
            }
            node = node.next;
        }
        node.next = new Node(insertVal, node.next);
        return head;
    }
}
