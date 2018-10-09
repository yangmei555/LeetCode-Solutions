// this solutions is actaully not fully correct, the way to detect overlap rectangles 
// is wrong. but strangely it passes the OJ 
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        Arrays.sort(rectangles, new Comparator<int[]>() {
            public int compare(int[] i1, int[] i2) {
                if (i1[0] == i2[0])
                    return i1[1] - i2[1];
                else
                    return i1[0] - i2[0];
            }
        });
        for (int i = 1; i < rectangles.length; i++) {
            if (rectangles[i-1][2] > rectangles[i][0]) {
                if (rectangles[i-1][1] < rectangles[i][3] && rectangles[i][1] < rectangles[i-1][3])
                    return false;
            }
        }
        Set<Point> points = new HashSet<>();
        for (int[] r : rectangles) {
            Point p1 = new Point(r[0], r[1]), p2 = new Point(r[2], r[3]);
            Point p3 = new Point(r[0], r[3]), p4 = new Point(r[2], r[1]);
            if (!points.add(p1))
                points.remove(p1);
            if (!points.add(p2))
                points.remove(p2);
            if (!points.add(p3))
                points.remove(p3);
            if (!points.add(p4))
                points.remove(p4);
        }
        if (points.size() != 4)
            return false;
        int x1 = Integer.MIN_VALUE, x2 = Integer.MIN_VALUE, 
            y1 = Integer.MIN_VALUE, y2 = Integer.MIN_VALUE;
        for (Point p : points) {
            if (x1 == Integer.MIN_VALUE)
                x1 = p.x;
            else if (x1 != p.x && x2 == Integer.MIN_VALUE)
                x2 = p.x;
            else if (x1 != p.x && x2 != p.x)
                return false;
            if (y1 == Integer.MIN_VALUE)
                y1 = p.y;
            else if (y1 != p.y && y2 == Integer.MIN_VALUE)
                y2 = p.y;
            else if (y1 != p.y && y2 != p.y)
                return false;
        }
        return true;
    }
    
    class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int hashCode() {
            return x * 31 + y;
        }
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return p.x == x && p.y == y;
        }
    }
}


// this solution is correct, just checking overlapping points and area equivalence 
class Solution {
    public boolean isRectangleCover(int[][] rectangles) {
        int area = 0;
        int x1 = Integer.MAX_VALUE, x2 = Integer.MIN_VALUE, 
            y1 = Integer.MAX_VALUE, y2 = Integer.MIN_VALUE;
        Set<Point> points = new HashSet<>();
        for (int[] r : rectangles) {
            x1 = Math.min(x1, r[0]);
            x2 = Math.max(x2, r[2]);
            y1 = Math.min(y1, r[1]);
            y2 = Math.max(y2, r[3]);
            area += (r[2] - r[0]) * (r[3] - r[1]);
            Point p1 = new Point(r[0], r[1]), p2 = new Point(r[2], r[3]);
            Point p3 = new Point(r[0], r[3]), p4 = new Point(r[2], r[1]);
            if (!points.add(p1))
                points.remove(p1);
            if (!points.add(p2))
                points.remove(p2);
            if (!points.add(p3))
                points.remove(p3);
            if (!points.add(p4))
                points.remove(p4);
        }
        if (points.size() != 4)
            return false;
        if (!points.contains(new Point(x1, y1)) || !points.contains(new Point(x1, y2)) || 
            !points.contains(new Point(x2, y1)) || !points.contains(new Point(x2, y2)))
            return false;
        return area == (x2 - x1) * (y2 - y1);
    }
    
    class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int hashCode() {
            return x * 31 + y;
        }
        public boolean equals(Object obj) {
            Point p = (Point)obj;
            return p.x == x && p.y == y;
        }
    }
}
