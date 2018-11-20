/**
 * Definition for a point.
 * class Point {
 *     int x;
 *     int y;
 *     Point() { x = 0; y = 0; }
 *     Point(int a, int b) { x = a; y = b; }
 * }
 */
class Solution {
    public List<Point> outerTrees(Point[] points) {
        List<Point> res = new LinkedList<>();
        if (points.length <= 3) {
            for (Point p : points)
                res.add(p);
        } else {
            Point minY = null;
            for (Point p : points) {
                if (minY == null || minY.y > p.y || minY.y == p.y && minY.x > p.x)
                    minY = p;
            }
            Point downLeft = new Point(minY.x, minY.y);
            Arrays.sort(points, new Comparator<Point>() {
                public int compare(Point p1, Point p2) {
                    int val = (p1.x - downLeft.x) * (p2.x - downLeft.x);
                    if (val < 0) {
                        return p2.x - p1.x;
                    } else if (val > 0) {
                        val = (p1.y - downLeft.y) * (p2.x - downLeft.x) - 
                                            (p2.y - downLeft.y) * (p1.x - downLeft.x);
                        if (val != 0)
                            return val;
                        else
                            return p1.x - p2.x;
                    } else {
                        if (p1.x == downLeft.x && p2.x == downLeft.x) {
                            return p1.y - p2.y;
                        } else if (p1.x == downLeft.x) {
                            if (p1.y == downLeft.y)
                                return -1;
                            else
                                return p2.x - downLeft.x;
                        } else {
                            if (p2.y == downLeft.y)
                                return 1;
                            else
                                return downLeft.x - p1.x;
                        }
                    }
                }
            });
            Point[] stack = new Point[points.length];
            int index = 0, i = points.length-1, j = points.length-1;
            while (i >= 0 && points[0].x == points[i].x)
                i--;
            for (i++; i < j; i++, j--) {
                Point temp = points[i];
                points[i] = points[j];
                points[j] = temp;
            }
            // graham scan 
            for (i = 0; i < points.length; i++) {
                while (index >= 3) {
                    int x1 = stack[index-1].x - stack[index-2].x;
                    int y1 = stack[index-1].y - stack[index-2].y;
                    int x2 = points[i].x - stack[index-1].x;
                    int y2 = points[i].y - stack[index-1].y;
                    if (x1 * y2 - x2 * y1 >= 0)
                        break;
                    else
                        index--;
                }
                stack[index++] = points[i];
            }
            for (i = 0; i < index; i++)
                res.add(stack[i]);
        }
        return res;
    }
}
