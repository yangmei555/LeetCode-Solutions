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
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });
        int count = 0, end = Integer.MIN_VALUE;
        for (Interval in : intervals) {
            if (in.start >= end) {
                end = in.end;
                count++;
            }
        }
        return intervals.length - count;
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
    public int eraseOverlapIntervals(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        int count = 0, pre = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[pre].end <= intervals[i].start) {
                pre = i;
            } else {
                count++;
                if (intervals[pre].end > intervals[i].end) 
                    pre = i;
            }
        }
        return count;
    }
}
