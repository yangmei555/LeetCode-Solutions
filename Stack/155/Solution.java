class MinStack {

    /** initialize your data structure here. */
    int min;
    Stack<Integer> data;
    public MinStack() {
        min = Integer.MAX_VALUE;
        data = new Stack<>();
    }
    
    public void push(int x) {
        if (x <= min) {
            data.push(min);
            min = x;
        }
        data.push(x);
    }
    
    public void pop() {
        int res = data.pop();
        if (res == min) {
            min = data.pop();
        }
    }
    
    public int top() {
        return data.peek();
    }
    
    public int getMin() {
        return min;
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */


// use a singly linked list 
class MinStack {

    /** initialize your data structure here. */
    Node head;
    public MinStack() {
        head = new Node(0, Integer.MAX_VALUE);
    }
    
    public void push(int x) {
        Node node = new Node(x, 0);
        node.min = Math.min(x, head.min);
        node.next = head;
        head = node;
    }
    
    public void pop() {
        head = head.next;
    }
    
    public int top() {
        return head.val;
    }
    
    public int getMin() {
        return head.min;
    }
    
    class Node {
        int val, min;
        Node next;
        public Node(int val, int min) {
            this.val = val;
            this.min = min;
            this.next = null;
        }
    }
}

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
