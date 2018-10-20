class MyHashMap {

    /** Initialize your data structure here. */
    int bucket, size;
    int[][] map;
    public MyHashMap() {
        bucket = 1000;
        size = 100000 / bucket + 1;
        map = new int[bucket][];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = key % bucket, pos = key / bucket;
        if (map[hash] == null)
            map[hash] = new int[size];
        map[hash][pos] = value + 1;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains 
        no mapping for the key */
    public int get(int key) {
        int hash = key % bucket, pos = key / bucket;
        if (map[hash] != null && map[hash][pos] > 0)
            return map[hash][pos] - 1;
        else
            return -1;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = key % bucket, pos = key / bucket;
        if (map[hash] != null)
            map[hash][pos] = 0;
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */


// singly linked list solution 
class MyHashMap {

    /** Initialize your data structure here. */
    Node[] nodes;
    int bucket;
    public MyHashMap() {
        bucket = 1000;
        nodes = new Node[bucket];
    }
    
    /** value will always be non-negative. */
    public void put(int key, int value) {
        int hash = key % bucket;
        if (nodes[hash] == null)
            nodes[hash] = new Node(-1, -1);
        Node dummy = nodes[hash];
        while (dummy.next != null && dummy.next.key != key)
            dummy = dummy.next;
        if (dummy.next == null)
            dummy.next = new Node(key, value);
        else
            dummy.next.val = value;
    }
    
    /** Returns the value to which the specified key is mapped, or -1 if this map contains 
        no mapping for the key */
    public int get(int key) {
        int hash = key % bucket;
        Node dummy = nodes[hash];
        if (dummy == null)
            return -1;
        while (dummy.next != null && dummy.next.key != key)
            dummy = dummy.next;
        return dummy.next == null ? -1 : dummy.next.val;
    }
    
    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int hash = key % bucket;
        if (nodes[hash] != null) {
            Node dummy = nodes[hash];
            while (dummy.next != null && dummy.next.key != key)
                dummy = dummy.next;
            if (dummy.next != null)
                dummy.next = dummy.next.next;
        }
    }
    
    class Node {
        int key, val;
        Node next;
        public Node(int key, int val) {
            this.key = key;
            this.val = val;
        }
    }
}

/**
 * Your MyHashMap object will be instantiated and called as such:
 * MyHashMap obj = new MyHashMap();
 * obj.put(key,value);
 * int param_2 = obj.get(key);
 * obj.remove(key);
 */
