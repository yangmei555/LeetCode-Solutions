class MyHashSet {

    /** Initialize your data structure here. */
    boolean[] map;
    public MyHashSet() {
        map = new boolean[1000001];
    }
    
    public void add(int key) {
        map[key] = true;
    }
    
    public void remove(int key) {
        map[key] = false;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        return map[key];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */


// a more space efficient approach, allocate memory only when needed 
class MyHashSet {

    /** Initialize your data structure here. */
    boolean[][] map;
    int bucket, size;
    public MyHashSet() {
        bucket = 1001;
        size = 1000000 / bucket + 1;
        map = new boolean[bucket][];
    }
    
    public int hash(int key) {
        return key % bucket;
    }
    
    public void add(int key) {
        int h = hash(key);
        if (map[h] == null)
            map[h] = new boolean[size];
        map[h][key / bucket] = true;
    }
    
    public void remove(int key) {
        int h = hash(key);
        if (map[h] != null)
            map[h][key / bucket] = false;
    }
    
    /** Returns true if this set contains the specified element */
    public boolean contains(int key) {
        int h = hash(key);
        return map[h] != null && map[h][key / bucket];
    }
}

/**
 * Your MyHashSet object will be instantiated and called as such:
 * MyHashSet obj = new MyHashSet();
 * obj.add(key);
 * obj.remove(key);
 * boolean param_3 = obj.contains(key);
 */
