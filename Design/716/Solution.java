// index each element in stack 
class MaxStack {

    /** initialize your data structure here. */
    int[] array;
    boolean[] invalid;
    int[] stack;
    int index, ptr;
    Queue<Integer> queue;
    public MaxStack() {
        array = new int[10000];
        invalid = new boolean[10000];
        stack = new int[10000];
        queue = new PriorityQueue<>(new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                if (array[i2] != array[i1])
                    return array[i2] - array[i1];
                else return i2 - i1;
            }
        });
        index = 0;
        ptr = 0;
    }
    
    public void push(int x) {
        stack[ptr++] = index;
        array[index] = x;
        queue.offer(index);
        index++;
    }
    
    public int pop() {
        while (invalid[stack[ptr-1]])
            ptr--;
        int res = array[stack[ptr-1]];
        invalid[stack[--ptr]] = true;
        return res;
    }
    
    public int top() {
        while (invalid[stack[ptr-1]])
            ptr--;
        return array[stack[ptr-1]];
    }
    
    public int peekMax() {
        while (invalid[queue.peek()])
            queue.poll();
        return array[queue.peek()];
    }
    
    public int popMax() {
        while (invalid[queue.peek()])
            queue.poll();
        int res = array[queue.peek()];
        invalid[queue.poll()] = true;
        return res;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */


// when popping the max, pop until find the max, then push back 
class MaxStack {

    /** initialize your data structure here. */
    int[] stack, max;
    int ptr;
    public MaxStack() {
        stack = new int[10000];
        max = new int[10000];
        ptr = 0;
    }
    
    public void push(int x) {
        stack[ptr] = x;
        max[ptr] = ptr == 0 ? x : Math.max(max[ptr-1], x);
        ptr++;
    }
    
    public int pop() {
        return stack[--ptr];
    }
    
    public int top() {
        return stack[ptr-1];
    }
    
    public int peekMax() {
        return max[ptr-1];
    }
    
    public int popMax() {
        int target = peekMax();
        List<Integer> temp = new LinkedList<>();
        while (stack[ptr-1] != target)
            temp.add(pop());
        pop();
        for (int i = temp.size()-1; i >= 0; i--)
            push(temp.get(i));
        return target;
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */


// treemap plus doubly linked list 
class MaxStack {

    /** initialize your data structure here. */
    Node head, tail;
    TreeMap<Integer, List<Node>> map;
    public MaxStack() {
        head = new Node(null, null, -1);
        tail = new Node(null, null, -1);
        head.next = tail;
        tail.prev = head;
        map = new TreeMap<>();
    }
    
    public void push(int x) {
        Node node = new Node(tail.prev, tail, x);
        tail.prev.next = node;
        tail.prev = node;
        map.putIfAbsent(x, new LinkedList<>());
        map.get(x).add(node);
    }
    
    public int pop() {
        Node node = tail.prev;
        tail.prev = node.prev;
        node.prev.next = tail;
        List<Node> list = map.get(node.val);
        list.remove(list.size()-1);
        if (list.isEmpty())
            map.remove(node.val);
        return node.val;
    }
    
    public int top() {
        return tail.prev.val;
    }
    
    public int peekMax() {
        return map.lastKey();
    }
    
    public int popMax() {
        int res = map.lastKey();
        List<Node> list = map.get(res);
        Node node = list.get(list.size()-1);
        list.remove(list.size()-1);
        if (list.isEmpty())
            map.remove(res);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        return res;
    }
    
    class Node {
        Node prev, next;
        int val;
        public Node(Node prev, Node next, int val) {
            this.prev = prev;
            this.next = next;
            this.val = val;
        }
    }
}

/**
 * Your MaxStack object will be instantiated and called as such:
 * MaxStack obj = new MaxStack();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.peekMax();
 * int param_5 = obj.popMax();
 */
