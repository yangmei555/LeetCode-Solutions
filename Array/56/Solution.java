/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new LinkedList<>();
        if (intervals.size() == 0)
            return list;
        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval l1, Interval l2) {
                return l1.start - l2.start;
            }
        });
        Interval pre = intervals.get(0);
        for (int i = 1; i < intervals.size(); i++) {
            Interval cur = intervals.get(i);
            if (pre.end < cur.start) {
                list.add(pre);
                pre = cur;
            } else {
                pre.end = pre.end > cur.end ? pre.end : cur.end;
            }
        }
        list.add(pre);
        return list;
    }
}


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new LinkedList<>();
        if (intervals.size() == 0)
            return list;
        int[] start = new int[intervals.size()], end = new int[intervals.size()];
        for (int i = 0; i < intervals.size(); i++) {
            start[i] = intervals.get(i).start;
            end[i] = intervals.get(i).end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        for (int i = 1, index = 0; i <= start.length; i++) {
            if (i == start.length || start[i] > end[i-1]) {
                list.add(new Interval(start[index], end[i-1]));
                index = i;
            } 
        }
        return list;
    }
}


/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class Solution {
    public List<Interval> merge(List<Interval> intervals) {
        List<Interval> list = new LinkedList<>();
        if (intervals.size() == 0)
            return list;
        for (Interval interval : intervals) {
            int size = list.size(), min = interval.start, max = interval.end;
            for (int i = size-1; i >= 0; i--) {
                Interval in = list.get(i);
                if (min <= in.end && max >= in.start) {
                    list.remove(i);
                    min = min < in.start ? min : in.start;
                    max = max > in.end ? max : in.end;
                }
            }
            list.add(new Interval(min, max));
        }
        return list;
    }
}
