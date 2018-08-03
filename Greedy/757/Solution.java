class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }    
        });
        // proceed from front to end 
        int res = 2, end = intervals[0][1], preend = Integer.MAX_VALUE;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > end) {
                res += 2;
                end = intervals[i][1];
                preend = Integer.MAX_VALUE;
            } else if (intervals[i][0] == end || intervals[i][0] > preend) {
                res += 1;
                preend = end;
                end = intervals[i][1];
            }
        }
        return res;
    }
}


class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            }    
        });
        // proceed from back to front 
        int res = 2, start = intervals[intervals.length-1][0], prestart = Integer.MIN_VALUE;
        for (int i = intervals.length-2; i >= 0; i--) {
            if (intervals[i][1] < start) {
                res += 2;
                start = intervals[i][0];
                prestart = Integer.MIN_VALUE;
            } else if (intervals[i][1] == start || intervals[i][1] < prestart) {
                res += 1;
                prestart = start;
                start = intervals[i][0];
            }
        }
        return res;
    }
}


class Solution {
    public int intersectionSizeTwo(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            }    
        });
        //           last added element and the second last added element 
        int res = 2, last = intervals[0][1], seclast = intervals[0][1]-1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] > seclast && intervals[i][0] <= last) {
                res += 1;
                seclast = last;
                last = intervals[i][1];
            } else if (intervals[i][0] > last) {
                res += 2;
                last = intervals[i][1];
                seclast = intervals[i][1]-1;
            }
        }
        return res;
    }
}
