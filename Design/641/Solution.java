class MyCircularDeque {

    /** Initialize your data structure here. Set the size of the deque to be k. */
    int[] deque;
    int p1, p2, size, len;
    public MyCircularDeque(int k) {
        len = k;
        size = 0;
        p1 = 0;
        p2 = 0;
        deque = new int[len];
    }
    
    /** Adds an item at the front of Deque. Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (isFull()) {
            return false;
        } else {
            p1 = (p1 + len - 1) % len;
            deque[p1] = value;
            size++;
            return true;
        }
    }
    
    /** Adds an item at the rear of Deque. Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (isFull()) {
            return false;
        } else {
            deque[p2] = value;
            p2 = (p2 + 1) % len;
            size++;
            return true;
        }
    }
    
    /** Deletes an item from the front of Deque. Return true if the operation is successful. */
    public boolean deleteFront() {
        if (isEmpty()) {
            return false;
        } else {
            p1 = (p1 + 1) % len;
            size--;
            return true;
        }
    }
    
    /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
    public boolean deleteLast() {
        if (isEmpty()) {
            return false;
        } else {
            p2 = (p2 + len - 1) % len;
            size--;
            return true;
        }
    }
    
    /** Get the front item from the deque. */
    public int getFront() {
        return isEmpty() ? -1 : deque[p1];
    }
    
    /** Get the last item from the deque. */
    public int getRear() {
        return isEmpty() ? -1 : deque[(p2+len-1) % len];
    }
    
    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }
    
    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == len;
    }
}

/**
 * Your MyCircularDeque object will be instantiated and called as such:
 * MyCircularDeque obj = new MyCircularDeque(k);
 * boolean param_1 = obj.insertFront(value);
 * boolean param_2 = obj.insertLast(value);
 * boolean param_3 = obj.deleteFront();
 * boolean param_4 = obj.deleteLast();
 * int param_5 = obj.getFront();
 * int param_6 = obj.getRear();
 * boolean param_7 = obj.isEmpty();
 * boolean param_8 = obj.isFull();
 */
