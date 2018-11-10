class RandomizedCollection {

    /** Initialize your data structure here. */
    Map<Integer, Set<Integer>> map;
    List<Integer> list;
    Random random;
    int size;
    public RandomizedCollection() {
        map = new HashMap<>();
        list = new ArrayList<>();
        random = new Random();
        size = 0;
    }
    
    /** Inserts a value to the collection. 
        Returns true if the collection did not already contain the specified element. */
    public boolean insert(int val) {
        boolean res = !map.containsKey(val);
        // notice that the use of linkedhashset is compulsory here to get strcitly O(1) performance 
        // hashset's next() method is not strictly O(1)
        map.putIfAbsent(val, new LinkedHashSet<>());  
        map.get(val).add(size++);
        list.add(val);
        return res;
    }
    
    /** Removes a value from the collection. 
        Returns true if the collection contained the specified element. */
    public boolean remove(int val) {
        if (map.containsKey(val)) {
            Set<Integer> set = map.get(val);
            // if the set is hashset, the next() performance as something to do with the 
            // set's capacity, see the code block beneath the solution 
            int index = set.iterator().next();
            if (list.get(index) == list.get(size-1)) {
                set.remove(size-1);
            } else {
                int last = list.get(size-1);
                map.get(last).add(index);
                map.get(last).remove(size-1);
                set.remove(index);
                list.set(index, last);
            }
            size--;
            list.remove(size);
            if (set.isEmpty())
                map.remove(val);
            return true;
        } else {
            return false;
        }
    }
    
    /** Get a random element from the collection. */
    public int getRandom() {
        return list.get(random.nextInt(size));
    }
}

/**
 * Your RandomizedCollection object will be instantiated and called as such:
 * RandomizedCollection obj = new RandomizedCollection();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */


// this simple test provided by StefanPochmann can demonstrate the performance difference 
// between hashset's next() method and linkedhashset's next() method , 
// the former takes much longer time than the latter 
class Test {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        for (int i=0; i<=1000000; i++)
            set.add(i);
        for (int i=0; i<1000000; i++)
            set.remove(i);

        long t0 = System.currentTimeMillis();
        for (int i=0; i<10000; i++)
            set.iterator().next();
        System.out.println(System.currentTimeMillis() - t0);
    }
}
