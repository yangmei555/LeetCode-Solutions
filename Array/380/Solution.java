class RandomizedSet {
    
    Set<Integer> set;
    List<Integer> list;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        set = new HashSet<>();
        list = new LinkedList<>();
        random = new Random(0);
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (set.contains(val))
            return false;
        set.add(val);
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!set.contains(val))
            return false;
        set.remove(val);
        list.remove((Integer)val);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int index = random.nextInt(set.size());
        return list.get(index);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


class RandomizedSet {
    
    Map<Integer, Integer> map1, map2;
    int index;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map1 = new HashMap<>();
        map2 = new HashMap<>();
        index = 0;
        random = new Random(0);
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map1.containsKey(val))
            return false;
        map1.put(val, index);
        map2.put(index, val);
        index++;
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map1.containsKey(val))
            return false;
        int n = map1.get(val);
        int last = map2.get(index-1);
        map1.put(last, n);
        map2.put(n, last);
        map1.remove(val);
        map2.remove(index-1);
        index--;
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int x = random.nextInt(index);
        return map2.get(x);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


class RandomizedSet {

    Map<Integer, Integer> map;
    List<Integer> list;
    int index;
    Random random;
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        list = new ArrayList<>();
        index = 0;
        random = new Random(0);
    }
    
    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (map.containsKey(val))
            return false;
        map.put(val, index);
        index++;
        list.add(val);
        return true;
    }
    
    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val))
            return false;
        int n = map.get(val);
        index--;
        if (n != index) {
            int last = list.get(index);
            map.put(last, n);
            list.set(n, last);
        }
        map.remove(val);
        list.remove(index);
        return true;
    }
    
    /** Get a random element from the set. */
    public int getRandom() {
        int x = random.nextInt(index);
        return list.get(x);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
