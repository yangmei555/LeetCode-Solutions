class MapSum {

    /** Initialize your data structure here. */
    Node root;
    public MapSum() {
        root = new Node();
    }
    
    // before inserting, check first whether "key" already exists 
    // if it does, need to minus its original value along its path 
    public void insert(String key, int val) {
        Node cur = root;
        int prev = -1;
        for (char c : key.toCharArray()) {
            if (cur.nodes[c-'a'] == null) {
                prev = 0;
                break;
            }
            cur = cur.nodes[c-'a'];
        }
        if (prev == -1)
            prev = cur.val;
        cur = root;
        for (char c : key.toCharArray()) {
            if (cur.nodes[c-'a'] == null)
                cur.nodes[c-'a'] = new Node();
            cur.total += val - prev;
            cur = cur.nodes[c-'a'];
        }
        cur.total += val - prev;
        cur.val = val;
        cur.end = true;
    }
    
    public int sum(String prefix) {
        Node cur = root;
        for (char c : prefix.toCharArray()) {
            if (cur.nodes[c-'a'] == null)
                return 0;
            cur = cur.nodes[c-'a'];
        }
        return cur.total;
    }
    
    class Node {
        Node[] nodes;
        boolean end;
        int total, val;
        public Node() {
            nodes = new Node[26];
            total = 0;
            val = 0;
        }
    }
}

/**
 * Your MapSum object will be instantiated and called as such:
 * MapSum obj = new MapSum();
 * obj.insert(key,val);
 * int param_2 = obj.sum(prefix);
 */
