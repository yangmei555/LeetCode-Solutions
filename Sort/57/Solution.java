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
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        List<Interval> res = new LinkedList<>();
        int index = 0;
        while (index < intervals.size() && intervals.get(index).end < newInterval.start)
            res.add(intervals.get(index++));
        if (index == intervals.size()) {
            res.add(newInterval);
            return res;
        } 
        if (newInterval.end >= intervals.get(index).start) {
            newInterval.start = Math.min(newInterval.start, intervals.get(index).start);
            newInterval.end = Math.max(newInterval.end, intervals.get(index).end);
            while (index < intervals.size() && newInterval.end >= intervals.get(index).start)
                newInterval.end = Math.max(newInterval.end, intervals.get(index++).end);
        }
        res.add(newInterval);
        while (index < intervals.size())
            res.add(intervals.get(index++));
        return res;
    }
}


class Solution {
    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        for (int i = intervals.size()-1; i >= 0; i--) {
            Interval in = intervals.get(i);
            if (in.start <= newInterval.end && newInterval.start <= in.end) {
                newInterval.start = Math.min(newInterval.start, in.start);
                newInterval.end = Math.max(newInterval.end, in.end);
                intervals.remove(i);
            } else if (in.end < newInterval.start) {
                intervals.add(i+1, newInterval);
                return intervals;
            }
        }
        intervals.add(0, newInterval);
        return intervals;
    }
}
