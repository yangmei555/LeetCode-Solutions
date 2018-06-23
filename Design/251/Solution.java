public class Vector2D implements Iterator<Integer> {
    
    Iterator<Integer> itr;
    List<List<Integer>> lists;
    int index;
    public Vector2D(List<List<Integer>> vec2d) {
        lists = vec2d;
        index = 0;
        for (List<Integer> list : vec2d) {
            itr = list.iterator();
            if (itr.hasNext())
                break;
            index++;
        }
    }

    @Override
    public Integer next() {
        int res = -1;
        if (itr.hasNext()) {
            res = itr.next();
        }
        if (!itr.hasNext()) {
            index++;
            for (int i = index; i < lists.size(); i++, index++) {
                itr = lists.get(i).iterator();
                if (itr.hasNext())
                    break;
            }
        }
        return res;
    }

    @Override
    public boolean hasNext() {
        return index != lists.size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */


public class Vector2D implements Iterator<Integer> {
    
    Iterator<Integer> itr;
    List<List<Integer>> lists;
    int index;
    public Vector2D(List<List<Integer>> vec2d) {
        lists = vec2d;
        itr = vec2d.size() == 0 ? null : vec2d.get(0).iterator();
        index = 0;
    }

    @Override
    public Integer next() {
        return itr.next();
    }

    @Override
    public boolean hasNext() {
        while (itr != null && !itr.hasNext()) {
            index++;
            if (index == lists.size())
                break;
            itr = lists.get(index).iterator();
        }
        return index != lists.size();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */


public class Vector2D implements Iterator<Integer> {
    
    Iterator<Integer> itr;
    List<Integer> list;
    public Vector2D(List<List<Integer>> vec2d) {
        list = new LinkedList<>();
        for (List<Integer> l : vec2d) {
            for (int i : l)
                list.add(i);
        }
        itr = list.iterator();
    }

    @Override
    public Integer next() {
        return itr.next();
    }

    @Override
    public boolean hasNext() {
        return itr.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */


public class Vector2D implements Iterator<Integer> {
    
    Iterator<List<Integer>> listitr;
    Iterator<Integer> intitr;
    public Vector2D(List<List<Integer>> vec2d) {
        listitr = vec2d.iterator();
        intitr = null;
    }

    @Override
    public Integer next() {
        return intitr.next();
    }

    @Override
    public boolean hasNext() {
        while (listitr.hasNext() && (intitr == null || !intitr.hasNext())) 
            intitr = listitr.next().iterator();
        return intitr != null && intitr.hasNext();
    }
}

/**
 * Your Vector2D object will be instantiated and called as such:
 * Vector2D i = new Vector2D(vec2d);
 * while (i.hasNext()) v[f()] = i.next();
 */
