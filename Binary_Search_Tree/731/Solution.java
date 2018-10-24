class MyCalendarTwo {
    TreeSet<int[]> set;
    public MyCalendarTwo() {
        set = new TreeSet<>(new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
    }
    
    public boolean book(int start, int end) {
        List<int[]> toBeAdded = new LinkedList<>();
        List<int[]> toBeRemoved = new LinkedList<>();
        int[] range = new int[]{start, end, 0};
        int[] range1 = set.floor(range);
        if (range1 != null && range[0] < range1[1]) {
            if (range1[2] == 2)
                return false;
            toBeRemoved.add(range1);
            if (range[0] != range1[0])
                toBeAdded.add(new int[]{range1[0], range[0], 1});
            if (range[1] <= range1[1]) {
                toBeAdded.add(new int[]{range[0], range[1], 2});
                if (range[1] != range1[1])
                    toBeAdded.add(new int[]{range[1], range1[1], 1});
                for (int[] to : toBeRemoved)
                    set.remove(to);
                for (int[] to : toBeAdded)
                    set.add(to);
                return true;
            } else {
                toBeAdded.add(new int[]{range[0], range1[1], 2});
                range[0] = range1[1];
            }
        }
        int[] range2 = set.ceiling(range);
        while (range2 != null && range2[0] < range[1]) {
            if (range2[2] == 2)
                return false;
            toBeRemoved.add(range2);
            if (range[0] != range2[0])
                toBeAdded.add(new int[]{range[0], range2[0], 1});
            if (range[1] <= range2[1]) {
                toBeAdded.add(new int[]{range2[0], range[1], 2});
                if (range[1] != range2[1])
                    toBeAdded.add(new int[]{range[1], range2[1], 1});
                range[0] = range2[1];
                break;
            } else {
                toBeAdded.add(new int[]{range2[0], range2[1], 2});
                range[0] = range2[1];
                range2 = set.ceiling(range);
            }
        }
        if (range[0] < range[1])
            toBeAdded.add(new int[]{range[0], range[1], 1});
        for (int[] to : toBeRemoved)
            set.remove(to);
        for (int[] to : toBeAdded)
            set.add(to);
        return true;
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */


// O(n^2) method but quite easy to implement 
class MyCalendarTwo {
    List<int[]> ranges;
    List<int[]> overlap;
    public MyCalendarTwo() {
        ranges = new LinkedList<>();
        overlap = new LinkedList<>();
    }
    
    public boolean book(int start, int end) {
        for (int[] ol : overlap) {
            if (start < ol[1] && ol[0] < end)
                return false;
        }
        for (int[] range : ranges) {
            if (start < range[1] && range[0] < end)
                overlap.add(new int[]{Math.max(start, range[0]), Math.min(end, range[1])});
        }
        return ranges.add(new int[]{start, end});
    }
}

/**
 * Your MyCalendarTwo object will be instantiated and called as such:
 * MyCalendarTwo obj = new MyCalendarTwo();
 * boolean param_1 = obj.book(start,end);
 */
