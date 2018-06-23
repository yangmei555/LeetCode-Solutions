public class ZigzagIterator {
    
    Iterator<Integer>[] itrs;
    int index;
    public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
        itrs = new Iterator[2]; 
        itrs[0] = v1.iterator();
        itrs[1] = v2.iterator();
        index = 0;
    }

    public int next() {
        int res = itrs[index].next();
        index = (index + 1) % 2;
        return res;
    }

    public boolean hasNext() {
        int cur = index;
        while (!itrs[index].hasNext()) {
            index = (index + 1) % 2;
            if (index == cur)
                return false;
        }
        return true;
    }
}

/**
 * Your ZigzagIterator object will be instantiated and called as such:
 * ZigzagIterator i = new ZigzagIterator(v1, v2);
 * while (i.hasNext()) v[f()] = i.next();
 */
