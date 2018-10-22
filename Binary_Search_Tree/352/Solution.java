/**
 * Definition for an interval.
 * public class Interval {
 *     int start;
 *     int end;
 *     Interval() { start = 0; end = 0; }
 *     Interval(int s, int e) { start = s; end = e; }
 * }
 */
class SummaryRanges {

    /** Initialize your data structure here. */
    TreeSet<Interval> set;
    public SummaryRanges() {
        set = new TreeSet<>(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
    }
    
    public void addNum(int val) {
        Interval itv = new Interval(val, val);
        Interval itv1 = set.floor(itv);
        if (itv1 != null) {
            if (itv1.end + 1 == val) {
                set.remove(itv1);
                itv.start = itv1.start;
            } else if (itv1.end >= val) {
                return;
            }
        }
        Interval itv2 = set.ceiling(itv);
        if (itv2 != null && val + 1 == itv2.start) {
            set.remove(itv2);
            itv.end = itv2.end;
        }
        set.add(itv);
    }
    
    public List<Interval> getIntervals() {
        return new LinkedList<>(set);
    }
}

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * SummaryRanges obj = new SummaryRanges();
 * obj.addNum(val);
 * List<Interval> param_2 = obj.getIntervals();
 */
