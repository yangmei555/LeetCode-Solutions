class MyLinkedList {

    /** Initialize your data structure here. */
    class Node {
        int val;
        Node prev, next;
        public Node (int val) {
            this.val = val;
        }
    }
    Node head, tail;
    int len;
    public MyLinkedList() {
        head = new Node(-1);
        tail = new Node(-1);
        head.next = tail;
        tail.prev = head;
        len = 0;
    }
    
    /** Get the value of the index-th node in the linked list. 
        If the index is invalid, return -1. */
    public int get(int index) {
        if (index >= 0 && index < len) {
            Node cur = head;
            while (index-- >= 0)
                cur = cur.next;
            return cur.val;
        } else {
            return -1;
        }
    }
    
    /** Add a node of value val before the first element of the linked list. 
        After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        Node node = new Node(val);
        node.prev = head;
        node.next = head.next;
        node.next.prev = node;
        head.next = node;
        len++;
    }
    
    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        Node node = new Node(val);
        node.prev = tail.prev;
        node.next = tail;
        node.prev.next = node;
        tail.prev = node;
        len++;
    }
    
    /** Add a node of value val before the index-th node in the linked list. 
        If index equals to the length of linked list, the node will be appended to the end of 
        linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if (index >= 0 && index <= len) {
            Node cur = head;
            while (index-- > 0) 
                cur = cur.next;
            Node node = new Node(val);
            node.prev = cur;
            node.next = cur.next;
            cur.next = node;
            node.next.prev = node;
            len++;
        }
    }
    
    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if (index >= 0 && index < len) {
            Node cur = head;
            while (index-- >= 0)
                cur = cur.next;
            cur.prev.next = cur.next;
            cur.next.prev = cur.prev;
            len--;
        }
    }
}

/**
 * Your MyLinkedList object will be instantiated and called as such:
 * MyLinkedList obj = new MyLinkedList();
 * int param_1 = obj.get(index);
 * obj.addAtHead(val);
 * obj.addAtTail(val);
 * obj.addAtIndex(index,val);
 * obj.deleteAtIndex(index);
 */
