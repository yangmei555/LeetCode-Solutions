// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    
    List<Integer> list;
    int index;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    list = new ArrayList<>();
        index = 0;
        while (iterator.hasNext())
            list.add(iterator.next());
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        return list.get(index);
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    return list.get(index++);
	}

	@Override
	public boolean hasNext() {
	    return index != list.size();
	}
}


// Java Iterator interface reference:
// https://docs.oracle.com/javase/8/docs/api/java/util/Iterator.html
class PeekingIterator implements Iterator<Integer> {
    
    Integer get;
    Iterator<Integer> myitr;
	public PeekingIterator(Iterator<Integer> iterator) {
	    // initialize any member here.
	    get = null;
        myitr = iterator;
	}

    // Returns the next element in the iteration without advancing the iterator.
	public Integer peek() {
        if (get == null)
            get = myitr.next();
        return get;
	}

	// hasNext() and next() should behave the same as in the Iterator interface.
	// Override them if needed.
	@Override
	public Integer next() {
	    if (get != null) {
            int res = get;
            get = null;
            return res;
        } else {
            return myitr.next();
        }
	}

	@Override
	public boolean hasNext() {
	    if (get != null) 
            return true;
        else
            return myitr.hasNext();
	}
}
