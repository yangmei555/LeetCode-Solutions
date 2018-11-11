class MyCircularQueue {

    /** Initialize your data structure here. Set the size of the queue to be k. */
    int[] queue;
    int p1, p2, len, size;
    public MyCircularQueue(int k) {
        queue = new int[k];
        len = k;
        p1 = 0;
        p2 = 0;
        size = 0;
    }
    
    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (!isFull()) {
            queue[p2] = value;
            p2 = (p2 + 1) % len;
            size++;
            return true;
        } else {
            return false;
        }
    }
    
    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (!isEmpty()) {
            p1 = (p1 + 1) % len;
            size--;
            return true;
        } else {
            return false;
        }
    }
    
    /** Get the front item from the queue. */
    public int Front() {
        return isEmpty() ? -1 : queue[p1];
    }
    
    /** Get the last item from the queue. */
    public int Rear() {
        return isEmpty() ? -1 : queue[(p2+len-1) % len];
    }
    
    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return size == len;
    }
}

/**
 * Your MyCircularQueue object will be instantiated and called as such:
 * MyCircularQueue obj = new MyCircularQueue(k);
 * boolean param_1 = obj.enQueue(value);
 * boolean param_2 = obj.deQueue();
 * int param_3 = obj.Front();
 * int param_4 = obj.Rear();
 * boolean param_5 = obj.isEmpty();
 * boolean param_6 = obj.isFull();
 */
