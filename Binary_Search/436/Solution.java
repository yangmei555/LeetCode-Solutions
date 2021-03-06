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
    public int[] findRightInterval(Interval[] intervals) {
        Node[] nodes = new Node[intervals.length];
        for (int i = 0; i < nodes.length; i++)
            nodes[i] = new Node(intervals[i], i);
        Arrays.sort(nodes, new Comparator<Node>() {
            public int compare(Node n1, Node n2) {
                return n1.interval.start - n2.interval.start;
            }
        });
        int[] res = new int[intervals.length];
        for (int i = 0; i < nodes.length; i++) {
            int left = i+1, right = nodes.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (nodes[mid].interval.start < nodes[i].interval.end)
                    left = mid + 1;
                else
                    right = mid;
            }
            res[nodes[i].index] = left == nodes.length ? -1 : nodes[left].index;
        }
        return res;
    }
    
    class Node {
        Interval interval;
        int index;
        public Node(Interval interval, int index) {
            this.interval = interval;
            this.index = index;
        }
    }
}


// only record the starting points and the indices are enough 
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[][] starts = new int[intervals.length][];
        for (int i = 0; i < starts.length; i++)
            starts[i] = new int[]{intervals[i].start, i};
        Arrays.sort(starts, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        int[] res = new int[intervals.length];
        for (int i = 0; i < res.length; i++) {
            int left = 0, right = starts.length;
            while (left < right) {
                int mid = (left + right) / 2;
                if (starts[mid][0] < intervals[i].end)
                    left = mid + 1;
                else
                    right = mid;
            }
            res[i] = left == starts.length ? -1 : starts[left][1];
        }
        return res;
    }
}


// using tree map 
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        for (int i = 0; i < intervals.length; i++)
            map.put(intervals[i].start, i);
        int[] res = new int[intervals.length];
        for (int i = 0; i < res.length; i++) {
            Integer key = map.ceilingKey(intervals[i].end);
            res[i] = key == null ? -1 : map.get(key);
        }
        return res;
    }
}


// the two pointers technique 
class Solution {
    public int[] findRightInterval(Interval[] intervals) {
        int[][] starts = new int[intervals.length][];
        int[][] ends = new int[intervals.length][];
        for (int i = 0; i < intervals.length; i++) {
            starts[i] = new int[]{intervals[i].start, i};
            ends[i] = new int[]{intervals[i].end, i};
        }
        Arrays.sort(starts, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        Arrays.sort(ends, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }
        });
        int[] res = new int[intervals.length];
        for (int i = 0, j = 0; j < ends.length; j++) {
            while (i < starts.length && starts[i][0] < ends[j][0])
                i++;
            res[ends[j][1]] = i == starts.length ? -1 : starts[i][1];
        }
        return res;
    }
}
