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
    public int minMeetingRooms(Interval[] intervals) {
        Node[] nodes = new Node[intervals.length * 2];
        for (int i = 0; i < intervals.length; i++) {
            nodes[i*2] = new Node(intervals[i].start, true);
            nodes[i*2+1] = new Node(intervals[i].end, false);
        }
        Arrays.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                if (n1.value == n2.value) 
                    return !n1.start && n2.start ? -1 : 1;
                else
                    return n1.value - n2.value;
            } 
        });
        int res = 0, used = 0;
        for (int i = 0; i < nodes.length; i++) {
            if (nodes[i].start) {
                if (used == 0)
                    res++;
                else
                    used--;
            } else {
                used++;
            }
        }
        return res;
    }
    
    class Node {
        int value;
        boolean start;
        public Node(int value, boolean start) {
            this.value = value;
            this.start = start;
        }
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
    public int minMeetingRooms(Interval[] intervals) {
        int[] start = new int[intervals.length], end = new int[intervals.length];
        for (int i = 0; i < intervals.length; i++) {
            start[i] = intervals[i].start;
            end[i] = intervals[i].end;
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int res = 0, index1 = 0, index2 = 0;
        while (index1 < start.length) {
            if (start[index1] < end[index2]) 
                res++;
            else 
                index2++;
            index1++;
        }
        return res;
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
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        PriorityQueue<Interval> queue = new PriorityQueue<>(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.end - i2.end;
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            if (!queue.isEmpty() && queue.peek().end <= intervals[i].start) {
                Interval in = queue.poll();
                in.end = intervals[i].end;
                queue.offer(in);
            } else {
                queue.offer(intervals[i]);
            }
        }
        return queue.size();
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
    public int minMeetingRooms(Interval[] intervals) {
        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int i = 0; i < intervals.length; i++) {
            if (!queue.isEmpty() && queue.peek() <= intervals[i].start) 
                queue.poll();
            queue.offer(intervals[i].end);
        }
        return queue.size();
    }
}
