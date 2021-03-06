class KthLargest {
    Queue<Integer> queue = new PriorityQueue<>();
    public KthLargest(int k, int[] nums) {
        for (int n : nums) {
            queue.offer(n);
            if (queue.size() > k)
                queue.poll();
        }
        while (queue.size() < k)
            queue.offer(Integer.MIN_VALUE);
    }
    
    public int add(int val) {
        queue.offer(val);
        queue.poll();
        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */


class KthLargest {
    Queue<Integer> queue = new PriorityQueue<>();
    int k;
    public KthLargest(int k, int[] nums) {
        this.k = k;
        for (int n : nums) {
            queue.offer(n);
            if (queue.size() > k)
                queue.poll();
        }
        while (queue.size() < k)
            queue.offer(Integer.MIN_VALUE);
    }
    
    public int add(int val) {
        if (val > queue.peek())
            queue.offer(val);
        if (queue.size() > k)
            queue.poll();
        return queue.peek();
    }
}

/**
 * Your KthLargest object will be instantiated and called as such:
 * KthLargest obj = new KthLargest(k, nums);
 * int param_1 = obj.add(val);
 */
