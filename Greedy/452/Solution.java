class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[1] - i2[1];
            } 
        });
        int res = 1, last = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] > last) {
                res++;
                last = points[i][1];
            }
        }
        return res;
    }
}


class Solution {
    public int findMinArrowShots(int[][] points) {
        if (points.length == 0)
            return 0;
        Arrays.sort(points, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                return i1[0] - i2[0];
            } 
        });
        int res = 1, last = points[0][1];
        for (int i = 1; i < points.length; i++) {
            if (last < points[i][0]) {
                res++;
                last = points[i][1];
            } else if (points[i][1] < last) {
                last = points[i][1];
            }
        }
        return res;
    }
}
