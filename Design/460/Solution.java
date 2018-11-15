class LFUCache {
    Node head, tail;
    Map<Integer, Integer> map;
    Map<Integer, Node> toFreq;
    int size;
    public LFUCache(int capacity) {
        size = capacity;
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>();
        toFreq = new HashMap<>();
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            Node node = toFreq.get(key);
            addAfterNode(node, key);
            node.set.remove(key);
            if (node.set.isEmpty()) 
                removeNode(node); 
            return map.get(key);
        }
    }
    
    public void put(int key, int value) {
        if (size == 0)
            return;
        if (!map.containsKey(key) && map.size() == size) {
            Node node = head.next;
            int k = node.set.iterator().next();
            node.set.remove(k);
            if (node.set.isEmpty()) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
            }
            map.remove(k);
            toFreq.remove(k);
        }
        if (!toFreq.containsKey(key)) {
            addAfterNode(head, key);
        } else {
            Node node = toFreq.get(key);
            addAfterNode(node, key);
            node.set.remove(key);
            if (node.set.isEmpty()) 
                removeNode(node);
        }
        map.put(key, value);
    }
    
    public void addAfterNode(Node node, int key) {
        Node newNode = null;
        if (node.next == tail || node.next.freq != node.freq + 1) {
            newNode = new Node(node.freq + 1);
            newNode.next = node.next;
            newNode.next.prev = newNode;
            node.next = newNode;
            newNode.prev = node;
        } else {
            newNode = node.next;
        }
        newNode.set.add(key);
        toFreq.put(key, newNode);
    }
    
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    class Node {
        Node prev, next;
        int freq;
        Set<Integer> set;
        public Node(int freq) {
            this.freq = freq;
            set = new LinkedHashSet<>();
            prev = null;
            next = null;
        }
    }
}


// another style of using doubly linked list 
class LFUCache {
    
    Map<Integer, int[]> map;
    Map<Integer, Node> freqSets;
    int size;
    Node head, tail;
    public LFUCache(int capacity) {
        size = capacity;
        map = new HashMap<>();
        freqSets = new HashMap<>();
        head = new Node(0);
        tail = new Node(0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        if (!map.containsKey(key))
            return -1;
        int[] valuePair = map.get(key);
        Node node = freqSets.get(valuePair[1]);
        node.set.remove(key);
        valuePair[1]++;
        insertBehindNode(node, valuePair[1]);
        node.next.set.add(key);
        if (node.set.isEmpty())
            removeNode(node);
        return valuePair[0];
    }
    
    public void put(int key, int value) {
        if (size == 0)
            return;
        if (!map.containsKey(key)) {
            if (map.size() == size) {
                int evict = head.next.set.iterator().next();
                head.next.set.remove(evict);
                if (head.next.set.isEmpty())
                    removeNode(head.next);
                map.remove(evict);
            }
            insertBehindNode(head, 1);
            head.next.set.add(key);
            map.put(key, new int[]{value, 1});
        } else {
            get(key);
            int[] valuePair = map.get(key);
            valuePair[0] = value;
        }
    }
    
    public void insertBehindNode(Node node, int val) {
        if (node.next.freq != val) {
            Node newNode = new Node(val);
            freqSets.put(val, newNode);
            newNode.next = node.next;
            newNode.next.prev = newNode;
            newNode.prev = node;
            node.next = newNode;
        }
    }
    
    public void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        freqSets.remove(node.freq);
    }
    
    class Node {
        int freq;
        Node prev, next;
        Set<Integer> set;
        public Node(int freq) {
            this.freq = freq;
            set = new LinkedHashSet<>();
        }
    }
}


// add 1 freq set first !!! 
class LFUCache {
    Map<Integer, int[]> map;
    Map<Integer, Set<Integer>> freq;
    int size, minFreq;
    public LFUCache(int capacity) {
        size = capacity;
        minFreq = 1;
        map = new HashMap<>();
        freq = new HashMap<>();
        freq.put(1, new LinkedHashSet<>());
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        } else {
            int[] node = map.get(key);
            freq.get(node[1]).remove(key);
            if (minFreq == node[1] && freq.get(node[1]).isEmpty())
                minFreq++;
            node[1]++;
            freq.putIfAbsent(node[1], new LinkedHashSet<>());
            freq.get(node[1]).add(key);
            return node[0];
        }
    }
    
    public void put(int key, int value) {
        if (size == 0)
            return;
        if (!map.containsKey(key)) {
            if (map.size() == size) {
                int k = freq.get(minFreq).iterator().next();
                freq.get(minFreq).remove(k);
                map.remove(k);
            }
            map.put(key, new int[]{value, 1});
            freq.get(1).add(key);
            minFreq = 1;
        } else {
            int[] node = map.getOrDefault(key, new int[]{value, 1});
            node[0] = value;
            map.put(key, node);
            get(key);
        }
    }
}
