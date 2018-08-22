/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        if (head == null)
            return null;
        Node node = head, next = head.next;
        head.next = flatten(head.child);
        if (head.next != null)
            head.next.prev = head;
        while (node.next != null)
            node = node.next;
        node.next = flatten(next);
        if (node.next != null)
            node.next.prev = node;
        head.child = null;
        return head;
    }
}


// does not need to loop once more 
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        Segment res = helper(head);
        return res.head;
    }
    
    public Segment helper(Node head) {
        if (head == null) {
            return new Segment(null, null);
        } else {
            Segment s1 = helper(head.child), s2 = helper(head.next);
            head.child = null;
            if (s1.head != null) {
                head.next = s1.head;
                s1.head.prev = head;
                s1.tail.next = s2.head;
                if (s2.head != null)
                    s2.head.prev = s1.tail;
            } 
            Node tail = s2.tail != null ? s2.tail : (s1.tail != null ? s1.tail : head);
            return new Segment(head, tail);
        }
    }
    
    class Segment {
        Node head;
        Node tail;
        public Segment(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }
}


// just return the not-null tail is OK  
/*
// Definition for a Node.
class Node {
    public int val;
    public Node prev;
    public Node next;
    public Node child;

    public Node() {}

    public Node(int _val,Node _prev,Node _next,Node _child) {
        val = _val;
        prev = _prev;
        next = _next;
        child = _child;
    }
};
*/
class Solution {
    public Node flatten(Node head) {
        helper(head);
        return head;
    }
    
    public Node helper(Node head) {
        if (head == null)
            return null;
        Node child = helper(head.child), next = helper(head.next);
        if (child != null) {
            Node temp = head.next;
            head.next = head.child;
            head.child.prev = head;
            head.child = null;
            child.next = temp;
            if (temp != null)
                temp.prev = child;
        }
        return next != null ? next : (child != null ? child : head);
    }
}
