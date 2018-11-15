// seems easy but difficult to implement 
class AllOne {

    /** Initialize your data structure here. */
    Map<String, Integer> map;
    Map<Integer, Node> linkedMap;
    Node head, tail;
    public AllOne() {
        map = new HashMap<>();
        linkedMap = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }
    
    /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
    public void inc(String key) {
        if (!map.containsKey(key)) {
            insertBehideNode(head, 1);
            head.next.set.add(key);
            map.put(key, 1);
        } else {
            int v = map.get(key);
            Node node = linkedMap.get(v);
            node.set.remove(key);
            insertBehideNode(node, v+1);
            node.next.set.add(key);
            if (node.set.isEmpty())
                removeNode(node);
            map.put(key, v+1);
        }
    }
    
    /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
    public void dec(String key) {
        if (map.containsKey(key)) {
            int v = map.get(key);
            Node node = linkedMap.get(v);
            node.set.remove(key);
            if (v != 1) {
                // this is actually "insertBefore $'node'" 
                if (node.prev.freq != v-1)
                    insertBehideNode(node.prev, v-1);
                node.prev.set.add(key);
                map.put(key, v-1);
            } else {
                map.remove(key);
            }
            if (node.set.isEmpty()) 
                removeNode(node);
        }
    }
    
    public void insertBehideNode(Node node, int freq) {
        if (node.next.freq != freq) {
            Node newNode = new Node(freq);
            newNode.next = node.next;
            newNode.next.prev = newNode;
            node.next = newNode;
            newNode.prev = node;
            linkedMap.put(freq, newNode);
        }
    }
    
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        linkedMap.remove(node.freq);
    }
    
    /** Returns one of the keys with maximal value. */
    public String getMaxKey() {
        if (tail.prev != head)
            return tail.prev.set.iterator().next();
        else
            return "";
    }
    
    /** Returns one of the keys with Minimal value. */
    public String getMinKey() {
        if (head.next != tail) 
            return head.next.set.iterator().next();
        else
            return "";
    }
    
    class Node {
        int freq;
        Node prev, next;
        Set<String> set;
        public Node(int freq) {
            this.freq = freq;
            set = new HashSet<>();
        }
    }
}

/**
 * Your AllOne object will be instantiated and called as such:
 * AllOne obj = new AllOne();
 * obj.inc(key);
 * obj.dec(key);
 * String param_3 = obj.getMaxKey();
 * String param_4 = obj.getMinKey();
 */
