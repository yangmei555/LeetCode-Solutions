class MyCalendar {
    
    List<int[]> list;
    public MyCalendar() {
        list = new ArrayList<>();
    }
    
    public boolean book(int start, int end) {
        boolean res = true;
        for (int[] i : list) {
            if (start < i[1] && end > i[0]) {
                res = false;
                break;
            }
        }
        if (res == false)
            return false;
        list.add(new int[]{start, end});
        return true;
    }
}

/**
 * Your MyCalendar object will be instantiated and called as such:
 * MyCalendar obj = new MyCalendar();
 * boolean param_1 = obj.book(start,end);
 */
