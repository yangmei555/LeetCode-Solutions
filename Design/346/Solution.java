class MovingAverage {

    /** Initialize your data structure here. */
    Queue<Integer> queue;
    int size, sum;
    public MovingAverage(int size) {
        queue = new LinkedList<>();
        this.size = size;
        sum = 0;
    }
    
    public double next(int val) {
        sum += val;
        queue.offer(val);
        if (queue.size() > size)
            sum -= queue.poll();
        return (sum + .0) / queue.size();
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */


class MovingAverage {

    /** Initialize your data structure here. */
    int[] nums;
    int size, n, sum, ptr;
    public MovingAverage(int size) {
        this.size = size;
        n = 0;
        sum = 0;
        ptr = 0;
        nums = new int[size];
    }
    
    public double next(int val) {
        if (n < size)
            n++;
        sum += val;
        sum -= nums[ptr];
        nums[ptr] = val;
        ptr = (ptr + 1) % size;
        return (sum + .0) / n;
    }
}

/**
 * Your MovingAverage object will be instantiated and called as such:
 * MovingAverage obj = new MovingAverage(size);
 * double param_1 = obj.next(val);
 */
