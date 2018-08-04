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
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> list = new ArrayList<>();
        for (List<Interval> l : schedule) {
            for (Interval i : l)
                list.add(i);
        }
        Collections.sort(list, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        // actually no need to use work list 
        List<Interval> work = new ArrayList<>();
        int start = list.get(0).start, end = list.get(0).end;
        for (int i = 1; i <= list.size(); i++) {
            if (i != list.size() && list.get(i).start <= end) {
                end = Math.max(end, list.get(i).end);
            } else {
                work.add(new Interval(start, end));
                if (i != list.size()) {
                    start = list.get(i).start;
                    end = list.get(i).end;
                }
            }
        }
        List<Interval> free = new LinkedList<>();
        for (int i = 1; i < work.size(); i++)
            free.add(new Interval(work.get(i-1).end, work.get(i).start));
        return free;
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
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        Queue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        for (List<Interval> l : schedule) {
            for (Interval i : l)
                queue.offer(i);
        }
        List<Interval> free = new LinkedList<>();
        int end = queue.poll().end;
        while (!queue.isEmpty()) {
            Interval in = queue.poll();
            if (in.start <= end) {
                end = Math.max(end, in.end);
            } else {
                free.add(new Interval(end, in.start));
                end = in.end;
            }
        }
        return free;
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
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Integer> start = new ArrayList<>(), end = new ArrayList<>();
        for (List<Interval> list : schedule) {
            for (Interval in : list) {
                start.add(in.start);
                end.add(in.end);
            }
        }
        Collections.sort(start);
        Collections.sort(end);
        List<Interval> res = new LinkedList<>();
        for (int i = 1; i < start.size(); i++) {
            if (end.get(i-1) < start.get(i))
                res.add(new Interval(end.get(i-1), start.get(i)));
        }
        return res;
    }
}
