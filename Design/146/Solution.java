class LRUCache {
    
    Map<Integer, Integer> map;
    int size;
    public LRUCache(int capacity) {
        size = capacity;
        map = new LinkedHashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            int res = map.get(key);
            map.remove(key);
            map.put(key, res);
            return res;
        }
    }
    
    public void put(int key, int value) {
        if (map.containsKey(key))
            map.remove(key);
        map.put(key, value);
        if (map.size() > size) {
            int k = map.keySet().iterator().next();
            map.remove(k);
        }
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
