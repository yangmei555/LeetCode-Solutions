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
    public int maxPoints(Point[] points) {
        int res = 0;
        for (int i = 0; i < points.length; i++) {
            Map<String, Integer> map = new HashMap<>();
            int dup = 0;
            for (int j = 0; j < points.length; j++) {
                // pay attention to the duplicate points 
                if (points[i].x == points[j].x && points[i].y == points[j].y) {
                    dup++;
                    continue;
                }
                int A = points[j].y - points[i].y;
                int B = points[i].x - points[j].x;
                int C = points[j].x * points[i].y - points[i].x * points[j].y;
                int gcd = helper(A, helper(B, C));
                A /= gcd;
                B /= gcd;
                C /= gcd;
                if (A == 0 && B < 0) {
                    B = -B;
                    C = -C;
                } else if (A < 0) {
                    A = -A;
                    B = -B;
                    C = -C;
                }
                String str = A + " " + B + " " + C;
                map.put(str, map.getOrDefault(str, 0) + 1);
            }
            int max = 0;
            for (String str : map.keySet())
                max = Math.max(max, map.get(str));
            res = Math.max(res, max + dup);
        }
        return res;
    }
    
    public int helper(int a, int b) {
        if (b == 0)
            return a;
        else
            return helper(b, a % b);
    }
}
