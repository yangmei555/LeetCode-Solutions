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
