class PhoneDirectory {

    /** Initialize your data structure here
        @param maxNumbers - The maximum numbers that can be stored in the phone directory. */
    int index, size;
    Queue<Integer> released;
    boolean[] used;
    public PhoneDirectory(int maxNumbers) {
        index = 0;
        size = maxNumbers;
        released = new LinkedList<>();
        used = new boolean[size];
    }
    
    /** Provide a number which is not assigned to anyone.
        @return - Return an available number. Return -1 if none is available. */
    public int get() {
        if (!released.isEmpty()) {
            int res = released.poll();
            used[res] = true;
            return res;
        } else {
            if (index == size) {
                return -1;
            } else {
                int res = index++;
                used[res] = true;
                return res;
            }
        }
    }
    
    /** Check if a number is available or not. */
    public boolean check(int number) {
        if (number >= 0 && number < size)
            return !used[number];
        else
            return false;
    }
    
    /** Recycle or release a number. */
    public void release(int number) {
        if (number >= 0 && number < size && used[number]) {
            used[number] = false;
            released.offer(number);
        }
    }
}

/**
 * Your PhoneDirectory object will be instantiated and called as such:
 * PhoneDirectory obj = new PhoneDirectory(maxNumbers);
 * int param_1 = obj.get();
 * boolean param_2 = obj.check(number);
 * obj.release(number);
 */
