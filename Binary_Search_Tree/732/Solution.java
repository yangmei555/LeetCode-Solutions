// mark start end end points 
class MyCalendarThree {
    Map<Integer, Integer> map;
    public MyCalendarThree() {
        map = new TreeMap<>();
    }
    
    public int book(int start, int end) {
        map.put(start, map.getOrDefault(start, 0) + 1);
        map.put(end, map.getOrDefault(end, 0) - 1);
        if (map.get(start) == 0)
            map.remove(start);
        if (map.get(end) == 0)
            map.remove(end);
        int count = 0, res = 0;
        for (int key : map.keySet()) {
            count += map.get(key);
            res = Math.max(res, count);
        }
        return res;
    }
}

/**
 * Your MyCalendarThree object will be instantiated and called as such:
 * MyCalendarThree obj = new MyCalendarThree();
 * int param_1 = obj.book(start,end);
 */
